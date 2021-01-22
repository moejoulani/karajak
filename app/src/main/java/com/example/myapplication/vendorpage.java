package com.example.myapplication;

import android.app.ProgressDialog;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Parts;
import com.example.myapplication.Model.RatingV;
import com.example.myapplication.Model.Users;
import com.example.myapplication.Prevalent.NUserPrevalent;
import com.example.myapplication.Prevalent.Prevalent;
import com.example.myapplication.ViewHolder.VendorViewHolder;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Query;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class vendorpage extends AppCompatActivity {
    ListView listView;
    ImageView back;
    CircleImageView circleImageView;
    TextView Vname, Vphone, Vaddress;
    TextView Close;
    RecyclerView VrecyclerView;
    RecyclerView.LayoutManager layoutManager;
    private Query query;
    private String saveCurrentDate,saveCurrentTime,rateRandomKey;
    private String phoneNumber;
    ProgressDialog loading;
    private RatingBar rt;
    private EditText comment;
    private ProgressDialog loading2;
    private Button submit;
    private int Rating=0;
    private TextView starsss;
    private RatingBar getRt;
    private String longitude ,ladtitude;
    private LinearLayout goToLocation ,callVend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendorpage);
        loading = new ProgressDialog(this);
        comment=(EditText)findViewById(R.id.comment);
        callVend=(LinearLayout)findViewById(R.id.callVend);
        goToLocation=(LinearLayout) findViewById(R.id.goToLoc);
        //starsss=(TextView)findViewById(R.id.startRatingGet);
        loading2=new ProgressDialog(this);
        submit=(Button)findViewById(R.id.comrat_submit);
        getRt=(RatingBar)findViewById(R.id.getStars);

        getRt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(vendorpage.this,reviewR.class);
            }
        });
        rt=(RatingBar)findViewById(R.id.rtt);
        rt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Rating = (int)rating;
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!(NUserPrevalent.isEmpty())) {
                   uploadRating();
               }
               else if(NUserPrevalent.isEmpty())
               {
                   finish();
                   Intent intent=new Intent(vendorpage.this,signinyoy.class);
                   startActivity(intent);
               }
            }
        });

      //  Toast.makeText(this,NUserPrevalent.currentOnlineNUsers.getNphone(),Toast.LENGTH_SHORT).show();
        circleImageView = (CircleImageView) findViewById(R.id.vendorImage);
        Vname = (TextView) findViewById(R.id.vendorName);
        Vphone = (TextView) findViewById(R.id.vendorPhone);
        Vaddress = (TextView) findViewById(R.id.vendoraddres);
        // listView =(ListView) findViewById(R.id.vendorlisto);
        phoneNumber = getIntent().getExtras().get("phonenumber").toString();
        callVend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });
        Close = (TextView) findViewById(R.id.vend_close_btn);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //LayoutInflater lf;
        //    View headerView;
        //  lf = this.getLayoutInflater();
        //      headerView =(View) lf.inflate(R.layout.vendorpagetop,null,false);
