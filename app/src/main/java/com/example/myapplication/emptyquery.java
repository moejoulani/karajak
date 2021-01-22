package com.example.myapplication;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class emptyquery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptyquery);
    }
    public void cash(View view)
    {
        finish();
        Intent intent =new Intent(this,first_brows.class);
        startActivity(intent);
    }
}
