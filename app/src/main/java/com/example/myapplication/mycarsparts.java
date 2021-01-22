package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Parts;
import com.example.myapplication.Prevalent.NUserPrevalent;
import com.example.myapplication.ViewHolder.MyCarViewHolder;
import com.example.myapplication.ViewHolder.ProductViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class mycarsparts extends AppCompatActivity {
      private  RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private ProgressDialog loading;
        private TextView close;
        private String y2k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycarsparts);
        recyclerView =(RecyclerView)findViewById(R.id.mycrecycle);
        recyclerView.setHasFixedSize(true);
        close=(TextView)findViewById(R.id.close_NUprofile22);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loading=new ProgressDialog(this);
          final   ProgressDialog lo=new ProgressDialog(this);
       lo.show();
    final     FirebaseFirestore dbb =FirebaseFirestore.getInstance();
    final     DocumentReference reference=dbb.collection("UCars").document(NUserPrevalent.currentOnlineNUsers.getNphone());
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    lo.dismiss();
                    DocumentSnapshot snap =task.getResult();
                    if(snap.exists())
                    {
                        y2k=(String)snap.get("CarType_CarSeries_CarModel");
                        Toast.makeText(mycarsparts.this,y2k,Toast.LENGTH_SHORT).show();
                        onStart45();
                    }
                    else{
                        Toast.makeText(mycarsparts.this,"nothing exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    protected void onStart45() {
      //  super.onStart();
        loading.show();
        loading.setCanceledOnTouchOutside(false);
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        final Query q =db.collection("Parts").whereEqualTo("CarType_CarSeries_CarModel",y2k);
        q.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful())
                    {
                        if(task.getResult().size()>0) {
                            Toast.makeText(mycarsparts.this,"size>0",Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                            final FirestoreRecyclerOptions<Parts> options = new FirestoreRecyclerOptions.Builder<Parts>().setQuery(q, Parts.class).build();
                            final FirestoreRecyclerAdapter<Parts, MyCarViewHolder> adapter = new FirestoreRecyclerAdapter<Parts, MyCarViewHolder>(options) {
                                @Override
                                protected void onBindViewHolder(@NonNull MyCarViewHolder holder, int position, @NonNull Parts model) {
                                    holder.MYC_partName.setText(model.getpartname());
                                    holder.MYC_partDESC.setText(model.getDescription());
                                    holder.MYC_partPrice.setText(String.valueOf(model.getPrice()));
                                    holder.MYC_vendName.setText(model.getVendor());
                                    holder.MYC_vendPhone.setText(model.getVendorPhoneNumber());
                                    holder.MYC_vendAddress.setText(model.getVendorAddress());
                                    Picasso.get().load(model.getImage()).into(holder.MYC_partImage);

                                }

                                @NonNull
                                @Override
                                public MyCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycarparts, parent, false);
                                    MyCarViewHolder holder = new MyCarViewHolder(view);
                                    return holder;
                                }
                            };
                            recyclerView.setAdapter(adapter);
                            adapter.startListening();
                        }
                        else{
                            loading.dismiss();
                            Toast.makeText(mycarsparts.this,"task is not exist",Toast.LENGTH_SHORT).show();
                        }
                        }
                    else{
                        Toast.makeText(mycarsparts.this,"task is not sucessfull",Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}
