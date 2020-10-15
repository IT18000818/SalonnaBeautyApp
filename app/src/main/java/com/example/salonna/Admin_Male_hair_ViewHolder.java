package com.example.salonna;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import Interfaces.ItemClickListener;

public class Admin_Male_hair_ViewHolder extends RecyclerView.ViewHolder{
    TextView tw_title, tw_brand,tw_price;
    ImageView imageView;
    Button btn_edit,btn_delete,btn_cancel;

    public Admin_Male_hair_ViewHolder(@NonNull View itemView) {
        super(itemView);

        tw_title = (TextView)itemView.findViewById(R.id.male_hair_title);
        tw_brand = (TextView)itemView.findViewById(R.id.male_hair_brand);
        tw_price = (TextView)itemView.findViewById(R.id.male_hair_price);
        btn_edit = (Button)itemView.findViewById(R.id.btn_edit_male_hair);
        btn_delete = (Button)itemView.findViewById(R.id.btn_delete_male_hair);
    }
}
