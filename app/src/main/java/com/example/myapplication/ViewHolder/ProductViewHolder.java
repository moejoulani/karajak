package com.example.myapplication.ViewHolder;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.ItemClickListner;
import com.example.myapplication.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtProductName , txtProductDescription,txtVendorName,txtVendorNum,txtTime,txtPrice,txtVendorAddress;
    public ImageView imageView;
    public ItemClickListner listner;
    public RatingBar rate;


    public ProductViewHolder(View itemView) {
        super(itemView);

        imageView =(ImageView) itemView.findViewById(R.id.img_product);
        txtProductName =(TextView) itemView.findViewById(R.id.product_name);
        txtProductDescription=(TextView)itemView.findViewById(R.id.descriptionprod);
        txtVendorName = (TextView) itemView.findViewById(R.id.vendorName);
        txtVendorNum  =(TextView) itemView.findViewById(R.id.vendorNum);
        txtTime =(TextView) itemView.findViewById(R.id.time);
        txtPrice=(TextView) itemView.findViewById(R.id.myPrice);
        txtVendorAddress=(TextView) itemView.findViewById(R.id.vendoraddres);
        rate=(RatingBar)itemView.findViewById(R.id.rate);
        rate.setRating(Float.parseFloat("0"));
    
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
