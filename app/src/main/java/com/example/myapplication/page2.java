package com.example.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.Model.NUsers;
import com.example.myapplication.Prevalent.NUserPrevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.paperdb.Paper;

public class page2 extends AppCompatActivity {
    ProgressDialog loading;
    public static final int PER_CODEE=1;
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        loading=new ProgressDialog(this);
        Paper.init(this);
        String NUserPhoneKey= Paper.book().read(NUserPrevalent.NUserPhoneKey);
        String NUserPasswordKey=Paper.book().read(NUserPrevalent.NUserPasswordKey);
        if(NUserPhoneKey != "" && NUserPasswordKey != "")
        {
            if(!TextUtils.isEmpty(NUserPhoneKey) && !TextUtils.isEmpty(NUserPasswordKey))
            {

                AllowAccess(NUserPhoneKey,NUserPasswordKey);
            }
        }

        if(ContextCompat.checkSelfPermission(page2.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(page2.this,"You have Already Granted",Toast.LENGTH_SHORT).show();

        }else{
            requestStoragePermession();
        }






    }
    public void requestStoragePermession()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("This Permission is needed beacause of this and that")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(page2.this,new String[]{Manifest.permission.CALL_PHONE},PER_CODEE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},PER_CODEE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PER_CODEE)
        {
            if(grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }




    }


    public void AllowAccess(final String phone,final String password)
    {
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        final FirebaseFirestore db =FirebaseFirestore.getInstance();
        final DocumentReference reference=db.collection("NUsers").document(phone);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot snap=task.getResult();
                    NUsers users =snap.toObject(NUsers.class);
                    if(snap.exists())
                    {
                        if(decrypt(users.getNpassword()).equals(password))
                        {
                            finish();
                            Toast.makeText(page2.this,"تم الدخول ..",Toast.LENGTH_SHORT).show();
                            //loadingBar.dismiss();
                            Intent intent =new Intent(page2.this,first_brows.class);
                            loading.dismiss();
                            NUserPrevalent.currentOnlineNUsers=users;
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(page2.this,"كلمة السر لهذا الرقم "+phone+" خاطئه !!!",Toast.LENGTH_SHORT).show();

                            loading.dismiss();

                        }

                    }
                    else{

                        Toast.makeText(page2.this, "لا يوجد حساب بهذا الرقم " +phone, Toast.LENGTH_SHORT).show();
                        //loadingBar.dismiss();
                        loading.dismiss();
                    }
                }
            }
        });
    }
    public void vendors(View view)
    {
        Intent goToVendorLogIn = new Intent(this    , vendor_log_in.class);

        startActivity(goToVendorLogIn);

    }
    public void showCategories(final View view)
    {

        Intent goCateg = new Intent(this,first_brows.class);

        startActivity(goCateg);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

        System.exit(1);
    }
    public void normalUser(View view)
    {
        Intent intent=new Intent(page2.this,signinyoy.class);
        startActivity(intent);
    }
    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decode(encrypted,1));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
