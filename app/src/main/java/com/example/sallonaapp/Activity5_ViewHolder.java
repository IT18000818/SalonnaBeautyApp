package com.example.sallonaapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Activity5_ViewHolder extends RecyclerView.ViewHolder {

    TextView v_name,v_date,v_time,v_phone;
    Button btn_edit,btn_delete;

    public Activity5_ViewHolder(@NonNull View itemView) {
        super(itemView);

        v_name = (TextView)itemView.findViewById(R.id.v_name);
        v_date = (TextView)itemView.findViewById(R.id.v_date);
        v_time = (TextView)itemView.findViewById(R.id.v_time);
        v_phone = (TextView)itemView.findViewById(R.id.v_phone);
        btn_edit = (Button)itemView.findViewById(R.id.edit_btn);
        btn_delete = (Button)itemView.findViewById(R.id.delete_btn);
    }
}
