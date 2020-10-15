package com.example.salonna;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import Interfaces.ItemClickListener;

public class Admin_BabyAndKids_baby_Hair_ViewHolder  extends RecyclerView.ViewHolder{
    TextView tw_title, tw_brand,tw_price;
    ImageView imageView;
    ItemClickListener itemClickListener;
    Button btn_edit, btn_delete;


    public Admin_BabyAndKids_baby_Hair_ViewHolder(@NonNull View itemView) {
        super(itemView);
        tw_title = (TextView)itemView.findViewById(R.id.hair_title);
        tw_brand = (TextView)itemView.findViewById(R.id.hair_brand);
        tw_price = (TextView)itemView.findViewById(R.id.hair_price);
        btn_edit = (Button) itemView.findViewById(R.id.btn_baby_Hair_edit);
        btn_delete = (Button) itemView.findViewById(R.id.btn_baby_Hair_delete);
    }
}
