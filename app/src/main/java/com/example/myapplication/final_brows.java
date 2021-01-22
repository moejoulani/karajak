package com.example.myapplication;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Path;
import android.media.Image;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Parts;
import com.example.myapplication.ViewHolder.ProductViewHolder;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class final_brows extends AppCompatActivity {
        //    DatabaseReference PartsRef;
            private RecyclerView recyclerView;
            RecyclerView.LayoutManager layoutManager;
            private static final String TAG ="FUCKING MESSAGE ->";
            ProgressDialog loading;
            private TextView fol1,fol2;
   private float numst;
            private ImageView emo;
            //private Query query;
            private String Category,Selec2,Selec3,Selec4,Selec5,CODE_RECEIVE;
            private String fieldSEARCH,locationSS;
            private Query myQuery;
    int CODE_RECEIVE_INT;
    String SEARCHGO;
    LinearLayout layout;
    PopupWindow popUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_brows);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        emo=(ImageView) findViewById(R.id.filterico);
        fol1 =(TextView) findViewById(R.id.fol1);
        fol2 =(TextView) findViewById(R.id.fol2);
     //   PartsRef = FirebaseDatabase.getInstance().getReference().child("Parts");
        recyclerView = findViewById(R.id.recycleview);
        loading =new ProgressDialog(this);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        Category =getIntent().getExtras().get("Category").toString();
        Selec2   =getIntent().getExtras().get("Selec2").toString();
        Selec3   =getIntent().getExtras().get("Selec3").toString();
        Selec4   =getIntent().getExtras().get("Selec4").toString();
        Selec5   =getIntent().getExtras().get("Selec5").toString();
        CODE_RECEIVE =getIntent().getExtras().get("code").toString();
        fieldSEARCH=getIntent().getExtras().get("field").toString();
        Toast.makeText(this,fieldSEARCH,Toast.LENGTH_SHORT).show();
        SEARCHGO="";
