package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        private static int WELCOME_TIMEOUT =1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent welcome  = new Intent(MainActivity.this,page2.class);
                    startActivity(welcome);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    finish();
            }
        },WELCOME_TIMEOUT);

    }
    public void page2(View view)
    {
        Intent myIntent = new Intent(this,page2.class);// Moving To Second Activity
        startActivity(myIntent);

    }
}
