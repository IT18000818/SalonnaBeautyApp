package com.example.sallonabeautyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewAppointments extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView dName, dMobile, dTime, dDate;
    Button edit, delete, done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_details);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appoint");

        dName = findViewById(R.id.nameview);
        dMobile = findViewById(R.id.mobileview);
        dTime = findViewById(R.id.timeview);
        dDate = findViewById(R.id.dateview);

        edit = findViewById(R.id.viewedit);
        done = findViewById(R.id.viewdone);
        delete = findViewById(R.id.viewdelete);

        Bundle extras = getIntent().getExtras();
        String mobileNumber = extras.getString("mobileNumber");


        Query query = databaseReference.orderByChild("mobile").equalTo(mobileNumber);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = "" + ds.child("name").getValue();
                    String mobile = "" + ds.child("mobile").getValue();
                    String date = "" + ds.child("date").getValue();
                    String time = "" + ds.child("time").getValue();

                    dName.setText(name);
                    dMobile.setText(mobile);
                    dDate.setText(date);
                    dTime.setText(time);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }
}