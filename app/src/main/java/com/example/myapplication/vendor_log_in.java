package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Users;
import com.example.myapplication.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.paperdb.Paper;

public class vendor_log_in extends AppCompatActivity {
    private EditText InputPhone, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar11;
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    private String ParentDbName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_log_in);

        loadingBar11 = new ProgressDialog(this);

        try {
            InputPhone = (EditText) findViewById(R.id.phoneNumber);
            InputPassword = (EditText) findViewById(R.id.password);
            LoginButton = (Button) findViewById(R.id.go_log);

            //_______________________________________________________________________//
            Paper.init(this);

            LoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(vendor_log_in.this,"جار الدخول ....",Toast.LENGTH_LONG).show();

                    LoginUser();
                }
            });
        } catch (Exception e) {
            String s = e.getMessage();
            Toast.makeText(this, "The Error In Karajak Application : " + s, Toast.LENGTH_LONG).show();

        }


    }

    public void LoginUser() {
        loadingBar11.setTitle("الدخول للحساب");
        loadingBar11.setMessage("يرجى الأنتظار....");
        loadingBar11.setCanceledOnTouchOutside(false);
        loadingBar11.show();

        String phone = InputPhone.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(vendor_log_in.this, "الرجاء ادخل رقم الهاتف !...", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(vendor_log_in.this, "الرجاء ادخل كلمة السر !....", Toast.LENGTH_SHORT).show();
        } else {
          /*  loadingBar.setTitle("Creat Account");
            loadingBar.setMessage("please wait");
            loadingBar.show();

            */



          AllowAcessToAccount(phone, password);


        }

    }

    public void AllowAcessToAccount(final String phone, final String password)
    {
        Paper.book().write(Prevalent.UserPasswordKey,password);
        Paper.book().write(Prevalent.UserPhoneKey,phone);



        final FirebaseFirestore db =FirebaseFirestore.getInstance();

        final DocumentReference RootRef =db.collection("Users").document(phone);
        RootRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        DocumentSnapshot snapshot =task.getResult();

                        Users usersData =snapshot.toObject(Users.class);
                        if(snapshot.exists())
                        {
                            if(decrypt(usersData.getPassword()).equals(password))
                            {
                                Toast.makeText(vendor_log_in.this,"تم الدخول ..",Toast.LENGTH_SHORT).show();
                                //loadingBar.dismiss();
                                Intent intent =new Intent(vendor_log_in.this,firstfun.class);
                                Prevalent.currentOnlineUsers = usersData;
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(vendor_log_in.this,"كلمة السر لهذا الرقم "+phone+" خاطئه !!!",Toast.LENGTH_SHORT).show();

                                loadingBar11.dismiss();

                            }

                        }
                        else {
                            Toast.makeText(vendor_log_in.this, "لا يوجد حساب بهذا الرقم " +phone, Toast.LENGTH_SHORT).show();
                            //loadingBar.dismiss();
                            loadingBar11.dismiss();
                        }

                    }

            }
        });


  /*      RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(ParentDbName).child(phone).exists())
                {
                    Users usersData = dataSnapshot.child(ParentDbName).child(phone).getValue(Users.class);
                    if(usersData.getPhone().equals(phone))
                    {
                        if(usersData.getPassword().equals(password))
                        {
                            Toast.makeText(vendor_log_in.this,"تم الدخول ..",Toast.LENGTH_SHORT).show();
                            //loadingBar.dismiss();
                            Intent intent =new Intent(vendor_log_in.this,firstfun.class);
                            Prevalent.currentOnlineUsers = usersData;
                            startActivity(intent);
                        }
                        else
                        {
                          //  loadingBar.dismiss();
                            Toast.makeText(vendor_log_in.this,"كلمة السر لهذا الرقم "+phone+" خاطئه !!!",Toast.LENGTH_SHORT).show();

                                loadingBar11.dismiss();
                        }
                    }
                }
                else
                {

                        Toast.makeText(vendor_log_in.this, "لا يوجد حساب بهذا الرقم " +phone, Toast.LENGTH_SHORT).show();
                         //loadingBar.dismiss();
                        loadingBar11.dismiss();



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */
    }
















    public void newUser(View view)
    {
        Intent go = new Intent(this,createNewUser.class);
        startActivity(go);

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
