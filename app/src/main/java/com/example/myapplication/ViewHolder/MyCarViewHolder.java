package com.example.myapplication.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.ItemClickListner;
import com.example.myapplication.R;

public class MyCarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
   public  ItemClickListner listner;
    public TextView MYC_partName,MYC_partDESC,MYC_vendName,MYC_vendPhone,MYC_partPrice,MYC_vendAddress;
    public ImageView MYC_partImage;
    public MyCarViewHolder(@NonNull View itemView) {
        super(itemView);
        MYC_partName=(TextView)itemView.findViewById(R.id.mycname);
        MYC_partDESC=(TextView)itemView.findViewById(R.id.mycdesc);
        MYC_partPrice=(TextView)itemView.findViewById(R.id.mycprice);
        MYC_vendAddress=(TextView)itemView.findViewById(R.id.mycaddress);
        MYC_vendName=(TextView)itemView.findViewById(R.id.mycvendname);
        MYC_vendPhone=(TextView)itemView.findViewById(R.id.mycphone);
        MYC_partImage=(ImageView)itemView.findViewById(R.id.mycimage);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner =listner;
    }


    @Override
    public void onClick(View v) {
        listner.onClick(v,getAdapterPosition(),false);
    }


}
