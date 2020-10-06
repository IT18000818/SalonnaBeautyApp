package com.example.salonna;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Customer_Female_hair_ViewHolder  extends RecyclerView.ViewHolder{
    TextView tw_title, tw_brand,tw_price;
    ImageView imageView;

    public Customer_Female_hair_ViewHolder(@NonNull View itemView) {
        super(itemView);
        tw_title = (TextView)itemView.findViewById(R.id.C_female_hair_title);
        tw_brand = (TextView)itemView.findViewById(R.id.C_female_hair_brand);
        tw_price = (TextView)itemView.findViewById(R.id.C_female_hair_price);
        imageView = (ImageView)itemView.findViewById(R.id.image_hair_C_female);
    }

}
