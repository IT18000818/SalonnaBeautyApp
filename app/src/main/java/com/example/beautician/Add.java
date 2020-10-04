package com.example.beautician;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set Appoinment selected
        bottomNavigationView.setSelectedItemId(R.id.Add);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.Add:
                        return;
                    case R.id.Appoinment:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity2.class));
                        overridePendingTransition(0,0);
                        return;
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext()
                                ,Profile.class));
                        overridePendingTransition(0,0);
                        return;

                }
                return;
            }
        });
    }
}