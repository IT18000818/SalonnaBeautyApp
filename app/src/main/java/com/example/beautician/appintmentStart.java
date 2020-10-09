package com.example.beautician;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class appintmentStart extends AppCompatActivity {


    EditText hourRate;
    Button strt;
    Button end;

    int totalprice,hr,min,sec,rate;

    //Shh means Start hour
    //Smm means Start minute
    //Sss means Start second
    // In Ess,Emm & Ess, E means end

    int Shh,Smm,Sss,Ehh,Emm,Ess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appintment_start);

        strt =(Button) findViewById(R.id.btnSartAppinmt);
        end =(Button) findViewById(R.id.btnEndAppinmt);
        hourRate = findViewById(R.id.hourRate);


        end.setEnabled(false);

        final appointment ap = new appointment();



        strt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal1 = Calendar.getInstance();

                Shh=cal1.get(Calendar.HOUR_OF_DAY);
                Smm=cal1.get(Calendar.MINUTE);
                Sss=cal1.get(Calendar.SECOND);

                strt.setEnabled(false);
                end.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_SHORT).show();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal2 = Calendar.getInstance();

                Ehh=cal2.get(Calendar.HOUR_OF_DAY);
                Emm=cal2.get(Calendar.MINUTE);
                Ess=cal2.get(Calendar.SECOND);

                hr = (Ehh - Shh);
                min = (Emm - Smm);
                sec = (Ess - Sss);

                if (sec < 0 && min > 1) {
                    sec = (60 - Sss) + Ess;
                    min = min - 1;

                } else if (sec < 0 && min == 1) {
                    sec = (60 - Sss) + Ess;
                    min = 0;
                }
                else if (min < 0) {
                    min = (60 - Smm) + Emm;
                    hr = 0;
                } else if (hr < 0) {
                    hr = (24 - Shh) + Ehh;
                }

                ap.setHrs(hr);
                ap.setMins(min);
                ap.setSeconds(sec);

                rate = Integer.parseInt(hourRate.getText().toString());


                totalprice = (hr * rate ) + ((min * rate) / 60) + ((sec * rate) / 3600);

                ap.setTotalprice(totalprice);


                Toast.makeText(getApplicationContext(), "Ended", Toast.LENGTH_SHORT).show();


                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(appintmentStart.this);
                builder.setTitle("Thank You!");
                builder.setMessage(" Hours: "+hr+" Minutes: "+min+" Seconds: "+sec+" \n \n Totalprice is: "+totalprice+" LKR" );
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(appintmentStart.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });


    }
}