package com.example.salonna;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    String mProductName;
    String mProductBrand;
    String mProductPrice;

    StorageReference storageReference;

    StorageTask UploadTask;

    public Uri imageUri;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__product);

        productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("ID");
        Save=findViewById(R.id.button2);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateProducts();
            }
        });
        DatabaseReference readref=FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Cream_Product").child(ID);

        readref.addValueEventListener(new ValueEventListener() {
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


        //--------------------------------------------------------------

        /*productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("ID");
        Save=findViewById(R.id.button2);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateProducts();
            }
        });
        DatabaseReference readref=FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Cream_Product").child(ID);

        readref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productName.setText(snapshot.child("name").getValue().toString());
                brand.setText(snapshot.child("brand").getValue().toString());
                price.setText(snapshot.child("price").getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        //--------------------------------------------------------------
        /*mProductName = intent.getStringExtra("male_body_1");
        mProductBrand = intent.getStringExtra("male_body_2");
        mProductPrice = intent.getStringExtra("male_body_3");

        productName.setText(mProductName);
        brand.setText(mProductBrand);
        price.setText(mProductPrice);*/

    }
    @Override
    protected void onStart() {
        super.onStart();



    }

    public void UpdateProducts(){

        productName = (EditText) findViewById(R.id.edit_product_Name);
        brand = (EditText) findViewById(R.id.edit_product_Brand);
        price = (EditText) findViewById(R.id.edit_product_Price);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("ID");

        DatabaseReference UodateRef=FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Cream_Product").child(ID);
        BabyAndKids_Cream_Products d1= new BabyAndKids_Cream_Products();
        d1.setBrand(brand.getText().toString());
        d1.setPrice(22);
        d1.setName(productName.getText().toString());
        d1.setImageId("asdasd");
        d1.setID(ID);
        UodateRef.setValue(d1);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();


    }
    /* User Categorization method */
    /*public void SelectUserCategory(int category, int maleHair, int femaleHair, int babyHair, int babyWash) {
        if (category == 18) {
            Toast.makeText(this, "male", Toast.LENGTH_SHORT);
            //male body
            if (maleHair == 5) {
                final Male_Body_Product product = new Male_Body_Product();

                btn_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChooseFile();
                    }
                });

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference().child("Male_Body_Product");

                        *//* Validations for Adding male body product *//*

                        if (editText1.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText2.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText3.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (imageUri == null) {
                            Toast.makeText(Add_Product.this, "Please upload your image!!! ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (UploadTask != null && UploadTask.isInProgress()){
                            Toast.makeText(Add_Product.this, "Please wait your image is being uploaded", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String ImageId;
                            ImageId = System.currentTimeMillis() + "." + GetExtension(imageUri);
                            Double price = Double.parseDouble(editText3.getText().toString().trim());
                            product.setImageId(ImageId);
                            product.setName(editText1.getText().toString().trim());
                            product.setBrand(editText2.getText().toString().trim());
                            product.setPrice(price);
                            Toast.makeText(getApplicationContext(), "Product Added Successfully to Male Body Category", Toast.LENGTH_SHORT).show();

                            databaseReference.push().setValue(product);
                            clearDataAfterInsert();
                            final StorageReference reference = storageReference.child(ImageId);

                            UploadTask = reference.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            Toast.makeText(Add_Product.this,"Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle unsuccessful uploads
                                            Toast.makeText(Add_Product.this,"Image uploaded Unsuccessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });


            } else {
                final Male_Hair_Product product = new Male_Hair_Product();

                btn_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChooseFile();
                    }
                });

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference().child("Male_Hair_Product");

                        *//* Validations for Adding male hair product *//*

                        if (editText1.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText2.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText3.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (imageUri == null) {
                            Toast.makeText(Add_Product.this, "Please upload your image!!! ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (UploadTask != null && UploadTask.isInProgress()){
                            Toast.makeText(Add_Product.this, "Please wait your image is being uploaded", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String ImageId;
                            ImageId = System.currentTimeMillis() + "." + GetExtension(imageUri);
                            Double price = Double.parseDouble(editText3.getText().toString().trim());
                            product.setImageId(ImageId);
                            product.setName(editText1.getText().toString().trim());
                            product.setBrand(editText2.getText().toString().trim());
                            product.setPrice(price);
                            Toast.makeText(getApplicationContext(), "Product Added Successfully to Male Hair Category", Toast.LENGTH_SHORT).show();
                            databaseReference.push().setValue(product);
                            clearDataAfterInsert();
                            final StorageReference reference = storageReference.child(ImageId);

                            UploadTask = reference.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            Toast.makeText(Add_Product.this,"Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle unsuccessful uploads
                                            Toast.makeText(Add_Product.this,"Image uploaded Unsuccessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
            }
        }
        //if female
        else if(category == 17) {
            Toast.makeText(this, "female", Toast.LENGTH_SHORT);
            if (femaleHair == 2) {
                final Female_Body_Product product = new Female_Body_Product();

                btn_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChooseFile();
                    }
                });

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference().child("Female_Body_Product");

                        *//* Validations for Adding female body product *//*
                        if (editText1.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText2.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText3.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (imageUri == null) {
                            Toast.makeText(Add_Product.this, "Please upload your image!!! ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (UploadTask != null && UploadTask.isInProgress()){
                            Toast.makeText(Add_Product.this, "Please wait your image is being uploaded", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String ImageId;
                            ImageId = System.currentTimeMillis() + "." + GetExtension(imageUri);
                            Double price = Double.parseDouble(editText3.getText().toString().trim());
                            product.setImageId(ImageId);
                            product.setName(editText1.getText().toString().trim());
                            product.setBrand(editText2.getText().toString().trim());
                            product.setPrice(price);
                            Toast.makeText(getApplicationContext(), "Product Added Successfully to Female Body Category", Toast.LENGTH_SHORT).show();
                            databaseReference.push().setValue(product);
                            clearDataAfterInsert();
                            final StorageReference reference = storageReference.child(ImageId);

                            UploadTask = reference.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            Toast.makeText(Add_Product.this,"Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle unsuccessful uploads
                                            Toast.makeText(Add_Product.this,"Image uploaded Unsuccessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
            } else {
                final Female_Hair_Product product = new Female_Hair_Product();

                btn_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChooseFile();
                    }
                });

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference().child("Female_Hair_Product");

                        *//* Validations for Adding female hair product *//*
                        if (editText1.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText2.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText3.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (imageUri == null) {
                            Toast.makeText(Add_Product.this, "Please upload your image!!! ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (UploadTask != null && UploadTask.isInProgress()){
                            Toast.makeText(Add_Product.this, "Please wait your image is being uploaded", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String ImageId;
                            ImageId = System.currentTimeMillis() + "." + GetExtension(imageUri);
                            Double price = Double.parseDouble(editText3.getText().toString().trim());
                            product.setImageId(ImageId);
                            product.setName(editText1.getText().toString().trim());
                            product.setBrand(editText2.getText().toString().trim());
                            product.setPrice(price);
                            Toast.makeText(getApplicationContext(), "Product Added Successfully to Female Hair Category", Toast.LENGTH_SHORT).show();
                            databaseReference.push().setValue(product);
                            clearDataAfterInsert();
                            final StorageReference reference = storageReference.child(ImageId);

                            UploadTask = reference.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            Toast.makeText(Add_Product.this,"Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle unsuccessful uploads
                                            Toast.makeText(Add_Product.this,"Image uploaded Unsuccessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
            }
        }
        //if baby and kids
        else {
            if (babyHair == 22){
                final BabyAndKids_Hair_Products product = new BabyAndKids_Hair_Products();

                btn_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChooseFile();
                    }
                });

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference().child("BabyAndKids_Hair_Product");

                        *//* Validations for Adding baby and kids hair product *//*
                        if (editText1.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText2.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText3.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (imageUri == null) {
                            Toast.makeText(Add_Product.this, "Please upload your image!!! ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (UploadTask != null && UploadTask.isInProgress()){
                            Toast.makeText(Add_Product.this, "Please wait your image is being uploaded", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String ImageId;
                            ImageId = System.currentTimeMillis() + "." + GetExtension(imageUri);
                            Double price = Double.parseDouble(editText3.getText().toString().trim());
                            product.setImageId(ImageId);
                            product.setName(editText1.getText().toString().trim());
                            product.setBrand(editText2.getText().toString().trim());
                            product.setPrice(price);
                            Toast.makeText(getApplicationContext(), "Product Added Successfully to Baby and Kids Hair Category", Toast.LENGTH_SHORT).show();
                            databaseReference.push().setValue(product);
                            clearDataAfterInsert();
                            final StorageReference reference = storageReference.child(ImageId);

                            UploadTask = reference.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            Toast.makeText(Add_Product.this,"Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle unsuccessful uploads
                                            Toast.makeText(Add_Product.this,"Image uploaded Unsuccessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
            }else if (babyWash == 23){
                final BabyAndKids_Cream_Products product = new BabyAndKids_Cream_Products();

                btn_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChooseFile();
                    }
                });

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference().child("BabyAndKids_Cream_Product");

                        *//* Validations for Adding baby and kids cream product *//*
                        if (editText1.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText2.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText3.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (imageUri == null) {
                            Toast.makeText(Add_Product.this, "Please upload your image!!! ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (UploadTask != null && UploadTask.isInProgress()){
                            Toast.makeText(Add_Product.this, "Please wait your image is being uploaded", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String ImageId;
                            ImageId = System.currentTimeMillis() + "." + GetExtension(imageUri);
                            Double price = Double.parseDouble(editText3.getText().toString().trim());
                            product.setImageId(ImageId);
                            product.setName(editText1.getText().toString().trim());
                            product.setBrand(editText2.getText().toString().trim());
                            product.setPrice(price);
                            Toast.makeText(getApplicationContext(), "Product Added Successfully to Baby and Kids Cream Category", Toast.LENGTH_SHORT).show();
                            databaseReference.push().setValue(product);
                            clearDataAfterInsert();
                            final StorageReference reference = storageReference.child(ImageId);

                            UploadTask = reference.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            Toast.makeText(Add_Product.this,"Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle unsuccessful uploads
                                            Toast.makeText(Add_Product.this,"Image uploaded Unsuccessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
            } else {
                final BabyAndKids_Wash_Products product = new BabyAndKids_Wash_Products();

                btn_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChooseFile();
                    }
                });

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference().child("BabyAndKids_Wash_Product");

                        *//* Validations for Adding baby and kids wash product *//*
                        if (editText1.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText2.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (editText3.getText().length() == 0){
                            Toast.makeText(Add_Product.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (imageUri == null) {
                            Toast.makeText(Add_Product.this, "Please upload your image!!! ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (UploadTask != null && UploadTask.isInProgress()){
                            Toast.makeText(Add_Product.this, "Please wait your image is being uploaded", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String ImageId;
                            ImageId = System.currentTimeMillis() + "." + GetExtension(imageUri);
                            Double price = Double.parseDouble(editText3.getText().toString().trim());
                            product.setImageId(ImageId);
                            product.setName(editText1.getText().toString().trim());
                            product.setBrand(editText2.getText().toString().trim());
                            product.setPrice(price);
                            Toast.makeText(getApplicationContext(), "Product Added Successfully to Baby and Kids Wash Category", Toast.LENGTH_SHORT).show();
                            databaseReference.push().setValue(product);
                            clearDataAfterInsert();
                            final StorageReference reference = storageReference.child(ImageId);

                            UploadTask = reference.putFile(imageUri)
                                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            // Get a URL to the uploaded content
                                            //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            Toast.makeText(Add_Product.this,"Image uploaded Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle unsuccessful uploads
                                            Toast.makeText(Add_Product.this,"Image uploaded Unsuccessfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
            }
        }

    }*/
}