package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Prevalent.NUserPrevalent;

import io.paperdb.Paper;

public class nuserProfile extends AppCompatActivity {
       private EditText Inputname,Inputphone,Inputpassword;
        private TextView close,addmycar;
        private Button logout,EditProfile,myCarParts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuser_profile);
        Paper.init(this);
        EditProfile=(Button) findViewById(R.id.Edit_NU);
        addmycar=(TextView)findViewById(R.id.addmycar);
        logout=(Button)findViewById(R.id.logout_NU);
        myCarParts=(Button)findViewById(R.id.MYPARTS);
        close=(TextView)findViewById(R.id.close_NUprofile);
        Inputname=(EditText)findViewById(R.id.nameENU);
        Inputphone=(EditText)findViewById(R.id.phoneENU);
        Inputpassword=(EditText)findViewById(R.id.passwordENU);
        Inputname.setText(NUserPrevalent.currentOnlineNUsers.getNname());
        Inputphone.setText(NUserPrevalent.currentOnlineNUsers.getNphone());
        Inputpassword.setText(NUserPrevalent.currentOnlineNUsers.getNpassword());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NUserPrevalent.currentOnlineNUsers=null;
                Paper.book().destroy();
                finish();
                Intent intent=new Intent(nuserProfile.this,page2.class);
                startActivity(intent);
            }
        });
        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addmycar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(nuserProfile.this,addcar.class);
                    startActivity(intent);
            }
        });
        myCarParts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(nuserProfile.this,mycarsparts.class);
                startActivity(intent);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        }
}
