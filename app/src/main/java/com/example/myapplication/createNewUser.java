package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;


import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class createNewUser extends AppCompatActivity {

    private Button createAccountButton;
    private EditText InputName, InputPhoneNumber, InputPassword, RePassword,InputAddress;
    private ProgressDialog loadingBar;
    private Spinner spAD;
    private String Address="";
    private String SelectedLOCATION;
    private Typeface tfavv;
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_create_new_user);
                        String addresses [] ={"أدخل موقعك","عمان","البيادر","ماركا","وادي الرمم","القويسمة","سحاب","الزرقاء","أربد","العقبة","طبربور","أخرى"};
                        createAccountButton = (Button) findViewById(R.id.createbtn);
                        InputName = (EditText) findViewById(R.id.name);
                        InputPhoneNumber = (EditText) findViewById(R.id.phone);
        //tfavv = Typeface.createFromAsset(getAssets(),"fonts/din.ttf");
                        InputPassword = (EditText) findViewById(R.id.password);
                        RePassword = (EditText) findViewById(R.id.repass);
                       // InputAddress =(EditText) findViewById(R.id.ent_address);
                        spAD=(Spinner) findViewById(R.id.spAD);


                        ArrayAdapter<String> ar =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,addresses)
                        {
                            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                                tfavv = Typeface.createFromAsset(getAssets(),"fonts/din.ttf");

                                TextView v = (TextView) super.getView(position, convertView, parent);
                                v.setTypeface(tfavv);
                               v.setTextColor(Color.WHITE);
                               v.setGravity(View.TEXT_ALIGNMENT_CENTER);
                                v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                               // v.setPadding(5,5,5,5);
                                //v.setTextSize(35);
                                return v;
                            }

                            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                                TextView v = (TextView) super.getView(position, convertView, parent);
                                v.setTypeface(tfavv);
                                v.setTextColor(Color.WHITE);
                                v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                               // v.setTextSize(35);
                                return v;
                            }
                        };
                        //ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,addresses);
                        spAD.setAdapter(ar);
        if (!(spAD.isSelected()))
        {
            spAD.setSelection(0);
        }

                       /* spAD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                TextView tv =(TextView)view;
                                    if(position==0)
                                    {
                                        tv.setTextColor(Color.GRAY);
                                    }
                                    else{
                                        tv.setTextColor(Color.BLACK);
                                    }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        */

        spAD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Address = "";
                } else if (position > 0) {
                    Address = spAD.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
                        loadingBar = new ProgressDialog(this);
                        createAccountButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                createAccount();
                            }
                        });

    }

    public void createAccount() {
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();
        String RePass = RePassword.getText().toString();



                            if (TextUtils.isEmpty(name)) {
                                Toast.makeText(this, "الرجاء ادخال الاسم.....", Toast.LENGTH_SHORT).show();
                            } else if (TextUtils.isEmpty(phone)) {
                                Toast.makeText(this, "الرجاء ادخال رقم الهاتف......", Toast.LENGTH_SHORT).show();

                            }
                            else if(TextUtils.isEmpty(Address))
                            {
                                Toast.makeText(this,"يجب ادخال الموقع ...",Toast.LENGTH_SHORT).show();
                            }
                            else if (TextUtils.isEmpty(password) && TextUtils.isEmpty(RePass)) {
                                Toast.makeText(this, "ادخل كلمة السر.....", Toast.LENGTH_SHORT).show();
                            } /*else if (password != RePass) {
                                Toast.makeText(this, "Enter The password corrcetly...", Toast.LENGTH_SHORT).show();
                            }*/
                            else if(!password.equals(RePass))
                            {
                                Toast.makeText(this,"تأكد من اعادة كلمة السر ....",Toast.LENGTH_SHORT).show();
                            }
                           else if(phone.length()!=10)
                            {
                                Toast.makeText(this,"يجب ان يكون رقم الهاتف مكون من عشرة ارقام",Toast.LENGTH_SHORT).show();
                            }
                            else if(password.length()<8)
                            {
                                Toast.makeText(this,"يجب ان تكون كلمة السر 8 خانات او اكثر",Toast.LENGTH_SHORT).show();
                            }
                            else if(phone.length()==10 && password.length()>=8)
                            {
                                loadingBar.setTitle("Creat Account");
                                loadingBar.setMessage("please wait");
                                loadingBar.setCanceledOnTouchOutside(false);
                                loadingBar.show();
                                ValidatePhoneNumber(name,phone,password,Address);
                            }
     //   Toast.makeText(createNewUser.this,Address,Toast.LENGTH_SHORT).show();
        }

        public void ValidatePhoneNumber(final String name, final String phone, final String password,final String Address)
        {


            final FirebaseFirestore db =FirebaseFirestore.getInstance();
            DocumentReference reference =db.collection("Users").document(phone);
            reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                                DocumentSnapshot documentSnapshot =task.getResult();
                                    if(documentSnapshot.exists())
                                    {
                                        Toast.makeText(createNewUser.this,"هذا الرقم : "+phone+"مستحدم من قبل .",Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Toast.makeText(createNewUser.this,"الرجاء ادخال رقم اخر .",Toast.LENGTH_SHORT).show();


                                    }
                                    else{
                                        Map<String,Object> userdataMap =new HashMap<>();
                                        userdataMap.put("phone",phone);
                                        userdataMap.put("name",name);
                                        userdataMap.put("password",encrypt(password));
                                        userdataMap.put("address", Address);
                                        db.collection("Users").document(phone).set(userdataMap, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(createNewUser.this, "تم أنشاء الحساب بنجاح .. ", Toast.LENGTH_SHORT).show();
                                                loadingBar.dismiss();
                                                Intent intent = new Intent(createNewUser.this,vendor_log_in.class);
                                                startActivity(intent);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                loadingBar.dismiss();
                                                Toast.makeText(createNewUser.this,"NetWork Error ...Please Try Again Later .",Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                    }
                        }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });











            /*    final DatabaseReference RootRef ;
                RootRef = FirebaseDatabase.getInstance().getReference();
                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!(dataSnapshot.child("Users").child(phone).exists()))
                        {
                            HashMap<String,Object>  userdataMap =new HashMap<>();
                            userdataMap.put("phone",phone);
                            userdataMap.put("name",name);
                            userdataMap.put("password",password);
                            userdataMap.put("address", Address);

                            RootRef.child("Users").child(phone).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(createNewUser.this, "تم أنشاء الحساب بنجاح .. ", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(createNewUser.this,vendor_log_in.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        loadingBar.dismiss();
                                        Toast.makeText(createNewUser.this,"NetWork Error ...Please Try Again Later .",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                        }
                       else
                        {
                            Toast.makeText(createNewUser.this,"هذا الرقم : "+phone+"مستحدم من قبل .",Toast.LENGTH_SHORT).show();
                             loadingBar.dismiss();
                             Toast.makeText(createNewUser.this,"الرجاء ادخال رقم اخر .",Toast.LENGTH_SHORT).show();
                                 Intent intent =new Intent(createNewUser.this,page2.class);
                                    startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                */
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