//        listView.addHeaderView(headerView,null,false);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference vendorRef = db.collection("Users").document(phoneNumber);
        vendorRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot snap = task.getResult();
                    if (snap.exists()) {
                        String image = (String) snap.get("image");
                        String name = (String) snap.get("name");
                        String phone = (String) snap.get("phone");
                        String address = (String) snap.get("address");
                         ladtitude = String.valueOf(snap.get("Ladtitude"));
                         longitude =String.valueOf(snap.get("Longitude"));
                        Toast.makeText(vendorpage.this,ladtitude+longitude,Toast.LENGTH_SHORT).show();
                     //   double stars = (Double)snap.get("avgRating");

                        if(snap.get("avgRating")!=null) {
                            double d =(Double)snap.get("avgRating");
                            float f =(float)d;
                            getRt.setRating(f);

                        }
                        else{
                            getRt.setRating(0);
                        }
                        getRt.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_UP) {
                                    Intent intent=new Intent(vendorpage.this,reviewR.class);
                                    intent.putExtra("phonenumber",phoneNumber);
                                    startActivity(intent);
                                }
                                return true;
                            }});
                       // float ss =(float)stars;
                        Picasso.get().load(image).into(circleImageView);
                        Vname.setText(name);
                        Vphone.setText(phone);
                        Vaddress.setText(address);
                        //starsss.setText(String.valueOf(stars));
                      //  Toast.makeText(vendorpage.this,"task is successfully >>"+snap.get("name").toString(),Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(vendorpage.this,"task is not exists >>",Toast.LENGTH_SHORT).show();


                    }
                }
                else{
                    Toast.makeText(vendorpage.this,"task is successfully >>",Toast.LENGTH_SHORT).show();

                }

            }

        });
        layoutManager = new LinearLayoutManager(this);
        VrecyclerView = findViewById(R.id.Reccno);
        VrecyclerView.setHasFixedSize(true);
        VrecyclerView.setLayoutManager(layoutManager);



        goToLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+ladtitude+","+longitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });



    /*    vendorRef = FirebaseDatabase.getInstance().getReference().child("Users").child(phoneNumber);
        vendorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    if(dataSnapshot.child("image").exists())
                    {
                        String image =dataSnapshot.child("image").getValue().toString();
                        String name  =dataSnapshot.child("name").getValue().toString();
                        String phone =dataSnapshot.child("phone").getValue().toString();
                        String address=dataSnapshot.child("address").getValue().toString();
                        Picasso.get().load(image).into(circleImageView);
                        Vname.setText(name);
                        Vphone.setText(phone);
                        Vaddress.setText(address);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        layoutManager = new LinearLayoutManager(this);
        VrecyclerView=findViewById(R.id.Reccno);
        VrecyclerView.setHasFixedSize(true);
        VrecyclerView.setLayoutManager(layoutManager);





*/


    }
    public void uploadRating()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate =new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        final SimpleDateFormat currentTime =new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime =currentTime.format(calendar.getTime());

        rateRandomKey =saveCurrentDate + saveCurrentTime;
        final String Comm=comment.getText().toString();

        if(TextUtils.isEmpty(Comm))
        {
            Toast.makeText(this,"You Must Write A comment To Vendor To Continue",Toast.LENGTH_SHORT).show();
        }
        else{
            loading2.setMessage("جار إضافة التعليق و التقييم");
            loading2.setCanceledOnTouchOutside(false);
            loading2.show();
         //   final FirebaseFirestore db= FirebaseFirestore.getInstance();
      //   final   DocumentReference reference=db.collection("Users").document(phoneNumber);

           // addRating(reference,Rating);
            final FirebaseFirestore db1 =FirebaseFirestore.getInstance();
            CollectionReference rateRef = db1.collection("Review");
            com.google.firebase.firestore.Query query=rateRef.whereEqualTo("RvendorPhone",phoneNumber).whereEqualTo("RnuserPhone",NUserPrevalent.currentOnlineNUsers.getNphone());
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                             if(task.isSuccessful())
                             {
                                Toast.makeText(vendorpage.this,"task successfull",Toast.LENGTH_SHORT).show();

                                            QuerySnapshot qsnap=task.getResult();
                                            if(qsnap.isEmpty()) {

                                                Toast.makeText(vendorpage.this, "مش موجود", Toast.LENGTH_SHORT).show();
                                                Map<String, Object> map = new HashMap<>();
                                                map.put("RvendorPhone", phoneNumber);
                                                map.put("RnuserPhone", NUserPrevalent.currentOnlineNUsers.getNphone());
                                                map.put("RnuserComment", Comm);
                                                map.put("RnuserRate", Rating);
                                                map.put("RnuserName",NUserPrevalent.currentOnlineNUsers.getNname());
                                                map.put("Rtime",saveCurrentDate+" "+saveCurrentTime );
                                                db1.collection("Review").document(rateRandomKey).set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        loading2.dismiss();
                                                        Toast.makeText(vendorpage.this, "تم التقييم بنجاح", Toast.LENGTH_SHORT).show();
                                                            final FirebaseFirestore db= FirebaseFirestore.getInstance();
                                                          final   DocumentReference reference=db.collection("Users").document(phoneNumber);

                                                         addRating(reference,Rating);
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        loading2.dismiss();
                                                        Toast.makeText(vendorpage.this, "num-1" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                            else{
                                                loading2.dismiss();
                                                Toast.makeText(vendorpage.this,"ما بتقدر تقيم",Toast.LENGTH_SHORT).show();
                                            }


                             }
                             else{
                                 loading2.dismiss();
                                 Toast.makeText(vendorpage.this,"task error",Toast.LENGTH_SHORT).show();
                             }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    loading2.dismiss();
                    Toast.makeText(vendorpage.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private Task<Void> addRating(final DocumentReference reference, final int rating)
    {
        final FirebaseFirestore db=FirebaseFirestore.getInstance();
      // reference=db.collection("Users").document(phoneNumber).collection("ratings").document(NUserPrevalent.currentOnlineNUsers.getNphone());

      return   db.runTransaction(new Transaction.Function<Void>() {
            @Nullable
            @Override
            public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                Users users=transaction.get(reference).toObject(Users.class);

              int newNumRatings=users.getNumRatings()+1;
         float oldRatingTotal =users.getAvgRating()*users.getNumRatings();
          float newAvgRating =(oldRatingTotal+rating)/newNumRatings;
              users.setNumRatings(newNumRatings);
            users.setAvgRating(newAvgRating);

                transaction.set(reference,users);
                Map<String,Object> data =new HashMap<>();
                data.put("rating",rating);
                //data.put("phoneRate",NUserPrevalent.currentOnlineNUsers.getNphone());
                transaction.set(reference,data,SetOptions.merge());
                return  null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                loading2.dismiss();
                Toast.makeText(vendorpage.this,"Successfully rating",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loading2.dismiss();
                Toast.makeText(vendorpage.this,"Failure rating : "+e.toString(),Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        FirebaseFirestore db =FirebaseFirestore.getInstance();
       final com.google.firebase.firestore.Query query =db.collection("Parts").whereEqualTo("VendorPhoneNumber",phoneNumber);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if(task.isSuccessful())
                {
                    loading.dismiss();
                    FirestoreRecyclerOptions<Parts>options=new FirestoreRecyclerOptions.Builder<Parts>().setQuery(query,Parts.class).build();
                    FirestoreRecyclerAdapter<Parts,VendorViewHolder> adapter=new FirestoreRecyclerAdapter<Parts, VendorViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull VendorViewHolder holder, int position, @NonNull Parts model) {
                            holder.Vpartname.setText(model.getpartname());
                            holder.Vcartype.setText(model.getCarType());
                            holder.Vcarseries.setText(model.getCarSeries());
                            holder.Vcarmodel.setText(model.getCarModel());
                            holder.Vprice.setText(String.valueOf(model.getPrice()));
                            Picasso.get().load(model.getImage()).into(holder.Vpartimage);

                        }

                        @NonNull
                        @Override
                        public VendorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.vendorspageparts,parent,false);
                            VendorViewHolder vendorViewHolder =new VendorViewHolder(view);
                            return vendorViewHolder;
                        }
                    };
                    VrecyclerView.setAdapter(adapter);
                    adapter.startListening();


                }

            }
        });

     /*   query=FirebaseDatabase.getInstance().getReference("Parts").orderByChild("VendorPhoneNumber").equalTo(phoneNumber);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    loading.dismiss();
                    final FirebaseRecyclerOptions<Parts> options =new FirebaseRecyclerOptions.Builder<Parts>().setQuery(query,Parts.class).build();

                    final FirebaseRecyclerAdapter<Parts, VendorViewHolder> adapter =new FirebaseRecyclerAdapter<Parts, VendorViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(VendorViewHolder holder, int position, Parts model) {
                             holder.Vpartname.setText(model.getpartname());
                            holder.Vcartype.setText(model.getCarType());
                            holder.Vcarseries.setText(model.getCarSeries());
                            holder.Vcarmodel.setText(model.getCarModel());
                            holder.Vprice.setText(model.getPrice());
                            Picasso.get().load(model.getImage()).into(holder.Vpartimage);


                        }


                        @Override
                        public VendorViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

                            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.vendorspageparts,parent,false);
                            VendorViewHolder vendorViewHolder =new VendorViewHolder(view);
                            return vendorViewHolder;


                        }
                    };
                    VrecyclerView.setAdapter(adapter);
                    adapter.startListening();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }
    */
    }
}