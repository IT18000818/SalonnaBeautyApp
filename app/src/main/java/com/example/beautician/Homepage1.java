package com.example.beautician;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage1 extends AppCompatActivity {

    Button button;
    Button btnAppimnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage1);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage1.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnAppimnt = (Button) findViewById(R.id.btnAppimnt);
        btnAppimnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage1.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}