package com.example.salonna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin_User_Male_Product_Category extends AppCompatActivity {

    Button btn_hair;
    Button btn_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__user__male__product__category);
        btn_hair = findViewById(R.id.btn_male_hair_admin);
        btn_body = findViewById(R.id.btn_male_body_admin);
    }

    public void ButtonClickMaleHair(View view) {
        btn_hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_User_Male_Product_Category.this, Admin_Male_Hair_Products_View.class);
                //intent.putExtra("hair",2);
                Toast.makeText(getApplicationContext(),"Navigating to Admin Male Hair Products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ButtonClickMaleBody(View view) {
        btn_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_User_Male_Product_Category.this, Admin_Male_Body_Products_View.class);
                intent.putExtra("body",1);
                Toast.makeText(getApplicationContext(),"Navigating to Admin Male Body Products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}