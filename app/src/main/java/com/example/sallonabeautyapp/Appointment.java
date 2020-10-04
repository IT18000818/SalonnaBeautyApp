package com.example.sallonabeautyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Appointment extends AppCompatActivity {

    EditText apName, apMobile, apService, apDate, apTime, apBill;

    ProgressDialog progressDialog;

    //declare am instance of firebaseAuth
    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    Button done;

    appoint appoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment);

        apName = findViewById(R.id.appName);
        apMobile = findViewById(R.id.appPhone);
        //apService = findViewById()
        apDate = findViewById(R.id.appDate);
        apTime = findViewById(R.id.appTime);
        //apbill = findViewById()

        done = findViewById(R.id.appDone);

        appoint = new appoint();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("appoint");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Name = apName.getText().toString().trim();
                final String Mobile = apMobile.getText().toString().trim();
                final String Date = apDate.getText().toString().trim();
                final String Time = apTime.getText().toString().trim();


                if(TextUtils.isEmpty(Name)) {
                    apName.setError("Name is Required");

                }

                else if(TextUtils.isEmpty(Mobile)) {
                    apMobile.setError("Mobile Number is Required");

                }

                else if(TextUtils.isEmpty(Date)) {
                    apDate.setError("Date is Required");
                }

                else if(TextUtils.isEmpty(Time)) {
                    apTime.setError("Time is Required");
                }

                else {
                    appoint.setName(Name);
                    appoint.setMobile(Mobile);
                    appoint.setDate(Date);
                    appoint.setTime(Time);
                    databaseReference.push().setValue(appoint);


                    Intent intent = new Intent(Appointment.this, ViewAppointments.class);
                    intent.putExtra("mobileNumber", Mobile);
                    startActivity(intent);
                    finish();


                    Toast.makeText(Appointment.this, "Appointment Created Successfully", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

}