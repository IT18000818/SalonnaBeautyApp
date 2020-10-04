package com.example.sallonaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity10 extends AppCompatActivity {

    Button update;
    Button delete;
    Button confirm;

    private static final String TAG = "Activity10";
    String treatmentID;
    String lastKey;
    EditText Name, Date, Time, Phone;
    DatabaseReference dbRef;
    Treatment trt;
    String lastkey2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_10);

        Name = findViewById(R.id.name);
        Date = findViewById(R.id.date);
        Time = findViewById(R.id.time);
        Phone = findViewById(R.id.phone);

        Treatment treatment = new Treatment();

        update = findViewById(R.id.button18);
        delete = findViewById(R.id.button20);
        confirm = findViewById(R.id.button21);


        dbRef = FirebaseDatabase.getInstance().getReference().child("Treatment")/*.child("Lasttreatment")*/;
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    Name.setText(dataSnapshot.child("name").getValue().toString());
                    Date.setText(dataSnapshot.child("date").getValue().toString());
                    Time.setText(dataSnapshot.child("time").getValue().toString());
                    Phone.setText(dataSnapshot.child("phone").getValue().toString());

                } else {


                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("Treatment");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //ArrayList<Treatment> arr = new ArrayList();
                        ArrayList<String> arrKeys = new ArrayList();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {

                            arrKeys.add(data.getKey());

                        }


                        String lastKey = arrKeys.get(arrKeys.size() - 2);

                        if (dataSnapshot.hasChild(lastKey)) {

                            try {
                            /*if (TextUtils.isEmpty(Name.getText().toString()))
                                Toast.makeText(getApplicationContext(), "please enter your name", Toast.LENGTH_SHORT).show();
                            else if (TextUtils.isEmpty(Date.getText().toString()))
                                Toast.makeText(getApplicationContext(), "please enter your date", Toast.LENGTH_SHORT).show();
                            else if (TextUtils.isEmpty(Time.getText().toString()))
                                Toast.makeText(getApplicationContext(), "please enter your time", Toast.LENGTH_SHORT).show();
                            else if (TextUtils.isEmpty(Phone.getText().toString()))
                                Toast.makeText(getApplicationContext(), "please enter your phone", Toast.LENGTH_SHORT).show();
                            else {
                            }*/

                                trt.setName(Name.getText().toString().trim());
                                trt.setName(Date.getText().toString().trim());
                                trt.setName(Time.getText().toString().trim());
                                trt.setName(Phone.getText().toString().trim());

                                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Treatment").child("lastTreatment");
                                dbref.setValue(trt);

                                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Number format error", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "No source", Toast.LENGTH_SHORT).show();
                        }

                        if (dataSnapshot.hasChild("LastTreatment")) {
                            try {
                                trt.setName(Name.getText().toString().trim());
                                trt.setName(Date.getText().toString().trim());
                                trt.setName(Time.getText().toString().trim());
                                trt.setName(Phone.getText().toString().trim());

                                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Treatment").child("LastTreatment");
                                dbref.setValue(trt);

                                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "No source", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Treatment");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> datakeys = new ArrayList<>();
                        for (DataSnapshot Data : dataSnapshot.getChildren()) {
                            datakeys.add(Data.getKey());

                        }

                        lastkey2 = datakeys.get(datakeys.size() - 2);

                        if (dataSnapshot.hasChild(lastkey2)) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Treatment").child(lastKey);
                            dbRef.removeValue();

                        }


                        if (dataSnapshot.hasChild("LastTreatment")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Treatment").child("LastTreatment");
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "No data to delete", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent intent = new Intent(Activity10.this, MainActivity.class);

                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity10.this);
                builder.setTitle("CONFIRMED");
                builder.setMessage("Your details has been confirmed.Thank You!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Activity10.this, MainActivity.class);
                        startActivity(intent);


                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });


    }
}








