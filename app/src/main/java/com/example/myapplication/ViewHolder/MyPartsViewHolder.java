package com.example.myapplication.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interface.ItemClickListner;
import com.example.myapplication.R;

import org.w3c.dom.Text;

public class MyPartsViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
                ItemClickListner listner;
                public TextView My_partName,My_typeCar,My_carSeries,My_carModel,My_Date;

    public MyPartsViewHolder(View itemView) {
        super(itemView);

        My_partName =(TextView) itemView.findViewById(R.id.My_partname);
        My_typeCar  =(TextView) itemView.findViewById(R.id.My_carType);
        My_carSeries=(TextView) itemView.findViewById(R.id.My_carSeries);
        My_carModel =(TextView) itemView.findViewById(R.id.My_carModel);
        My_Date =(TextView) itemView.findViewById(R.id.My_date);








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
