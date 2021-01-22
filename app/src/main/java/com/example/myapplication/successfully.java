package com.example.myapplication;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class successfully extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully);


    }
    public void goback(View view)
    {
        Intent intent =new Intent(this,firstfun.class);
        startActivity(intent);
    }

}
