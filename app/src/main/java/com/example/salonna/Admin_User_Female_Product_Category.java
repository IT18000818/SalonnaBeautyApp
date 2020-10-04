package com.example.salonna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin_User_Female_Product_Category extends AppCompatActivity {

    Button btn_hair;
    Button btn_body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__user__female__product__category);
        btn_hair = (Button)findViewById(R.id.btn_female_hair_admin);
        btn_body = (Button)findViewById(R.id.btn_female_body_admin);
    }

    public void ButtonClickFemaleHairAdmin(View view) {
        btn_hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_User_Female_Product_Category.this, Admin_Female_Hair_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Admin Female Hair Products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ButtonClickFemaleBodyAdmin(View view) {
        btn_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_User_Female_Product_Category.this, Admin_Female_Body_Products_View.class);
                Toast.makeText(getApplicationContext(),"Navigating to Admin Female Body Products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}