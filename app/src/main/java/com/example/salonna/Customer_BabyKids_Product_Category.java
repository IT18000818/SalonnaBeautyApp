package com.example.salonna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Customer_BabyKids_Product_Category extends AppCompatActivity {

    Button btn_hair;
    Button btn_wash;
    Button btn_cream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__baby_kids__product__category);

        btn_hair = (Button)findViewById(R.id.btn_baby_Hair);
        btn_wash = (Button)findViewById(R.id.btn_baby_Wash);
        btn_cream = (Button)findViewById(R.id.btn_baby_Cream);
    }

    public void ButtonClickBabyHair(View view) {
        btn_hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_BabyKids_Product_Category.this, Customer_BabyKids_Hair_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Baby and Kids Hair Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ButtonClickBabyWash(View view) {
        btn_wash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_BabyKids_Product_Category.this, Customer_BabyKids_BabyWash_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Baby and Kids Wash Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ButtonClickBabyCream(View view) {
        btn_cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_BabyKids_Product_Category.this, Customer_BabyKids_BabyCream_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Baby and Kids Cream Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}