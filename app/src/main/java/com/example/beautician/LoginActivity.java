package com.example.beautician;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;

    private String UserName = "Admin";
    private String Password = "12345678";

    private int counter = 5;

    boolean isValid = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"Please enter all the details correctly", Toast.LENGTH_SHORT).show();
                }else{

                    isValid = validate(inputName, inputPassword);

                    if(!isValid){

                        counter--;

                        Toast.makeText(LoginActivity.this,"Incorrect credentials entered!", Toast.LENGTH_SHORT).show();

                        eAttemptsInfo.setText("No.of.attempts remaining: " + counter);

                        if(counter == 0){
                            eLogin.setEnabled(false);
                        }
                    }else{


                        Toast.makeText(LoginActivity.this,"Login Successful!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, Homepage1.class);
                        startActivity(intent);
                    }

                }


            }
        });


    }

    private boolean validate(String name,String password){

        if (name.equals(UserName) && password.equals(Password)){
            return true;
        }

        return false;
    }

}