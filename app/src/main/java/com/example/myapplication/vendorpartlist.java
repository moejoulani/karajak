package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Parts;
import com.example.myapplication.Prevalent.Prevalent;
import com.example.myapplication.ViewHolder.MyPartsViewHolder;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class vendorpartlist extends AppCompatActivity {
   // DatabaseReference PartsRef2;
    DocumentReference partRef;
    private RecyclerView recyclerView2;
    String UserParts;
    String ID2;
    TextView pid;
    RecyclerView.LayoutManager layoutManager2;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendorpartlist);
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        partRef=db.collection("Parts").document();

       // PartsRef2 = FirebaseDatabase.getInstance().getReference().child("Parts");
        recyclerView2 =findViewById(R.id.recoo);
        recyclerView2.setHasFixedSize(true);
        layoutManager2 =new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(layoutManager2);
        UserParts = Prevalent.currentOnlineUsers.getPhone();
        Toast.makeText(this,"The User : "+UserParts,Toast.LENGTH_SHORT).show();
        pid = (TextView) findViewById(R.id.My_date);
        //ID2 =pid.getText().toString();








    }

    @Override
    protected void onStart() {
        super.onStart();


        //        db.collection("Parts").document(model.getPid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {

        //  final Query query =FirebaseDatabase.getInstance().getReference("Parts").orderByChild("VendorPhoneNumber").equalTo(UserParts);


        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("Parts").whereEqualTo("VendorPhoneNumber", UserParts);
        FirestoreRecyclerOptions<Parts> options = new FirestoreRecyclerOptions.Builder<Parts>().setQuery(query, Parts.class).build();
        final FirestoreRecyclerAdapter<Parts, MyPartsViewHolder> adapter = new FirestoreRecyclerAdapter<Parts, MyPartsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyPartsViewHolder holder, int position, @NonNull final Parts model) {


                holder.My_partName.setText(model.getpartname());
                holder.My_typeCar.setText(model.getCarType());
                holder.My_carSeries.setText(model.getCarSeries());
                holder.My_carModel.setText(model.getCarModel());
                holder.My_Date.setText(model.getPid());
                // Toast.makeText(vendorpartlist.this,"The Data is :"+model.getCarModel()+model.getpartname()+model.getCarSeries(),Toast.LENGTH_SHORT).show();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence delete[] = new CharSequence[]
                                {"Delete"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(vendorpartlist.this);
                        builder.setTitle("Delete Items");
                        builder.setItems(delete, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                db.collection("Parts").document(model.getPid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(vendorpartlist.this, "PID : " + model.getPid() + "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(vendorpartlist.this, firstfun.class);
                                            startActivity(intent);
                                        }
                                    }
                                });

                            }
                        });
                        builder.show();

                    }
                });
            }

            @NonNull
            @Override
            public MyPartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items_list, parent, false);
                MyPartsViewHolder holder = new MyPartsViewHolder(view);
                return holder;
            }
        };
        recyclerView2.setAdapter(adapter);
        adapter.startListening();
    }




    /*    query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Toast.makeText(vendorpartlist.this,"inside data exist",Toast.LENGTH_LONG).show();
                    final FirebaseRecyclerOptions<Parts> options = new FirebaseRecyclerOptions.Builder<Parts>().setQuery(query, Parts.class).build();

                    final FirebaseRecyclerAdapter<Parts, MyPartsViewHolder> adapter = new FirebaseRecyclerAdapter<Parts, MyPartsViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull MyPartsViewHolder holder, int position, @NonNull final Parts model) {
                            holder.My_partName.setText(model.getpartname());
                            holder.My_typeCar.setText(model.getCarType());
                            holder.My_carSeries.setText(model.getCarSeries());
                            holder.My_carModel.setText(model.getCarModel());
                            holder.My_Date.setText(model.getPid());
                           // Toast.makeText(vendorpartlist.this,"The Data is :"+model.getCarModel()+model.getpartname()+model.getCarSeries(),Toast.LENGTH_SHORT).show();

                                holder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        CharSequence delete[] =new CharSequence[]
                                                {"Delete"};
                                        AlertDialog.Builder builder =new AlertDialog.Builder(vendorpartlist.this);
                                        builder.setTitle("Delete Items");
                                        builder.setItems(delete, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                    if(which==0)
                                                    {
                                                       PartsRef2.child(model.getPid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                    if(task.isSuccessful())
                                                                    {
                                                                        Toast.makeText(vendorpartlist.this,"PID : "+model.getPid()+"Successfully Deleted",Toast.LENGTH_SHORT).show();
                                                                        Intent intent =new Intent(vendorpartlist.this,firstfun.class);
                                                                        startActivity(intent);
                                                                    }
                                                            }
                                                        });

                                                       //_______________________________________________Not Important______________________________________________________________


                                                        PartsRef2.child("Parts").addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                                                {
                                                                    if(dataSnapshot1.child("pid").getValue().equals(model.getPid()))
                                                                    {
                                                                        dataSnapshot1.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                Toast.makeText(vendorpartlist.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                                                                                Intent intent =new Intent(vendorpartlist.this,firstfun.class);
                                                                                startActivity(intent);
                                                                            }
                                                                        });
                                                                    }
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(DatabaseError databaseError) {

                                                            }
                                                        });

                                                       //_____________________________________________Not Important______________________________________________________________

                                                    }
                                            }
                                        });
                                        builder.show();
                                    }
                                });
                        }

                        @NonNull
                        @Override
                        public MyPartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items_list,parent,false);
                            MyPartsViewHolder holder =new MyPartsViewHolder(view);
                            return holder;
                        }
                    };
                    recyclerView2.setAdapter(adapter);
                    adapter.startListening();
                }
                else{
                    Toast.makeText( vendorpartlist.this,"No Exist ::",Toast.LENGTH_SHORT).show();
                   Toast.makeText( vendorpartlist.this,UserParts,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */
            }