//        locationSS=getIntent().getExtras().get("location").toString();

         CODE_RECEIVE_INT =Integer.parseInt(CODE_RECEIVE);
        Toast.makeText(this,"The Code is "+CODE_RECEIVE,Toast.LENGTH_SHORT).show();

        //Toast.makeText(this,"Category"+Category+"  Selec2"+Selec2+"  Selec3"+Selec3+"  Selec4"+Selec4+"  Selec5"+Selec5,Toast.LENGTH_LONG).show();
        emo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(final_brows.this,sorting.class);
                intent.putExtra("Category", Category);
                intent.putExtra("Selec2", Selec2);
                intent.putExtra("Selec3", Selec3);
                intent.putExtra("Selec4", Selec4);
                intent.putExtra("Selec5", Selec5);
                finish();
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loading.setTitle("جار البحث عن القطعة");
        loading.setMessage("جار البحث عن "+Selec2+" يرجى الأنتظار ....");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        FirebaseFirestore db1 =FirebaseFirestore.getInstance();
        CollectionReference Creference =db1.collection("Parts");
        if(CODE_RECEIVE_INT == 1)
        {
         //myQuery=db1.collection("Parts").whereEqualTo("Category_Section_CarType_CarSeries_CarModel",Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5).orderBy("price");
           myQuery=Creference.whereEqualTo("Category_Section_CarType_CarSeries_CarModel",Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5).orderBy("price", Query.Direction.ASCENDING);
            Toast.makeText(final_brows.this,"الكود رقم 1",Toast.LENGTH_SHORT).show();
        }
        else if(CODE_RECEIVE_INT==3)
        {
            // Query queryRef = firebase.orderByKey().startAt("#" + mHashTag).endAt("#" + mHashTag + "\uf8ff");
            // myQuery=db1.collection("Parts").whereEqualTo("Category_Section_CarType_CarSeries_CarModel",Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5);
            //myQuery=db1.collection("Parts").document().orderBy(Category).startAt("\uf8ff"+fieldSEARCH).endAt (fieldSEARCH+"\uf8ff");
            Toast.makeText(final_brows.this,fieldSEARCH,Toast.LENGTH_SHORT).show();

            myQuery=db1.collection("Parts").whereGreaterThanOrEqualTo("Section",fieldSEARCH).whereLessThanOrEqualTo("Section",fieldSEARCH+"\uf8ff");

        }
        else if(Selec4.equals("") && Selec5.equals(""))
        {
            myQuery=db1.collection("Parts").whereEqualTo("Category",Category).whereEqualTo("Section",Selec2);
        }
         else if(CODE_RECEIVE_INT==0){

            // query = FirebaseDatabase.getInstance().getReference("Parts").orderByChild("Category_Section_CarType_CarSeries_CarModel").equalTo(Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5);
             fol1.setText(Category);
             fol2.setText(Selec2);
             myQuery=db1.collection("Parts").whereEqualTo("Category_Section_CarType_CarSeries_CarModel",Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5);


        }
         else if(CODE_RECEIVE_INT==2)
        {
            myQuery=db1.collection("Parts").whereEqualTo("Category_Section_CarType_CarSeries_CarModel",Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5).orderBy("price", Query.Direction.DESCENDING);

        }

         else if(CODE_RECEIVE_INT==45)
        {
            String location=getIntent().getExtras().get("location").toString();
            myQuery=db1.collection("Parts").whereEqualTo("Category_Section_CarType_CarSeries_CarModel",Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5).whereEqualTo("VendorAddress",location);
            Toast.makeText(this,location+"__"+CODE_RECEIVE_INT,Toast.LENGTH_SHORT).show();

        }
         else if(CODE_RECEIVE_INT==97)
        {
            String location=getIntent().getExtras().get("location").toString();
            myQuery=db1.collection("Parts").whereEqualTo("Category_Section_CarType_CarSeries_CarModel",Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5).whereEqualTo("VendorAddress",location).orderBy("price",Query.Direction.ASCENDING);
            Toast.makeText(this,location,Toast.LENGTH_SHORT).show();

        }
         else if(CODE_RECEIVE_INT==102)
        {
            String location=getIntent().getExtras().get("location").toString();
            myQuery=db1.collection("Parts").whereEqualTo("Category_Section_CarType_CarSeries_CarModel",Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5).whereEqualTo("VendorAddress",location).orderBy("price", Query.Direction.DESCENDING);
            Toast.makeText(this,location,Toast.LENGTH_SHORT).show();
        }
         FirebaseFirestore db =FirebaseFirestore.getInstance();

   // myQuery =db.collection("Parts").whereEqualTo("Category_Section_CarType_CarSeries_CarModel",Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5);
        myQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                  //  loading.dismiss();

                            if(task.getResult().size()>0)
                            {
                                loading.dismiss();

                                final FirestoreRecyclerOptions<Parts> options=new FirestoreRecyclerOptions.Builder<Parts>().setQuery(myQuery,Parts.class).build();
                                final FirestoreRecyclerAdapter<Parts,ProductViewHolder> adapter=new FirestoreRecyclerAdapter<Parts, ProductViewHolder>(options) {
                                    @Override
                                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Parts model) {

                                        FirebaseFirestore db45 =FirebaseFirestore.getInstance();
                                        DocumentReference reference =db45.collection("Users").document(model.getVendorPhoneNumber());
                                       reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                           @Override
                                           public void onSuccess(DocumentSnapshot documentSnapshot) {
                                           if(documentSnapshot!=null) {
                                            if(documentSnapshot.get("avgRating")!=null) {
                                                double d = (Double) documentSnapshot.get("avgRating");
                                                float f = (float) d;
                                                numst = f;
                                            }
                                            else{
                                                numst=0;
                                            }
                                           }
                                           }
                                       }).addOnFailureListener(new OnFailureListener() {
                                           @Override
                                           public void onFailure(@NonNull Exception e) {
                                               Toast.makeText(final_brows.this,e.toString(),Toast.LENGTH_SHORT).show();
                                           }
                                       });
                                     /*   reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful())
                                                {

                                                    DocumentSnapshot snap =task.getResult();
                                                    if(snap!=null)
                                                    {
                                                        if(snap.get("avgRating")!=null) {
                                                            double d = (Double) snap.get("avgRating");
                                                            float f = (float) d;
                                                            numst = f;
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                        */
                                        holder.txtProductName.setText(model.getpartname());
                                        holder.txtProductDescription.setText(model.getDescription());
                                        holder.txtVendorName.setText(model.getVendor());
                                        holder.txtVendorNum.setText(model.getVendorPhoneNumber());
                                      //  holder.rate.setRating(numst);
                                        holder.txtTime.setText(model.getPid());
                                        holder.txtPrice.setText(String.valueOf(model.getPrice()));
                                        Toast.makeText(final_brows.this,String.valueOf(model.getPrice()),Toast.LENGTH_SHORT).show();
                                        holder.txtVendorAddress.setText(model.getVendorAddress());
                                        Picasso.get().load(model.getImage()).into(holder.imageView);

                                        final String phoneNumber =model.getVendorPhoneNumber();
                                        holder.txtVendorName.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(final_brows.this,"Clicked on vendor name",Toast.LENGTH_SHORT).show();
                                                Intent intent =new Intent(final_brows.this,vendorpage.class);
                                                intent.putExtra("phonenumber",phoneNumber);
                                                startActivity(intent);
                                            }
                                        });



                                    }

                                    @NonNull
                                    @Override
                                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_parts_items, parent, false);
                                        ProductViewHolder holder = new ProductViewHolder(view);
                                        return holder;
                                    }
                                };
                                recyclerView.setAdapter(adapter);
                                adapter.startListening();
                            }
                            else{
                                loading.dismiss();
                                Intent intent =new Intent(final_brows.this,emptyquery.class);
                                startActivity(intent);
                                finish();

                            }



                }
                else{
                    Toast.makeText(final_brows.this,"Errorr loading price",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               Log.d(TAG,e.toString());
               Toast.makeText(final_brows.this,"TAGO",Toast.LENGTH_SHORT).show();
            }
        });
     /*    final FirestoreRecyclerOptions<Parts> options=new FirestoreRecyclerOptions.Builder<Parts>().setQuery(query,Parts.class).build();
         final FirestoreRecyclerAdapter<Parts,ProductViewHolder> adapter=new FirestoreRecyclerAdapter<Parts, ProductViewHolder>(options) {
             @Override
             protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Parts model) {

                 holder.txtProductName.setText(model.getpartname());
                 holder.txtProductDescription.setText(model.getDescription());
                 holder.txtVendorName.setText(model.getVendor());
                 holder.txtVendorNum.setText(model.getVendorPhoneNumber());
                 holder.txtTime.setText(model.getPid());
                 holder.txtPrice.setText(String.valueOf(model.getPrice()));
                 Toast.makeText(final_brows.this,String.valueOf(model.getPrice()),Toast.LENGTH_SHORT).show();
                 holder.txtVendorAddress.setText(model.getVendorAddress());
                 Picasso.get().load(model.getImage()).into(holder.imageView);
                 final String phoneNumber =model.getVendorPhoneNumber();
                 holder.txtVendorName.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Toast.makeText(final_brows.this,"Clicked on vendor name",Toast.LENGTH_SHORT).show();
                         Intent intent =new Intent(final_brows.this,vendorpage.class);
                         intent.putExtra("phonenumber",phoneNumber);
                         startActivity(intent);
                     }
                 });



             }

             @NonNull
             @Override
             public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_parts_items, parent, false);
                 ProductViewHolder holder = new ProductViewHolder(view);
                 return holder;
             }
         };
                recyclerView.setAdapter(adapter);
        adapter.startListening();



*/




