package com.example.sallonaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Activity5 extends AppCompatActivity {

    EditText txtName,txtDate,txtTime,txtPhone;
    Button btnSubmit;
    DatabaseReference dbRef;
    Treatment trt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        txtName = findViewById(R.id.name);
        txtDate = findViewById(R.id.date);
        txtTime = findViewById(R.id.time);
        txtPhone = findViewById(R.id.phone);

        btnSubmit = findViewById(R.id.button12);

        trt = new Treatment();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


     btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference(/*"Treatment"*/).child("Treatment");
                dbRef.setValue("test");
                try {

                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a NAME", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a DATE", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a TIME", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a PHONE", Toast.LENGTH_SHORT).show();

                    //Take inputs from the user and assigning them to this instance
                    trt.setName((txtName.getText().toString().trim()));
                    trt.setDate((txtDate.getText().toString().trim()));
                    trt.setTime((txtTime.getText().toString().trim()));
                    trt.setPhone((txtPhone.getText().toString().trim()));

                    //Insert in to the database
                    dbRef.push().setValue(trt);

                    //Feedback to the user

                    Toast.makeText(getApplicationContext(), "data saved successfully", Toast.LENGTH_SHORT).show();

                  }
                 catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid phone Number", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(Activity5.this, Activity10.class);
                startActivity(intent);
                  }
    });

        };







       // button12 = findViewById(R.id.button12);
        //button12.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {

               // Intent intent = new Intent(Activity5.this, Activity10.class);
                //startActivity(intent);

       // });
}
