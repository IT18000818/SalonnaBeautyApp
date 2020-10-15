package com.example.beautician;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.config.GservicesValue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class beauticianProfile extends AppCompatActivity {

    Button update;
    Button delete;
    Button confirm;

    private static final String TAG ="beauticianProfile";
    String beauticianID;

    EditText NIC, FirstName, LastName,Birthday,Address,Email,Services;
    DatabaseReference dbRef;
    beautician ss;
    String lastkey2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautician_profile);


        NIC = findViewById(R.id.nic1);
        FirstName = findViewById(R.id.fname2);
        LastName = findViewById(R.id.lname2);
        Birthday = findViewById(R.id.birth1);
        Address = findViewById(R.id.address1);
        Email = findViewById(R.id.email1);
        Services = findViewById(R.id.service1);

        final beautician beautician= new beautician();

        update = findViewById(R.id.button6);
        delete = findViewById(R.id.button7);
        confirm= findViewById(R.id.button8);



        dbRef = FirebaseDatabase.getInstance().getReference().child("beautician").child("Lastbeautician");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    NIC.setText(dataSnapshot.child("nic_id").getValue().toString());
                    FirstName.setText(dataSnapshot.child("first_name").getValue().toString());
                    LastName.setText(dataSnapshot.child("last_name").getValue().toString());
                    Birthday.setText(dataSnapshot.child("birthday").getValue().toString());
                    Address.setText(dataSnapshot.child("address").getValue().toString());
                    Email.setText(dataSnapshot.child("email").getValue().toString());
                    Services.setText(dataSnapshot.child("services").getValue().toString());

                }else{
                    Toast.makeText(getApplicationContext(),"No source to display ", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("beautician");
               upref.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       ArrayList<String> arrKeys = new ArrayList();
                       for (DataSnapshot data : dataSnapshot.getChildren()) {

                           arrKeys.add(data.getKey());
                       }

                       String lastKey = arrKeys.get(arrKeys.size() -2);


                       if (dataSnapshot.hasChild(lastKey)){

                           try {
                               /*if (TextUtils.isEmpty(NIC.getText().toString()))
                                   Toast.makeText(getApplicationContext(),"please enter your NIC", Toast.LENGTH_SHORT).show();
                               else if (TextUtils.isEmpty(FirstName.getText().toString()))
                               Toast.makeText(getApplicationContext(), "Please enter your  First Name ", Toast.LENGTH_SHORT).show();
                               else if (TextUtils.isEmpty(LastName.getText().toString()))
                                   Toast.makeText(getApplicationContext(), "Please enter your Last Name ", Toast.LENGTH_SHORT).show();
                               else if (TextUtils.isEmpty(Birthday.getText().toString()))
                                   Toast.makeText(getApplicationContext(), "Please enter your Birthday ", Toast.LENGTH_SHORT).show();
                               else if (TextUtils.isEmpty(Address.getText().toString()))
                                   Toast.makeText(getApplicationContext(), "Please enter your Address ", Toast.LENGTH_SHORT).show();
                               else if (TextUtils.isEmpty(Email.getText().toString()))
                                   Toast.makeText(getApplicationContext(), "Please enter your Email ", Toast.LENGTH_SHORT).show();
                               else if (TextUtils.isEmpty(Services.getText().toString()))
                                   Toast.makeText(getApplicationContext(), "Please enter your Services ", Toast.LENGTH_SHORT).show();

                               else {

                               }*/
                               beautician.setNic_id(NIC.getText().toString().trim());
                               beautician.setFirst_name(FirstName.getText().toString().trim());
                               beautician.setLast_name(LastName.getText().toString().trim());
                               beautician.setBirthday(Birthday.getText().toString().trim());
                               beautician.setAddress(Address.getText().toString().trim());
                               beautician.setEmail(Email.getText().toString().trim());
                               beautician.setServices(Services.getText().toString().trim());

                                //updating one key before lastbeautician
                               DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("beautician").child(lastKey);
                               dbref.setValue(beautician);

                               Toast.makeText(getApplicationContext(),"Updated your profile details Successfully", Toast.LENGTH_SHORT).show();


                           } catch (NumberFormatException e){
                               Toast.makeText(getApplicationContext(),"Number format error", Toast.LENGTH_SHORT).show();
                           }
                       }
                       else{
                           Toast.makeText(getApplicationContext(),"No source", Toast.LENGTH_SHORT).show();
                       }
                       if (dataSnapshot.hasChild("Lastbeautician")){
                           try{
                               beautician.setNic_id(NIC.getText().toString().trim());
                               beautician.setFirst_name(FirstName.getText().toString().trim());
                               beautician.setLast_name(LastName.getText().toString().trim());
                               beautician.setBirthday(Birthday.getText().toString().trim());
                               beautician.setAddress(Address.getText().toString().trim());
                               beautician.setEmail(Email.getText().toString().trim());
                               beautician.setServices(Services.getText().toString().trim());

                               //updating last beautician
                               DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("beautician").child("Lastbeautician");
                               dbref.setValue(beautician);

                               Toast.makeText(getApplicationContext(),"Updated", Toast.LENGTH_SHORT).show();

                           }catch(NumberFormatException e){
                               Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                           }

                       }else{
                           Toast.makeText(getApplicationContext(),"No source", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("beautician");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> datakeys = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()){
                                datakeys.add(data.getKey());
                        }

                        lastkey2 = datakeys.get(datakeys.size()-2);

                        if(dataSnapshot.hasChild(lastkey2)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("beautician").child(lastkey2);
                            dbRef.removeValue();
                        }

                        if(dataSnapshot.hasChild( "Lastbeautician")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("beautician").child("Lastbeautician");
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"No data to delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent( beauticianProfile.this,MainActivity.class );
               startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(beauticianProfile.this);
                builder.setTitle("CONFIRMED");
                builder.setMessage("Your profile details has been confirmed.Thank You!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(beauticianProfile.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });





    }
}