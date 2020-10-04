package com.example.sallonabeautyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class StartingPage extends AppCompatActivity {

    EditText txtEmail, txtPassword;

    ProgressDialog progressDialog;

    //declare am instance of firebaseAuth
    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_page);

        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.passwrd);
        signupbtn = findViewById(R.id.signupbtn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering User...");

        //in the onCreate() method, initialize the FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = txtEmail.getText().toString().trim();
                final String password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    txtEmail.setError("Email is Required");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    txtEmail.setError("Invalid Email");
                    txtEmail.setFocusable(true);
                }

                else if(TextUtils.isEmpty(password)) {
                    txtPassword.setError("Password is Required");
                }
                else if(password.length()<6) {
                    txtPassword.setError("Password length must contain at least 6 characters");
                    txtPassword.setFocusable(true);
                }

                else {
                    registerUser(email,password); //uses to register the user
                }

            }
        });

    }

    private void registerUser(String email, String password){

        //email and password validation passed, display progress dialog
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, dismiss dialog and start register activity
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();

                            //get user email and uid from auth
                            String email = user.getEmail();
                            String uid = user.getUid();

                            //use hashmap to store user details in DB when registering
                            HashMap<Object,String> hashMap = new HashMap<>();

                            //insert information into hashmap
                            hashMap.put("email", email);
                            hashMap.put("uid", uid);

                            //firebase database instance
                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            //store customer
                            DatabaseReference refernce = database.getReference("Customers");

                            //insert hashmap data into database
                            refernce.child(uid).setValue(hashMap);

                            Toast.makeText(StartingPage.this, "Account Created Successfully\n"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(StartingPage.this,genderselection.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user
                            progressDialog.dismiss();
                            Toast.makeText(StartingPage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //error, dismiss progress dialog and get show the error message
                progressDialog.dismiss();
                Toast.makeText(StartingPage.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}