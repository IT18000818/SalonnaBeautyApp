package com.example.sallonabeautyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LadiesCategory extends AppCompatActivity {

    Button bridal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladies_category);

        bridal = findViewById(R.id.bridal);

        bridal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LadiesCategory.this, Bridal.class);
                startActivity(intent);
            }

        });

    }
}