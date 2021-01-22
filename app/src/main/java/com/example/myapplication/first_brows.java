package com.example.myapplication;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Prevalent.NUserPrevalent;

public class first_brows extends AppCompatActivity {
private TextView NnameTop;
private Button btn_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_brows);
        btn_reg=(Button)findViewById(R.id.btn_register);
        NnameTop=(TextView)findViewById(R.id.NnameTop);
        btn_reg.setVisibility(View.GONE);
        if(!(NUserPrevalent.isEmpty()))
        {
            NnameTop.setText(NUserPrevalent.currentOnlineNUsers.getNname());
            NnameTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(first_brows.this,nuserProfile.class);
                    startActivity(intent);
                }
            });
            btn_reg.setVisibility(View.GONE);
        }
        else if(NUserPrevalent.isEmpty())
        {
            btn_reg.setVisibility(View.VISIBLE);
            NnameTop.setVisibility(View.GONE);
            btn_reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(first_brows.this,signinyoy.class);
                    startActivity(intent);
                }
            });
        }
    }

    public void mechanicListView(View view)
    {
        Intent intent = new Intent(this,second_brows.class);
        intent.putExtra("Category","ميكانيك");
        startActivity(intent);


    }
    public void bodyListView(View view)
    {
        Intent intent = new Intent(this,second_brows.class);
        intent.putExtra("Category","بودي");
        startActivity(intent);
    }
    public void electronicListView(View view)
    {
        Intent intent = new Intent(this,second_brows.class);
        intent.putExtra("Category","كهرباء");
        startActivity(intent);
    }
    public void tiresListView(View view)
    {
        Intent intent = new Intent(this,second_brows.class);
        intent.putExtra("Category","اطارات");
        startActivity(intent);
    }
    public void accessoriesListView(View view)
    {
        Intent intent = new Intent(this,second_brows.class);
        intent.putExtra("Category","اكسسوارات");
        startActivity(intent);
    }
    public void oilsListView(View view)
    {
        Intent intent = new Intent(this,second_brows.class);
        intent.putExtra("Category","زيوت");
        startActivity(intent);
    }


}

