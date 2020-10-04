package com.example.sallonaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;

        //myRef.setValue("Hello, World!");
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,me2.class);
                startActivity(intent);
            }
        });

       // button2 = findViewById(R.id.button2);
       // button2.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View view) {
               // Intent intent = new Intent(MainActivity.this,me2.class);
               // startActivity(intent);
         //   }
       // });


       // button3 = findViewById(R.id.button3);
       // button3.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View view) {
             //   Intent intent = new Intent(MainActivity.this,me2.class);
              //  startActivity(intent);
         //   }
        //});



    }


}