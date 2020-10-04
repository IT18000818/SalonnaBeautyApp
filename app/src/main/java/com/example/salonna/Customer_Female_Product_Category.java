package com.example.salonna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Customer_Female_Product_Category extends AppCompatActivity {

    Button btn_hair;
    Button btn_body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__female__product__category);
        btn_hair = (Button) findViewById(R.id.btn_female_hair);
        btn_body = (Button) findViewById(R.id.btn_female_body);
    }

    public void ButtonClickFemaleHair(View view) {
        btn_hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_Female_Product_Category.this, Customer_Female_Hair_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Female Hair Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ButtonClickFemaleBody(View view) {
        btn_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_Female_Product_Category.this, Customer_Female_Body_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Female Body Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}