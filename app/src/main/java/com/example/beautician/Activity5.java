package com.example.beautician;

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

import static com.example.beautician.R.id.birthday2;
import static com.example.beautician.R.id.button14;

public class Activity5 extends AppCompatActivity {


    EditText txtID, txtFirstName, txtLastName, txtBirthday, txtAddress, txtEmail, txtServices;
    Button btnNext;
    DatabaseReference dbRef;
    beautician ss;

    private void clearControls() {
        txtID.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtBirthday.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtServices.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);


        txtID = findViewById(R.id.name1);
        txtFirstName = findViewById(R.id.fname);
        txtLastName = findViewById(R.id.lname);
        txtBirthday = findViewById(R.id.birthday2);
        txtAddress = findViewById(R.id.address1);
        txtEmail = findViewById(R.id.email);
        txtServices = findViewById(R.id.service);

        btnNext = findViewById(R.id.button14);

        //button = findViewById(R.id.button14);

        ss = new beautician();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Activity5.this, Activity6.class);
//                startActivity(intent);
//
//
//
//            }
//
//
//        });

        btnNext.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("beautician");
                try {
                    if (TextUtils.isEmpty(txtID.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an ID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtFirstName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter the First Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtLastName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter the Last Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtBirthday.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter the Birthday", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtAddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter the Address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter the Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtServices.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter the Service", Toast.LENGTH_SHORT).show();
                    else {
                        //Take inputs from the user and assigning them to this instance
                        ss.setNic_id((txtID.getText().toString().trim()));
                        ss.setFirst_name((txtFirstName.getText().toString().trim()));
                        ss.setBirthday((txtBirthday.getText().toString().trim()));
                        ss.setLast_name((txtLastName.getText().toString().trim()));
                        ss.setAddress((txtAddress.getText().toString().trim()));
                        ss.setEmail((txtEmail.getText().toString().trim()));
                        ss.setServices((txtServices.getText().toString().trim()));

                        //Insert into the database
                        dbRef.push().setValue(ss);
                        dbRef.child("Lastbeautician").setValue(ss);

                        //feedback to the user

                        Toast.makeText(getApplicationContext(), "Create Account successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                        Intent beat = new Intent(Activity5.this, MainActivity.class);
                        startActivity(beat);
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid context number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}