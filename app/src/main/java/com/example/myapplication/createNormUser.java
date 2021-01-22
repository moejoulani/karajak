package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class createNormUser extends AppCompatActivity {
    private EditText nameN,phoneN,passwordN,rePasswordN;
    private TextView closeN;
    private Button create_btn_N;
    private ProgressDialog loading;
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_norm_user);
        loading=new ProgressDialog(this);
        nameN=(EditText)findViewById(R.id.NORname);
        phoneN=(EditText)findViewById(R.id.NORphone);
        passwordN=(EditText)findViewById(R.id.NORpassword);
        rePasswordN=(EditText)findViewById(R.id.NORrepassword);
        create_btn_N=(Button)findViewById(R.id.create_btn_NN);
        closeN=(TextView) findViewById(R.id.close_btnNN);
        closeN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        create_btn_N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCheck();
            }
        });




    }
    public void createCheck()
    {
        String Nname=nameN.getText().toString();
        String Nphone=phoneN.getText().toString();
        String Npassword=passwordN.getText().toString();
        String Nrepassword=rePasswordN.getText().toString();


        if (TextUtils.isEmpty(Nname)) {
            Toast.makeText(this, "الرجاء ادخال الاسم.....", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Nphone)) {
            Toast.makeText(this, "الرجاء ادخال رقم الهاتف......", Toast.LENGTH_SHORT).show();

        }

        else if (TextUtils.isEmpty(Npassword) && TextUtils.isEmpty(Nrepassword)) {
            Toast.makeText(this, "ادخل كلمة السر.....", Toast.LENGTH_SHORT).show();
        } /*else if (password != RePass) {
                                Toast.makeText(this, "Enter The password corrcetly...", Toast.LENGTH_SHORT).show();
                            }*/
        else if(!Npassword.equals(Nrepassword))
        {
            Toast.makeText(this,"تأكد من اعادة كلمة السر ....",Toast.LENGTH_SHORT).show();
        }
        else if(Nphone.length()!=10)
        {
            Toast.makeText(this,"يجب ان يكون رقم الهاتف مكون من عشرة ارقام",Toast.LENGTH_SHORT).show();
        }
        else if(Npassword.length()<8)
        {
            Toast.makeText(this,"يجب ان تكون كلمة السر 8 خانات او اكثر",Toast.LENGTH_SHORT).show();
        }
        else if(Nphone.length()==10 && Npassword.length()>=8)
        {
            loading.setTitle("جار انشاء الحساب");
            loading.setMessage("الرجاء الأنتظار");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            ValidatePhoneNumber(Nname,Nphone,Npassword);
        }





    }
    public void ValidatePhoneNumber(final String name,final String phone,final String password)
    {

        final FirebaseFirestore db =FirebaseFirestore.getInstance();
        DocumentReference reference =db.collection("NUsers").document(phone);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        DocumentSnapshot snapshot =task.getResult();
                        if(snapshot.exists())
                        {
                            Toast.makeText(createNormUser.this,"هذا الرقم : "+phone+"مستحدم من قبل .",Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                            Toast.makeText(createNormUser.this,"الرجاء ادخال رقم اخر .",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Map<String,Object> map =new HashMap<>();
                            map.put("Nname",name);
                            map.put("Nphone",phone);
                            map.put("Npassword",encrypt(password));
                            db.collection("NUsers").document(phone).set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    loading.dismiss();
                                    Toast.makeText(createNormUser.this,"تم أنشاء الحساب بنجاح ",Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent intent=new Intent(createNormUser.this,signinyoy.class);
                                    startActivity(intent);
                                }
                            });


                        }
                    }
            }
        });



    }

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeToString(encrypted,1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



}
