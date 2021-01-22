package com.example.myapplication;

import android.provider.ContactsContract;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class ItemsList extends AppCompatActivity {
      Button Delete;
      TextView Pid;
      String ID;
    //  DatabaseReference reference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        //Delete =(Button) findViewById(R.id.deleteItem);
      //  reference1 = FirebaseDatabase.getInstance().getReference().child("Parts");
        Pid =(TextView) findViewById(R.id.My_date);
       ID = Pid.getText().toString();
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(,"Click CLick Simpson",Toast.LENGTH_LONG).show();

            }
        });
    }
   /*public void DeleteItem()
    {
    //    Query query = FirebaseDatabase.getInstance().getReference().child("Parts").orderByChild("pid").equalTo(ID);
      //  reference1.child("Parts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    if(dataSnapshot1.child("pid").getValue().toString().equals(ID))
                    {
                        dataSnapshot1.getRef().setValue(null);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    */

    
}