/*       final FirebaseRecyclerOptions<Parts> options = new FirebaseRecyclerOptions.Builder<Parts>().setQuery(query, Parts.class).build();


        final FirebaseRecyclerAdapter<Parts, ProductViewHolder> adapter =new FirebaseRecyclerAdapter<Parts, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(ProductViewHolder holder, int position,Parts model) {
                if(model.getTime().length()==0 || options.toString().length()==0)
                {
                    holder.txtProductName.setText("لا يوووجد..................");
                    holder.txtProductDescription.setText("لا يوووجد..................");
                    holder.txtVendorName.setText("لا يوووجد..................");
                    holder.txtVendorNum.setText("لا يوووجد..................");
                    holder.txtTime.setText("لا يوووجد..................");
                    holder.txtPrice.setText("لا يوووجد..................");
                    holder.txtVendorAddress.setText("لا يوووجد..................");
                    Picasso.get().load(model.getImage()).into(holder.imageView);
                    Toast.makeText(final_brows.this,"NOOOOOO++++++++",Toast.LENGTH_LONG).show();
                }
                else {
                    holder.txtProductName.setText(model.getpartname());
                    holder.txtProductDescription.setText(model.getDescription());
                    holder.txtVendorName.setText(model.getVendor());
                    holder.txtVendorNum.setText(model.getVendorPhoneNumber());
                    holder.txtTime.setText(model.getPid());
                    holder.txtPrice.setText(model.getPrice());
                    holder.txtVendorAddress.setText(model.getVendorAddress());
                        Picasso.get().load(model.getImage()).into(holder.imageView);
                    Toast.makeText(final_brows.this,"BBBOOOOOOOOOOOOOBBBBB",Toast.LENGTH_LONG).show();
                    Toast.makeText(final_brows.this,model.getTime(),Toast.LENGTH_LONG).show();
                }

            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_parts_items,parent,false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
*/








        /*query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    loading.dismiss();
                    final FirebaseRecyclerOptions<Parts> options = new FirebaseRecyclerOptions.Builder<Parts>().setQuery(query, Parts.class).build();


                    final FirebaseRecyclerAdapter<Parts, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Parts, ProductViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(ProductViewHolder holder, int position, Parts model) {



                                holder.txtProductName.setText(model.getpartname());
                                holder.txtProductDescription.setText(model.getDescription());
                                holder.txtVendorName.setText(model.getVendor());
                                holder.txtVendorNum.setText(model.getVendorPhoneNumber());
                                holder.txtTime.setText(model.getPid());
                                holder.txtPrice.setText(String.valueOf(model.getPrice()));
                                Toast.makeText(final_brows.this,String.valueOf(model.getPrice()),Toast.LENGTH_SHORT).show();
                                holder.txtVendorAddress.setText(model.getVendorAddress());
                                Picasso.get().load(model.getImage()).into(holder.imageView);
                                final String phoneNumber =model.getVendorPhoneNumber();
                                holder.txtVendorName.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(final_brows.this,"Clicked on vendor name",Toast.LENGTH_SHORT).show();
                                        Intent intent =new Intent(final_brows.this,vendorpage.class);
                                        intent.putExtra("phonenumber",phoneNumber);
                                        startActivity(intent);
                                    }
                                });

                        }

                        @NonNull
                        @Override
                        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_parts_items, parent, false);
                            ProductViewHolder holder = new ProductViewHolder(view);
                            return holder;
                        }
                    };
                    recyclerView.setAdapter(adapter);
                    adapter.startListening();
                    recyclerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent =new Intent(final_brows.this,vendorpage.class);
                            startActivity(intent);
                        }
                    });
                }
                else{
               //     Toast.makeText(final_brows.this, "NOOOOOO++++++++", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(final_brows.this,emptyquery.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });













*/


    }// on start

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu_second_final,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        if(id == R.id.go_to_home);
        {
            Intent intent =new Intent(final_brows.this,page2.class);
            startActivity(intent);

        }
       if(id==android.R.id.home)
        {

            Intent myIntent = new Intent(getApplicationContext(), first_brows.class);
            startActivityForResult(myIntent, 0);
        }



        return super.onOptionsItemSelected(item);
    }
}
