package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Model.Review;
import com.example.myapplication.ViewHolder.ReviewViewHolder;
import com.firebase.ui.auth.ui.email.RecoverPasswordActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class reviewR extends AppCompatActivity {
    private ProgressDialog loading;
    private String phoneNumber;
    private ImageView close_btn;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_r);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .8));



        loading=new ProgressDialog(this);
        phoneNumber = getIntent().getExtras().get("phonenumber").toString();
        close_btn=(ImageView) findViewById(R.id.close_btn_rev);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyyy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        final Query query=db.collection("Review").whereEqualTo("RvendorPhone",phoneNumber);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful())
                    {
                        if(task.getResult().size()>0)
                        {
                            loading.dismiss();
                            final FirestoreRecyclerOptions<Review> options=new FirestoreRecyclerOptions.Builder<Review>().setQuery(query,Review.class).build();
                            final FirestoreRecyclerAdapter<Review,ReviewViewHolder> adapter=new FirestoreRecyclerAdapter<Review, ReviewViewHolder>(options) {
                                @Override
                                protected void onBindViewHolder(@NonNull ReviewViewHolder holder, int position, @NonNull Review model) {
                                                    holder.RevName.setText(model.getRnuserName());
                                                    holder.RevComment.setText(model.getRnuserComment());
                                                    holder.RevTime.setText(model.getRtime());
                                                    holder.rate.setRating(model.getRnuserRate());

                                }

                                @NonNull
                                @Override
                                public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                   View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comments,parent,false);
                                   ReviewViewHolder reviewViewHolder =new ReviewViewHolder(view);
                                   return reviewViewHolder;
                                }
                            };
                            recyclerView.setAdapter(adapter);
                            adapter.startListening();
                        }
                    }
                    else{
                        loading.dismiss();
                        Toast.makeText(reviewR.this,"لا يوجد تعليقات ...",Toast.LENGTH_SHORT).show();
                    }



            }
        });

    }
}
