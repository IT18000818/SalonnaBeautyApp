package com.example.sallonabeautyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Beautician extends AppCompatActivity {

    Button beautician1, beautician2, beautician3, beautician4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beautician);

        beautician1 = findViewById(R.id.radioButton4);
        beautician2 = findViewById(R.id.radioButton);
        beautician3 = findViewById(R.id.radioButton2);
        beautician4 = findViewById(R.id.radioButton3);


        beautician1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( Beautician.this, Appointment.class);
                startActivity(intent);
            }

        });

        beautician2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Beautician.this, Appointment.class);
                startActivity(intent);
            }
        });

        beautician3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Beautician.this, Appointment.class);
                startActivity(intent);
            }
        });

        beautician4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Beautician.this, Appointment.class);
                startActivity(intent);
            }
        });
    }
}