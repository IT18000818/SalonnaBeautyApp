package com.example.salonna;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import Interfaces.ItemClickListener;

public class Admin_BabyAndKids_baby_Wash_ViewHolder extends RecyclerView.ViewHolder{
    TextView tw_title, tw_brand,tw_price;
    ImageView imageView;
    ItemClickListener itemClickListener;

    public Admin_BabyAndKids_baby_Wash_ViewHolder(@NonNull View itemView) {
        super(itemView);

        tw_title = (TextView)itemView.findViewById(R.id.wash_title);
        tw_brand = (TextView)itemView.findViewById(R.id.wash_brand);
        tw_price = (TextView)itemView.findViewById(R.id.wash_price);
        imageView = (ImageView)itemView.findViewById(R.id.image_wash);
    }
}
