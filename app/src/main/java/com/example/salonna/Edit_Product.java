package com.example.salonna;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Edit_Product extends AppCompatActivity {

    EditText productName;
    EditText brand;
    EditText price;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__product);

        Intent intent = getIntent();
        int category = intent.getIntExtra("Category", 18);
        int male_hair = intent.getIntExtra("MaleHair",2);
        int female_hair = intent.getIntExtra("FemaleHair",3);
        int baby_hair = intent.getIntExtra("BabyHair",21);
        int baby_wash = intent.getIntExtra("BabyWash",22);

        /* Category validation method*/
        SelectUserCategory(category,male_hair, female_hair,baby_hair,baby_wash);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    /*update male hair products*/
    public void UpdateProductsMaleHair(){

        productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("IDMaleHair");

        DatabaseReference UpdateRef=FirebaseDatabase.getInstance().getReference().child("Male_Hair_Product").child(ID);
        Male_Hair_Product d1= new Male_Hair_Product();
        d1.setBrand(brand.getText().toString());
        d1.setPrice(Double.parseDouble(price.getText().toString()));
        d1.setName(productName.getText().toString());
        d1.setID(ID);
        UpdateRef.setValue(d1);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        Intent il = new Intent(Edit_Product.this,Admin_Male_Hair_Products_View.class);
        Toast.makeText(this, "Back to all products", Toast.LENGTH_SHORT).show();
        startActivity(il);
    }


    /*update male body products*/
    public void UpdateProductsMaleBody(){

        productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("IDMaleBody");

        DatabaseReference UpdateRef=FirebaseDatabase.getInstance().getReference().child("Male_Body_Product").child(ID);
        Male_Body_Product d1= new Male_Body_Product();
        d1.setBrand(brand.getText().toString());
        d1.setPrice(Double.parseDouble(price.getText().toString()));
        d1.setName(productName.getText().toString());
        d1.setID(ID);
        UpdateRef.setValue(d1);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        Intent il = new Intent(Edit_Product.this,Admin_Male_Body_Products_View.class);
        Toast.makeText(this, "Back to all products", Toast.LENGTH_SHORT).show();
        startActivity(il);
    }

    /*update female hair products*/
    public void UpdateProductsFemaleHair(){

        productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("IDFemaleHair");

        DatabaseReference UpdateRef=FirebaseDatabase.getInstance().getReference().child("Female_Hair_Product").child(ID);
        Female_Hair_Product d1= new Female_Hair_Product();
        d1.setBrand(brand.getText().toString());
        d1.setPrice(Double.parseDouble(price.getText().toString()));
        d1.setName(productName.getText().toString());
        d1.setID(ID);
        UpdateRef.setValue(d1);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        Intent il = new Intent(Edit_Product.this,Admin_Female_Hair_Products_View.class);
        Toast.makeText(this, "Back to all products", Toast.LENGTH_SHORT).show();
        startActivity(il);
    }

    /*update female body products*/
    public void UpdateProductsFemaleBody(){

        productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("IDFemaleBody");

        DatabaseReference UpdateRef=FirebaseDatabase.getInstance().getReference().child("Female_Body_Product").child(ID);
        Female_Body_Product d1= new Female_Body_Product();
        d1.setBrand(brand.getText().toString());
        d1.setPrice(Double.parseDouble(price.getText().toString()));
        d1.setName(productName.getText().toString());
        d1.setID(ID);
        UpdateRef.setValue(d1);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        Intent il = new Intent(Edit_Product.this,Admin_Female_Body_Products_View.class);
        Toast.makeText(this, "Back to all products", Toast.LENGTH_SHORT).show();
        startActivity(il);
    }


    /*update baby cream products*/
    public void UpdateProductsBabyCream(){

        productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("IDBabyCream");

        DatabaseReference UpdateRef=FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Cream_Product").child(ID);
        BabyAndKids_Cream_Products d1= new BabyAndKids_Cream_Products();
        d1.setBrand(brand.getText().toString());
        d1.setPrice(Double.parseDouble(price.getText().toString()));
        d1.setName(productName.getText().toString());
        d1.setID(ID);
        UpdateRef.setValue(d1);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        Intent il = new Intent(Edit_Product.this,Admin_BabyKids_BabyCream_Products_View.class);
        Toast.makeText(this, "Back to all products", Toast.LENGTH_SHORT).show();
        startActivity(il);
    }

    /*update baby hair products*/
    public void UpdateProductsBabyHair(){
        productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("IDBabyHair");

        DatabaseReference UpdateRef=FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Hair_Product").child(ID);
        BabyAndKids_Hair_Products d1= new BabyAndKids_Hair_Products();
        d1.setBrand(brand.getText().toString());
        d1.setPrice(Double.parseDouble(price.getText().toString()));
        d1.setName(productName.getText().toString());
        d1.setID(ID);
        UpdateRef.setValue(d1);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        Intent il = new Intent(Edit_Product.this,Admin_BabyKids_BabyHair_Products_View.class);
        Toast.makeText(this, "Back to all products", Toast.LENGTH_SHORT).show();
        startActivity(il);

    }

    /*update baby hair products*/
    public void UpdateProductsBabyWash(){

        productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("IDBabyWash");

        DatabaseReference UpdateRef=FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Wash_Product").child(ID);
        BabyAndKids_Wash_Products d1= new BabyAndKids_Wash_Products();
        d1.setBrand(brand.getText().toString());
        d1.setPrice(Double.parseDouble(price.getText().toString()));
        d1.setName(productName.getText().toString());
        d1.setID(ID);
        UpdateRef.setValue(d1);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        Intent il = new Intent(Edit_Product.this,Admin_BabyKids_BabyWash_Products_View.class);
        Toast.makeText(this, "Back to all products", Toast.LENGTH_SHORT).show();
        startActivity(il);
    }

    /* User Categorization method */
    public void SelectUserCategory(int category,int maleHair, int femaleHair, int babyHair, int babyWash) {
        if (category == 18) {
            Toast.makeText(this, "male", Toast.LENGTH_SHORT);
            /*If male category*/
            if (maleHair == 2) {
                /*If male body category*/

                productName = (EditText) findViewById(R.id.edit_product_Name);
                brand = (EditText) findViewById(R.id.edit_product_Brand);
                price = (EditText) findViewById(R.id.edit_product_Price);
                Intent i1 = getIntent();
                String ID = i1.getStringExtra("IDMaleBody");
                Save = findViewById(R.id.button2);
                Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpdateProductsMaleBody();
                    }
                });
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Male_Body_Product").child(ID);

                readRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Male_Hair_Product m1 = new Male_Hair_Product();
                        productName.setText(snapshot.child("name").getValue().toString());
                        brand.setText(snapshot.child("brand").getValue().toString());
                        price.setText(snapshot.child("price").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            } else {
                /*If male hair category*/

                productName = (EditText) findViewById(R.id.edit_product_Name);
                brand = (EditText) findViewById(R.id.edit_product_Brand);
                price = (EditText) findViewById(R.id.edit_product_Price);
                Intent i1 = getIntent();
                String ID = i1.getStringExtra("IDMaleHair");
                Save = findViewById(R.id.button2);
                Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpdateProductsMaleHair();
                    }
                });
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Male_Hair_Product").child(ID);

                readRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productName.setText(snapshot.child("name").getValue().toString());
                        brand.setText(snapshot.child("brand").getValue().toString());
                        price.setText(snapshot.child("price").getValue().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
            //if female
        } else if (category == 17) {
            /*If female category*/
            if (femaleHair == 3) {
                /*If female body category*/

                productName = (EditText) findViewById(R.id.edit_product_Name);
                brand = (EditText) findViewById(R.id.edit_product_Brand);
                price = (EditText) findViewById(R.id.edit_product_Price);
                Intent i1 = getIntent();
                String ID = i1.getStringExtra("IDFemaleBody");
                Save = findViewById(R.id.button2);
                Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpdateProductsFemaleBody();
                    }
                });
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Female_Body_Product").child(ID);

                readRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productName.setText(snapshot.child("name").getValue().toString());
                        brand.setText(snapshot.child("brand").getValue().toString());
                        price.setText(snapshot.child("price").getValue().toString());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            } else {
                /*If female hair category*/

                productName = (EditText) findViewById(R.id.edit_product_Name);
                brand = (EditText) findViewById(R.id.edit_product_Brand);
                price = (EditText) findViewById(R.id.edit_product_Price);
                Intent i1 = getIntent();
                String ID = i1.getStringExtra("IDFemaleHair");
                Save = findViewById(R.id.button2);
                Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpdateProductsFemaleHair();
                    }
                });
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Female_Hair_Product").child(ID);

                readRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productName.setText(snapshot.child("name").getValue().toString());
                        brand.setText(snapshot.child("brand").getValue().toString());
                        price.setText(snapshot.child("price").getValue().toString());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            //if baby and kids
        } else {
            if (babyHair == 22) {
                /*If baby hair category */

                productName = (EditText) findViewById(R.id.edit_product_Name);
                brand = (EditText) findViewById(R.id.edit_product_Brand);
                price = (EditText) findViewById(R.id.edit_product_Price);
                Intent i1 = getIntent();
                String ID = i1.getStringExtra("IDBabyHair");
                Save = findViewById(R.id.button2);
                Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpdateProductsBabyHair();
                    }
                });
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Hair_Product").child(ID);

                readRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productName.setText(snapshot.child("name").getValue().toString());
                        brand.setText(snapshot.child("brand").getValue().toString());
                        price.setText(snapshot.child("price").getValue().toString());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else if (babyWash == 24) {
                /*If baby wash category */

                productName = (EditText) findViewById(R.id.edit_product_Name);
                brand = (EditText) findViewById(R.id.edit_product_Brand);
                price = (EditText) findViewById(R.id.edit_product_Price);
                Intent i1 = getIntent();
                String ID = i1.getStringExtra("IDBabyWash");
                Save = findViewById(R.id.button2);
                Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpdateProductsBabyWash();
                    }
                });
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Wash_Product").child(ID);

                readRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productName.setText(snapshot.child("name").getValue().toString());
                        brand.setText(snapshot.child("brand").getValue().toString());
                        price.setText(snapshot.child("price").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else {

                /* If baby cream category */

                productName = (EditText) findViewById(R.id.edit_product_Name);
                brand = (EditText) findViewById(R.id.edit_product_Brand);
                price = (EditText) findViewById(R.id.edit_product_Price);
                Intent i1 = getIntent();
                String ID = i1.getStringExtra("IDBabyCream");
                Save = findViewById(R.id.button2);
                Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpdateProductsBabyCream();
                    }
                });
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Cream_Product").child(ID);

                readRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productName.setText(snapshot.child("name").getValue().toString());
                        brand.setText(snapshot.child("brand").getValue().toString());
                        price.setText(snapshot.child("price").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        }
    }


}