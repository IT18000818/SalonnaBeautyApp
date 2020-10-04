package com.example.salonna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin_Customer_Category_selection extends AppCompatActivity {

    Button btn_male;
    Button btn_female;
    Button btn_babyAndKids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__customer__category_selection);
        btn_male = (Button) findViewById(R.id.btn_Male);
        btn_female = (Button) findViewById(R.id.btn_female);
        btn_babyAndKids = (Button) findViewById(R.id.btn_babyandkids);

    }
    public void ButtonClickMale(View view) {
        btn_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Customer_Category_selection.this, Admin_User_Male_Product_Category.class);
                Toast.makeText(getApplicationContext(),"Navigating to Male Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
    public void ButtonClickFemale(View view) {
        btn_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Customer_Category_selection.this, Admin_User_Female_Product_Category.class);
                Toast.makeText(getApplicationContext(),"Navigating to Female Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    public void ButtonClickBabyAndKids(View view) {
        btn_babyAndKids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Customer_Category_selection.this, Admin_Baby_kids_product_category.class);
                Toast.makeText(getApplicationContext(),"Navigating to Baby and Kids Products Category", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}