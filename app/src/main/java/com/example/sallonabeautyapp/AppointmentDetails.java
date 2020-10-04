package com.example.sallonabeautyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AppointmentDetails extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView dName, dMobile, dTime, dDate;
    Button edit, delete, done;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appointment_details, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            System.out.println( "Extra:" + extras.getString("mobileNUmber") );
        }


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appoint");

        dName = view.findViewById(R.id.nameview);
        dMobile = view.findViewById(R.id.mobileview);
        dTime = view.findViewById(R.id.timeview);
        dDate = view.findViewById(R.id.dateview);

        edit = findViewById(R.id.viewedit);
        done = findViewById(R.id.viewdone);
        delete = findViewById(R.id.viewdelete);

        // user log -> mobile number
        //
        //String mmobile = getVi

        Query query = databaseReference.orderByChild("mobile").equalTo("7777777");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = "" + ds.child("name");
                    String mobile = "" + ds.child("mobile");
                    String date = "" + ds.child("date");
                    String time = "" + ds.child("time");

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

        //edit.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View view) {
         //       String name =
         //    }
        //});

        return view;



    }



}