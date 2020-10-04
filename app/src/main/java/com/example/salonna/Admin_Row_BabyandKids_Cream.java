package com.example.salonna;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Admin_Row_BabyandKids_Cream extends AppCompatActivity {

    Button btn_edit;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.admin_row_babyandkids_baby_cream);

        btn_edit = findViewById(R.id.btn_edit_cream);
    }

    public void EditDetails(View view){
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Row_BabyandKids_Cream.this, Edit_Product.class);
                startActivity(intent);
            }
        });
    }

}
