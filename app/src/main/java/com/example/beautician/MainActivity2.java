package com.example.beautician;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set Appoinment selected
        bottomNavigationView.setSelectedItemId(R.id.Appoinment);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.Add:
                        startActivity(new Intent(getApplicationContext()
                            ,Add.class));
                        overridePendingTransition(0,0);
                        return;
                    case R.id.Appoinment:
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