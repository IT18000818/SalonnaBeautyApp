package com.example.salonna;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import Interfaces.ItemClickListener;

public class Admin_Female_hair_ViewHolder extends RecyclerView.ViewHolder {
    TextView tw_title, tw_brand, tw_price;
    ImageView imageView;
    Button btn_edit,btn_delete;
    ItemClickListener itemClickListener;

    public Admin_Female_hair_ViewHolder(@NonNull View itemView) {
        super(itemView);
        tw_title = (TextView) itemView.findViewById(R.id.female_hair_title);
        tw_brand = (TextView) itemView.findViewById(R.id.female_hair_brand);
        tw_price = (TextView) itemView.findViewById(R.id.female_hair_price);
        imageView = (ImageView) itemView.findViewById(R.id.image_female_hair);
        btn_edit = (Button) itemView.findViewById(R.id.btn_edit_female_hair);
        btn_delete = (Button) itemView.findViewById(R.id.btn_delete_female_hair);
    }
}
