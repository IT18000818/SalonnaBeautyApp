package com.example.salonna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Customer_Male_Product_Category extends AppCompatActivity {

    Button btn_hair;
    Button btn_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__male__product__category);
        btn_hair = (Button)findViewById(R.id.btn_Male_Hair);
        btn_body = (Button)findViewById(R.id.btn_Male_body);
    }

    public void ButtonClickMaleHair(View view) {
        btn_hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_Male_Product_Category.this, Customer_Male_Hair_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Male Hair Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ButtonClickMaleBody(View view) {
        btn_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_Male_Product_Category.this, Customer_Male_Body_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Male Body Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}