package com.example.sallonaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity3_1 extends AppCompatActivity {

    Button  button19;
    Button  button35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3_1);

        button19 = findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity3_1.this, MainActivity.class);
                startActivity(intent);
            }
        });


        /*button35 = findViewById(R.id.button35);
        button35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity3_1.this, Activity5.class);
                startActivity(intent);
            }
        });*/


    }

    public void ButtonClickNext(View view){
        button35 = findViewById(R.id.button35);
        button35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity3_1.this, Activity5.class);
                startActivity(intent);
            }
        });
    }
}