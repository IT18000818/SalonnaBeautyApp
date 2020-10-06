package com.example.salonna;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Add_Product extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;

    Button btn_choose,btn_add;
    ImageView imageView;
    StorageReference storageReference;

    StorageTask UploadTask;

    public Uri imageUri;

    String downloadImageURL, productName, brand, price;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);

        Intent intent = getIntent();
        int category = intent.getIntExtra("Category", 18);

        int cater_user_male = intent.getIntExtra("hair", 5);

        int cater_user_female = intent.getIntExtra("femaleHair", 2);

        int babyHair = intent.getIntExtra("babyHair",21);

        int babyWash = intent.getIntExtra("babyWash",23);

        /* Storage instance initialization */
        storageReference = FirebaseStorage.getInstance().getReference().child("Images");

        /* Edit text declaration */
        editText1 = (EditText) findViewById(R.id.editTextTextPersonName2);
        editText2 = (EditText) findViewById(R.id.editTextTextPersonName3);
        editText3 = (EditText) findViewById(R.id.editTextNumberDecimal);

        /* Button declaration */
        btn_choose = findViewById(R.id.choose_image);
        btn_add = findViewById(R.id.upload_image);

        /* ImageView declaration */
        imageView = findViewById(R.id.product_imageView);

        /* Category validation method*/
        SelectUserCategory(category, cater_user_male, cater_user_female,babyHair,babyWash);

    }

    /* Image get Extension */
    public String GetExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    /* Choose image file function */
    public void ChooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    public void SelectUserCategory(int category, int maleHair, int femaleHair, int babyHair, int babyWash) {
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

                        /* Validations for Adding male body product */

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
                            DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                            readref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String ID= snapshot.child("IntitalVal").getValue().toString();
                                    int IDx;
                                    IDx=Integer.parseInt(ID);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    double price = Double.parseDouble(editText3.getText().toString());
                                    product.setID(ID);
                                    product.setImageId(downloadImageURL);
                                    product.setName(editText1.getText().toString().trim());
                                    product.setBrand(editText2.getText().toString().trim());
                                    product.setPrice(price);
                                    Toast.makeText(getApplicationContext(), "Product Added Successfully to Male body Category", Toast.LENGTH_SHORT).show();
                                    DatabaseReference Insertref2=FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                                    databaseReference.child(ID).setValue(product);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    Insertref2.child("IntitalVal").setValue(ID);

                                    clearDataAfterInsert();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

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

                        /* Validations for Adding male hair product */

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
                            DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                            readref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String ID= snapshot.child("IntitalVal").getValue().toString();
                                    int IDx;
                                    IDx=Integer.parseInt(ID);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    double price = Double.parseDouble(editText3.getText().toString());
                                    product.setID(ID);
                                    product.setImageId(downloadImageURL);
                                    product.setName(editText1.getText().toString().trim());
                                    product.setBrand(editText2.getText().toString().trim());
                                    product.setPrice(price);
                                    Toast.makeText(getApplicationContext(), "Product Added Successfully to Male hair Category", Toast.LENGTH_SHORT).show();
                                    DatabaseReference Insertref2=FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                                    databaseReference.child(ID).setValue(product);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    Insertref2.child("IntitalVal").setValue(ID);

                                    clearDataAfterInsert();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

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

                        /* Validations for Adding female body product */
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
                            DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                            readref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String ID= snapshot.child("IntitalVal").getValue().toString();
                                    int IDx;
                                    IDx=Integer.parseInt(ID);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    double price = Double.parseDouble(editText3.getText().toString());
                                    product.setID(ID);
                                    product.setImageId(downloadImageURL);
                                    product.setName(editText1.getText().toString().trim());
                                    product.setBrand(editText2.getText().toString().trim());
                                    product.setPrice(price);
                                    Toast.makeText(getApplicationContext(), "Product Added Successfully to Female body Category", Toast.LENGTH_SHORT).show();
                                    DatabaseReference Insertref2=FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                                    databaseReference.child(ID).setValue(product);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    Insertref2.child("IntitalVal").setValue(ID);

                                    clearDataAfterInsert();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

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

                        /* Validations for Adding female hair product */
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
                            DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                            readref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String ID= snapshot.child("IntitalVal").getValue().toString();
                                    int IDx;
                                    IDx=Integer.parseInt(ID);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    double price = Double.parseDouble(editText3.getText().toString());
                                    product.setID(ID);
                                    product.setImageId(downloadImageURL);
                                    product.setName(editText1.getText().toString().trim());
                                    product.setBrand(editText2.getText().toString().trim());
                                    product.setPrice(price);
                                    Toast.makeText(getApplicationContext(), "Product Added Successfully to Female hair Category", Toast.LENGTH_SHORT).show();
                                    DatabaseReference Insertref2=FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                                    databaseReference.child(ID).setValue(product);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    Insertref2.child("IntitalVal").setValue(ID);

                                    clearDataAfterInsert();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

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

                        /* Validations for Adding baby and kids hair product */
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
                            DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                            readref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String ID= snapshot.child("IntitalVal").getValue().toString();
                                    int IDx;
                                    IDx=Integer.parseInt(ID);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    double price = Double.parseDouble(editText3.getText().toString());
                                    product.setID(ID);
                                    product.setImageId(downloadImageURL);
                                    product.setName(editText1.getText().toString().trim());
                                    product.setBrand(editText2.getText().toString().trim());
                                    product.setPrice(price);
                                    Toast.makeText(getApplicationContext(), "Product Added Successfully to Baby and kids hair Category", Toast.LENGTH_SHORT).show();
                                    DatabaseReference Insertref2=FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                                    databaseReference.child(ID).setValue(product);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    Insertref2.child("IntitalVal").setValue(ID);

                                    clearDataAfterInsert();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

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

                        //ValidateProduct();
                        // Validations for Adding baby and kids cream product */
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
                            DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                            readref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String ID= snapshot.child("IntitalVal").getValue().toString();
                                    int IDx;
                                    IDx=Integer.parseInt(ID);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    double price = Double.parseDouble(editText3.getText().toString());
                                    product.setID(ID);
                                    product.setImageId(downloadImageURL);
                                    product.setName(editText1.getText().toString().trim());
                                    product.setBrand(editText2.getText().toString().trim());
                                    product.setPrice(price);
                                    Toast.makeText(getApplicationContext(), "Product Added Successfully to Baby and kids cream Category", Toast.LENGTH_SHORT).show();
                                    DatabaseReference Insertref2=FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                                    databaseReference.child(ID).setValue(product);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    Insertref2.child("IntitalVal").setValue(ID);

                                    clearDataAfterInsert();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

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

                        /* Validations for Adding baby and kids wash product */
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
                            DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                            readref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String ID= snapshot.child("IntitalVal").getValue().toString();
                                    int IDx;
                                    IDx=Integer.parseInt(ID);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    double price = Double.parseDouble(editText3.getText().toString());
                                    product.setID(ID);
                                    product.setImageId(downloadImageURL);
                                    product.setName(editText1.getText().toString().trim());
                                    product.setBrand(editText2.getText().toString().trim());
                                    product.setPrice(price);
                                    Toast.makeText(getApplicationContext(), "Product Added Successfully to Baby and kids wash Category", Toast.LENGTH_SHORT).show();
                                    DatabaseReference Insertref2=FirebaseDatabase.getInstance().getReference().child("ProductIntital").child("Initta");
                                    databaseReference.child(ID).setValue(product);
                                    IDx++;
                                    ID=Integer.toString(IDx);
                                    Insertref2.child("IntitalVal").setValue(ID);

                                    clearDataAfterInsert();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                });
            }
        }

    }

    public void clearDataAfterInsert(){
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        imageView.setImageURI(null);
    }
}