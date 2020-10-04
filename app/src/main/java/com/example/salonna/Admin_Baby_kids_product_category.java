package com.example.salonna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin_Baby_kids_product_category extends AppCompatActivity {

    Button btn_hair;
    Button btn_wash;
    Button btn_cream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__baby_kids_product_category);

        btn_hair = (Button)findViewById(R.id.btn_baby_and_kids_hair_admin);
        btn_wash = (Button)findViewById(R.id.btn_baby_and_kids_wash_admin);
        btn_cream = (Button)findViewById(R.id.btn_baby_and_kids_cream_admin);
    }

    public void ButtonClickBabyAndKidsHair(View view) {
        btn_hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Baby_kids_product_category.this, Admin_BabyKids_BabyHair_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Admin Baby and Kids Hair Products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ButtonClickBabyAndKidsWash(View view) {
        btn_wash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Baby_kids_product_category.this, Admin_BabyKids_BabyWash_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Admin Baby and Kids Wash Products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ButtonClickBabyAndKidsCream(View view) {
        btn_cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Baby_kids_product_category.this, Admin_BabyKids_BabyCream_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Admin Baby and Kids Cream Products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}