package com.example.sallonabeautyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Bridal extends AppCompatActivity {

    Button chPlatinum, chGold, chSilver, chBronze;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bridal);


        chPlatinum = findViewById(R.id.radioplatinum);
        chGold = findViewById(R.id.radiogold);
        chSilver = findViewById(R.id.radiosilver);
        chBronze = findViewById(R.id.radiobronze);


        chPlatinum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( Bridal.this, Beautician.class);
                startActivity(intent);
            }

        });

        chGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Bridal.this, Beautician.class);
                startActivity(intent);
            }
        });

        chSilver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Bridal.this, Beautician.class);
                startActivity(intent);
            }
        });

        chBronze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Bridal.this, Beautician.class);
                startActivity(intent);
            }
        });
    }
}