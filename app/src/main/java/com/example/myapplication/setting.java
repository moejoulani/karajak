 package com.example.myapplication;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.Prevalent.Prevalent;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/* public class setting extends AppCompatActivity {
        private CircleImageView profileImageView;
        private TextView profileChangeTextbtn ,closeTextbtn,saveTextButton;
        private Uri imageUri;
        private String myUri = "";
        private StorageReference storageProfilePictureRef;
        private String checker = "";
        private StorageTask uploadTask;
     private EditText fullNameEditText, userPhoneEditText, addressEditText;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

   storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("profile pictures");

        profileImageView = (CircleImageView) findViewById(R.id.setting_profile_image);
        profileChangeTextbtn = (TextView) findViewById(R.id.profile_image_change_btn);
        closeTextbtn = (TextView) findViewById(R.id.close_btn);
        saveTextButton = (TextView) findViewById(R.id.update_btn);
        fullNameEditText = (EditText) findViewById(R.id.settings_full_name);
        userPhoneEditText = (EditText) findViewById(R.id.settings_phone_number);
        addressEditText = (EditText) findViewById(R.id.settings_address);

        userInfoDisplay(profileImageView,fullNameEditText,userPhoneEditText,addressEditText);

        closeTextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checker.equals("clicked"))
                {
                            userInfoSaved();
                }

            }
        });

        profileChangeTextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker = "clicked";
                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(setting.this);


            }
        });

    }
    public void updateOnlyUserInfo()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

        HashMap<String, Object> userMap = new HashMap<>();
        userMap. put("name", fullNameEditText.getText().toString());
        userMap. put("address", addressEditText.getText().toString());
        userMap. put("phoneOrder", userPhoneEditText.getText().toString());
        ref.child(Prevalent.currentOnlineUsers.getPhone()).updateChildren(userMap);

        startActivity(new Intent(setting.this, MainActivity.class));
        Toast.makeText(setting.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
        finish();
    }
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data)
     {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE  &&  resultCode==RESULT_OK  &&  data!=null)
         {
             CropImage.ActivityResult result = CropImage.getActivityResult(data);
             imageUri = result.getUri();

             profileImageView.setImageURI(imageUri);
         }
         else
         {
             Toast.makeText(this, "Error, Try Again.", Toast.LENGTH_SHORT).show();

             startActivity(new Intent(setting.this, setting.class));
             finish();
         }
     }




     /*@Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
          if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
          {
              CropImage.ActivityResult result =CropImage.getActivityResult(data);
              imageUri = result.getUri();

              profileImageView.setImageURI(imageUri);

          }
          else
          {
              Toast.makeText(this,"Error , Try Again .",Toast.LENGTH_SHORT).show();
              startActivity(new Intent(setting.this,setting.class));
              finish();
          }
     }

     public void userInfoSaved()
    {
        if (TextUtils.isEmpty(fullNameEditText.getText().toString()))
        {
            Toast.makeText(this, "Name is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Name is address.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(userPhoneEditText.getText().toString()))
        {
            Toast.makeText(this, "Name is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if(checker.equals("clicked"))
        {
            uploadImage();
        }

    }
    public void uploadImage()
    {

        if(imageUri != null)
        {

            final StorageReference fileRef = storageProfilePictureRef.child(Prevalent.currentOnlineUsers.getPhone() + ".jpg");

            uploadTask = fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }

                    return fileRef.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task)
                        {
                            if (task.isSuccessful())
                            {
                                Uri downloadUrl = task.getResult();
                                myUri = downloadUrl.toString();

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

                                HashMap<String, Object> userMap = new HashMap<>();
                                userMap.put("name", fullNameEditText.getText().toString());
                                userMap. put("address", addressEditText.getText().toString());
                                userMap. put("phoneOrder", userPhoneEditText.getText().toString());
                                userMap. put("image", myUri);
                                ref.child(Prevalent.currentOnlineUsers.getPhone()).updateChildren(userMap);



                                startActivity(new Intent(setting.this, MainActivity.class));
                                Toast.makeText(setting.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {

                                Toast.makeText(setting.this, "Error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(this, "image is not selected.", Toast.LENGTH_SHORT).show();
        }
        }
         /*   final StorageReference fileRef =storageProfilePictureRef
                    .child(Prevalent.currentOnlineUsers.getPhone() + ".jpg");

            uploadTask = fileRef.putFile(imageUri);

         uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful())
                    {
                      throw task.getException();

                    }


                    return fileRef.getDownloadUrl();


                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful())
                    {

                            Uri downloadUri = task.getResult();
                            myUri = downloadUri.toString();

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");


                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap. put("name", fullNameEditText.getText().toString());
                        userMap. put("address", addressEditText.getText().toString());
                        userMap. put("phoneOrder", userPhoneEditText.getText().toString());
                        userMap. put("image", myUri);
                        ref.child(Prevalent.currentOnlineUsers.getPhone()).updateChildren(userMap);

                            startActivity(new Intent(setting.this, page2.class));
                            Toast.makeText(setting.this, "Profile Image Update Successfully", Toast.LENGTH_SHORT).show();
                            finish();



                    }
                    else
                    {

                           Toast.makeText(setting.this, "Eroor", Toast.LENGTH_SHORT).show();


                    }

                }
            });
        }
        else
        {
            Toast.makeText(this,"Image Dose Not Selected !!",Toast.LENGTH_SHORT).show();

        }
    }
    public void userInfoDisplay(final CircleImageView profileImageViewfinal, final EditText fullNameEditText, final EditText userPhoneEditText, final EditText addressEditText)
    {
        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUsers.getPhone());

        UsersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    if(dataSnapshot.child("image").exists())
                    {
                        String image = dataSnapshot.child("image").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String phone = dataSnapshot.child("phone").getValue().toString();
                        String address = dataSnapshot.child("address").getValue().toString();
                       // String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImageView);
                        fullNameEditText.setText(name);
                        userPhoneEditText.setText(phone);
                        addressEditText.setText(address);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

 */

 public class setting extends AppCompatActivity
 {
     private static final int MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 1;
     private CircleImageView profileImageView;
     private EditText fullNameEditText, userPhoneEditText, addressEditText;
     private TextView profileChangeTextBtn,  closeTextBtn, saveTextButton;
    private Spinner spinner;
     private Uri imageUri;
     private String myUrl = "";
     private StorageTask uploadTask;
     private StorageReference storageProfilePrictureRef;
     private String checker = "";
    private ProgressDialog loading;
    private String LocationSp;
    private Button FetchLO;
     private FusedLocationProviderClient fusedLocationClient;

     @Override
     protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_setting);

         FetchLO =(Button)findViewById(R.id.fetchLocation);
         fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
         spinner = (Spinner) findViewById(R.id.spinnerAddress);
         String addresses [] ={"","عمان","البيادر","ماركا","وادي الرمم","القويسمة","سحاب","الزرقاء","أربد","العقبة","طبربور"};
         ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,addresses);
         spinner.setAdapter(adapter);




          LocationSp=  spinner.getSelectedItem().toString();

         Toast.makeText(this,LocationSp,Toast.LENGTH_SHORT).show();
         storageProfilePrictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");
         loading =new ProgressDialog(this);
         profileImageView = (CircleImageView) findViewById(R.id.setting_profile_image);
         fullNameEditText = (EditText) findViewById(R.id.settings_full_name);
         userPhoneEditText = (EditText) findViewById(R.id.settings_phone_number);
         addressEditText = (EditText) findViewById(R.id.settings_address);
         profileChangeTextBtn = (TextView) findViewById(R.id.profile_image_change_btn);
         closeTextBtn = (TextView) findViewById(R.id.close_btn);
         saveTextButton = (TextView) findViewById(R.id.update_btn);

         userInfoDisplay(profileImageView, fullNameEditText, userPhoneEditText, addressEditText);

        FetchLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLocation();
            }
        });
         closeTextBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             {
                 finish();
             }
         });


         saveTextButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             {
                 if (checker.equals("clicked"))
                 {
                     userInfoSaved();
                 }
                 else
                 {
                     updateOnlyUserInfo();
                 }
             }
         });


         profileChangeTextBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             {
                 checker = "clicked";

                 CropImage.activity(imageUri)
                         .setAspectRatio(1, 1)
                         .start(setting.this);
             }
         });
     }




     private void updateOnlyUserInfo()
     {
            Toast.makeText(this,LocationSp,Toast.LENGTH_SHORT).show();
         loading.setTitle("تحديث الحساب ");

         loading.setMessage("يرجى الأنتظار...");

         loading.setCanceledOnTouchOutside(false);

         loading.show();

       final   FirebaseFirestore db =FirebaseFirestore.getInstance();
         DocumentReference ref =db.collection("Users").document(Prevalent.currentOnlineUsers.getPhone());
         Map<String, Object> userMap = new HashMap<>();

         userMap.put("name", fullNameEditText.getText().toString());

         userMap.put("address", LocationSp);

         userMap.put("phoneOrder", userPhoneEditText.getText().toString());
         ref.update(userMap);


         startActivity(new Intent(setting.this, firstfun.class));

         Toast.makeText(setting.this, "تم تحديث بيانات الحساب .", Toast.LENGTH_SHORT).show();

         finish();

   /*      DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

         HashMap<String, Object> userMap = new HashMap<>();

         userMap. put("name", fullNameEditText.getText().toString());

         userMap. put("address", LocationSp);

         userMap. put("phoneOrder", userPhoneEditText.getText().toString());

         ref.child(Prevalent.currentOnlineUsers.getPhone()).updateChildren(userMap);

         startActivity(new Intent(setting.this, firstfun.class));

         Toast.makeText(setting.this, "تم تحديث بيانات الحساب.", Toast.LENGTH_SHORT).show();

         finish();
         */

     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data)
     {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE  &&  resultCode==RESULT_OK  &&  data!=null)
         {
             CropImage.ActivityResult result = CropImage.getActivityResult(data);
             imageUri = result.getUri();
             profileImageView.setImageURI(imageUri);

         }
         else
         {
             Toast.makeText(this, "Error, Try Again.", Toast.LENGTH_SHORT).show();
                loading.dismiss();
             startActivity(new Intent(setting.this, setting.class));
             finish();
         }
     }




     private void userInfoSaved()
     {
         if (TextUtils.isEmpty(fullNameEditText.getText().toString()))
         {
             Toast.makeText(this, "الاسم مطلوب.", Toast.LENGTH_SHORT).show();
             loading.dismiss();
         }

         else if (TextUtils.isEmpty(userPhoneEditText.getText().toString()))
         {
             Toast.makeText(this, "الرقم مطلوب.", Toast.LENGTH_SHORT).show();
             loading.dismiss();
         }
         else if(checker.equals("clicked"))
         {
             uploadImage();
             loading.dismiss();
         }
     }



     private void uploadImage()
     {


         if (imageUri != null)
         {
             final StorageReference fileRef = storageProfilePrictureRef
                     .child(Prevalent.currentOnlineUsers.getPhone() + ".jpg");

             uploadTask = fileRef.putFile(imageUri);

             uploadTask.continueWithTask(new Continuation() {
                 @Override
                 public Object then( Task task) throws Exception
                 {
                     if (!task.isSuccessful())
                     {
                         throw task.getException();
                     }

                     return fileRef.getDownloadUrl();
                 }
             })
                     .addOnCompleteListener(new OnCompleteListener<Uri>() {
                         @Override
                         public void onComplete( Task<Uri> task)
                         {
                             if (task.isSuccessful())
                             {
                                 Uri downloadUrl = task.getResult();
                                 myUrl = downloadUrl.toString();

                               //  DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
                                    FirebaseFirestore db =FirebaseFirestore.getInstance();
                                    DocumentReference ref =db.collection("Users").document(Prevalent.currentOnlineUsers.getPhone());
                                 HashMap<String, Object> userMap = new HashMap<>();
                                 userMap. put("name", fullNameEditText.getText().toString());
                                 userMap. put("address", LocationSp);
                                 userMap. put("phoneOrder", userPhoneEditText.getText().toString());
                                 userMap. put("image", myUrl);
                                // ref.child(Prevalent.currentOnlineUsers.getPhone()).updateChildren(userMap);
                                ref.update(userMap);
                                startActivity(new Intent(setting.this, firstfun.class));
                               //  Toast.makeText(setting.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
                                 finish();
                             }
                             else
                             {

                                 Toast.makeText(setting.this, "Error.", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
         }
         else
         {
             Toast.makeText(this, "image is not selected.", Toast.LENGTH_SHORT).show();
         }
     }


     private void userInfoDisplay(final CircleImageView profileImageView, final EditText fullNameEditText, final EditText userPhoneEditText, final EditText addressEditText)
     {
         FirebaseFirestore db =FirebaseFirestore.getInstance();
         DocumentReference reference =db.collection("Users").document(Prevalent.currentOnlineUsers.getPhone());
         reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                 if(task.isSuccessful())
                 {
                        DocumentSnapshot snap =task.getResult();
                        if(snap.exists())
                        {
                            String image = (String)snap.get("image");
                            String name = (String)snap.get("name");
                            String phone = (String)snap.get("phone");
                            String address = (String)snap.get("address");
                            Picasso.get().load(image).into(profileImageView);
                            fullNameEditText.setText(name);
                            userPhoneEditText.setText(phone);
                            addressEditText.setText(address);
                            spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        /*
                        String username = (String) docuemnt.get("username");  //if the field is String
                             Boolean b = (Boolean) document.get("isPublic");       //if the field is Boolean
                           Integer i = (Integer) document.get("age")

                       */
                        }
                 }
             }
         });










       /*  DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUsers.getPhone());

         UsersRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot)
             {
                 if (dataSnapshot.exists())
                 {
                     if (dataSnapshot.child("image").exists())
                     {
                         String image = dataSnapshot.child("image").getValue().toString();
                         String name = dataSnapshot.child("name").getValue().toString();
                         String phone = dataSnapshot.child("phone").getValue().toString();
                         String address = dataSnapshot.child("address").getValue().toString();
                         Picasso.get().load(image).into(profileImageView);
                         fullNameEditText.setText(name);
                         userPhoneEditText.setText(phone);
                         addressEditText.setText(address);
                     }
                 }
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
            */
     }
     public void fetchLocation()
     {
         if (ContextCompat.checkSelfPermission(setting.this,
                 Manifest.permission.ACCESS_COARSE_LOCATION)
                 != PackageManager.PERMISSION_GRANTED) {

             // Permission is not granted
             // Should we show an explanation?
             if (ActivityCompat.shouldShowRequestPermissionRationale(setting.this,
                     Manifest.permission.ACCESS_COARSE_LOCATION)) {
                 new AlertDialog.Builder(this)
                         .setTitle("Required Location Permission")
                         .setMessage("You Have To Give The Permission To Access The Feature")
                         .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 ActivityCompat.requestPermissions(setting.this,
                                         new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                         MY_PERMISSIONS_ACCESS_COARSE_LOCATION);
                             }
                         }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                     }
                 }).create()
                         .show();
                 // Show an explanation to the user *asynchronously* -- don't block
                 // this thread waiting for the user's response! After the user
                 // sees the explanation, try again to request the permission.
             } else {
                 // No explanation needed; request the permission
                 ActivityCompat.requestPermissions(setting.this,
                         new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                         MY_PERMISSIONS_ACCESS_COARSE_LOCATION);

                 // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                 // app-defined int constant. The callback method gets the
                 // result of the request.
             }
         } else {
             // Permission has already been granted
             Toast.makeText(setting.this,"FUESD LOCATION CLIENT",Toast.LENGTH_SHORT).show();
             fusedLocationClient.getLastLocation()
                     .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                         @Override
                         public void onSuccess(Location location) {
                             // Got last known location. In some rare situations this can be null.
                             if (location != null) {
                                 // Logic to handle location object
                                 double latitude = location.getLatitude();
                                 double longitude =location.getLongitude();
                                 Toast.makeText(setting.this,"Latitude : "+latitude+"Longitude : "+longitude,Toast.LENGTH_SHORT).show();
                                 uploadMyLocation(latitude,longitude);

                             }
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     Toast.makeText(setting.this,"Failure Listener ",Toast.LENGTH_SHORT).show();
                 }
             });

         }
     }

     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                if(requestCode == MY_PERMISSIONS_ACCESS_COARSE_LOCATION)
                {
                    if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(setting.this,"permission granted",Toast.LENGTH_SHORT).show();
                    }
                    else{

                    }
                }
     }
     public void uploadMyLocation(final Double latitude, final Double longitude)
     {
         final ProgressDialog lod=new ProgressDialog(setting.this);
         lod.setMessage("جار ألتقاط موقعك ....");
         lod.setCanceledOnTouchOutside(false);
         lod.show();
        final FirebaseFirestore db =FirebaseFirestore.getInstance();
         DocumentReference reference =db.collection("Users").document(Prevalent.currentOnlineUsers.getPhone());
         reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                 if(task.isSuccessful())
                 {
                     Map<String,Double> map =new HashMap<>();
                     map.put("Ladtitude",latitude);
                     map.put("Longitude",longitude);
                     db.collection("Users").document(Prevalent.currentOnlineUsers.getPhone()).set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             lod.dismiss();
                             Toast.makeText(setting.this,"تم أخذ موقعك الحالي ...",Toast.LENGTH_SHORT).show();
                         }
                     });
                 }
             }
         });
     }
 }
