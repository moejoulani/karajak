package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Mechanic_1 extends AppCompatActivity {
    int IMAGES []= {R.drawable.bawaji,R.drawable.bakrat};
    String NAMES  []={"الاقشطة و البكرات","البواجي و نظام التشغيل"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_1);


    }


}
