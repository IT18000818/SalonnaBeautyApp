package com.example.sallonaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Activity5 extends AppCompatActivity {

    EditText txtName,txtDate,txtTime,txtPhone;
    Button btnSubmit;
    DatabaseReference dbRef;
    Treatment trt;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        txtName = findViewById(R.id.add_name);
        txtDate = findViewById(R.id.add_date);
        txtTime = findViewById(R.id.add_time);
        txtPhone = findViewById(R.id.add_phone);

        btnSubmit = findViewById(R.id.button12);

        trt = new Treatment();
        // Write a message to the database

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*dbRef = FirebaseDatabase.getInstance().getReference(*//*"Treatment"*//*).child("Treatment");
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

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid phone Number", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(Activity5.this, Activity10.class);
                startActivity(intent);*/

                final Treatment treatment = new Treatment();

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("Treatment");

                //ValidateProduct();
                // Validations for Adding baby and kids cream product */
                if (TextUtils.isEmpty(txtName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter a NAME", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtDate.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter a DATE", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtDate.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter a TIME", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtDate.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please Enter a PHONE", Toast.LENGTH_SHORT).show();
                else {
                    DatabaseReference  readref= FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                    readref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String ID= snapshot.child("IntitalVal").getValue().toString();
                            int IDx;
                            IDx=Integer.parseInt(ID);
                            IDx++;
                            ID=Integer.toString(IDx);
                            treatment.setID(ID);
                            treatment.setName(txtName.getText().toString().trim());
                            treatment.setDate(txtDate.getText().toString().trim());
                            treatment.setTime(txtTime.getText().toString().trim());
                            treatment.setPhone(txtPhone.getText().toString().trim());
                            Toast.makeText(getApplicationContext(), "data saved successfully", Toast.LENGTH_SHORT).show();
                            DatabaseReference Insertref2=FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                            databaseReference.child(ID).setValue(treatment);
                            IDx++;
                            ID=Integer.toString(IDx);
                            Insertref2.child("IntitalVal").setValue(ID);

                            clearDataAfterInsert();

                            Intent intent = new Intent(Activity5.this,ViewActivity.class);
                            Toast.makeText(Activity5.this, "Navigating to view page", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            }
        }
    });
    }
    public void clearDataAfterInsert(){
        txtDate.setText("");
        txtName.setText("");
        txtTime.setText("");
        txtPhone.setText("");
    }
}






       // button12 = findViewById(R.id.button12);
        //button12.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {

               // Intent intent = new Intent(Activity5.this, Activity10.class);
                //startActivity(intent);

       // });

