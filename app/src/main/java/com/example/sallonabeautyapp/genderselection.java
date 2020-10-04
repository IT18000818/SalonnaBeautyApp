package com.example.sallonabeautyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class genderselection extends AppCompatActivity {

    Button ladies;
    Button gents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genderselection);

        ladies = findViewById(R.id.ladies);
        gents = findViewById(R.id.gents);

        ladies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( genderselection.this, LadiesCategory.class);
                startActivity(intent);
            }

        });

        gents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(genderselection.this, GentsCategory.class);
                startActivity(intent);
            }
        });
    }
}