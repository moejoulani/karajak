package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.NUsers;

import com.example.myapplication.Prevalent.NUserPrevalent;
import com.example.myapplication.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.paperdb.Paper;

public class signinyoy extends AppCompatActivity {
    private TextView createNU;
    private EditText normalPhone, normalPassword;
    private Button Go;
    private FirebaseAuth auth;
    private ProgressDialog loading;
    private DocumentReference userRef;
    private CheckBox checkBox;
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinyoy);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));

       // userRef=FirebaseFirestore.getInstance().collection("")
        checkBox=(CheckBox)findViewById(R.id.remember);
        Paper.init(this);
        loading = new ProgressDialog(this);
        createNU = (TextView) findViewById(R.id.createNuser);
        createNU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(signinyoy.this, createNormUser.class);
                startActivity(intent);
            }
        });
        normalPhone = (EditText) findViewById(R.id.normalUserPhone);
        normalPassword = (EditText) findViewById(R.id.normalUserPassword);

        Go = (Button) findViewById(R.id.signInNor);
        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });


    }

    public void LoginUser() {
        loading.setTitle("الدخول للحساب");
        loading.setMessage("يرجى الأنتظار....");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        String phone = normalPhone.getText().toString();
        String password = normalPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "الرجاء ادخل رقم الهاتف !...", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "الرجاء ادخل كلمة السر !....", Toast.LENGTH_SHORT).show();
        } else {

        }
        AllowAcessToAccount(phone, password);

    }
    public void AllowAcessToAccount(final String phone, final String password)
    {
        if(checkBox.isChecked())
        {
            Paper.book().write(NUserPrevalent.NUserPhoneKey, phone);
            Paper.book().write(NUserPrevalent.NUserPasswordKey, password);
        }
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

                            
                            String deviceToken   = FirebaseInstanceId.getInstance().getToken();

                            finish();
                            Toast.makeText(signinyoy.this,"تم الدخول ..",Toast.LENGTH_SHORT).show();
                            //loadingBar.dismiss();
                            Intent intent =new Intent(signinyoy.this,first_brows.class);
                            loading.dismiss();
                            NUserPrevalent.currentOnlineNUsers=users;
                            Map<String,Object> map=new HashMap<>();
                            map.put("deviceToken",deviceToken);
                            db.collection("NUsers").document(NUserPrevalent.currentOnlineNUsers.getNphone()).collection("Token").document(NUserPrevalent.currentOnlineNUsers.getNphone()).set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(signinyoy.this,"Token Token karajak",Toast.LENGTH_SHORT).show();
                                }
                            });
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(signinyoy.this,"كلمة السر لهذا الرقم "+phone+" خاطئه !!!",Toast.LENGTH_SHORT).show();

                            loading.dismiss();

                        }

                    }
                    else{

                        Toast.makeText(signinyoy.this, "لا يوجد حساب بهذا الرقم " +phone, Toast.LENGTH_SHORT).show();
                        //loadingBar.dismiss();
                        loading.dismiss();
                    }
                }
            }
        });
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
