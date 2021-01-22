package com.example.myapplication.ViewHolder;

import android.media.Image;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.ItemClickListner;
import com.example.myapplication.ItemsList;
import com.example.myapplication.R;

import org.w3c.dom.Text;


public class VendorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView Vpartname,Vcartype,Vcarseries,Vcarmodel,Vprice;
    public ImageView Vpartimage;
    ItemClickListner listner;
    public VendorViewHolder(View itemView) {
        super(itemView);

        Vpartname =(TextView) itemView.findViewById(R.id.partnameV);
        Vcartype  =(TextView) itemView.findViewById(R.id.cartypeV);
        Vcarseries=(TextView) itemView.findViewById(R.id.carseriesV);
        Vcarmodel =(TextView) itemView.findViewById(R.id.carmodelV);
        Vpartimage=(ImageView)itemView.findViewById(R.id.imagepartV);
        Vprice    =(TextView) itemView.findViewById(R.id.priceV);


    }
    public void setItemClickListener(ItemClickListner listener)
    {
        this.listner =listener;
    }


    @Override
    public void onClick(View v) {
            listner.onClick(v,getAdapterPosition(),false);
    }



}
