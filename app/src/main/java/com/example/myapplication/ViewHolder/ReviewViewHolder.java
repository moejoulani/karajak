package com.example.myapplication.ViewHolder;

import android.content.ClipData;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.ItemClickListner;
import com.example.myapplication.R;

import org.w3c.dom.Text;

public class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemClickListner listner;
    public TextView RevName,RevComment,RevTime;
    public RatingBar rate;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        RevName =(TextView) itemView.findViewById(R.id.comname);
        RevComment=(TextView)itemView.findViewById(R.id.comm);
        rate=(RatingBar)itemView.findViewById(R.id.revRate);
        RevTime=(TextView)itemView.findViewById(R.id.timeooo);
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
