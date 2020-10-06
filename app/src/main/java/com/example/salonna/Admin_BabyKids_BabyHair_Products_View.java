package com.example.salonna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Admin_BabyKids_BabyHair_Products_View extends AppCompatActivity {

    Button btn_edit;
    Button btn_add;

    TextView view1;
    TextView view2;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__baby_kids__baby_hair__products__view);

        btn_add = findViewById(R.id.btn_add_baby_hair);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Hair_Product");
    }

    public void EditProductDetailsBabyHair(View view){
        Intent intent = new Intent(Admin_BabyKids_BabyHair_Products_View.this, Edit_Product.class);
        Toast.makeText(getApplicationContext(), "Navigating to Edit Text", Toast.LENGTH_SHORT).show();
        intent.putExtra("text1", view1.getText().toString());
        intent.putExtra("text2", view2.getText().toString());

        startActivity(intent);
    }

    public void AddProductDetailsBabyHair(View view){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_BabyKids_BabyHair_Products_View.this, Add_Product.class);
                intent.putExtra("Category",19); //in order to get in to main Baby and kids category
                intent.putExtra("babyHair",22);
                Toast.makeText(getApplicationContext(),"Navigating to Admin Baby and Kids Hair Products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<BabyAndKids_Hair_Products> options = new FirebaseRecyclerOptions.Builder<BabyAndKids_Hair_Products>().setQuery(databaseReference, BabyAndKids_Hair_Products.class).build();

        FirebaseRecyclerAdapter<BabyAndKids_Hair_Products,Admin_BabyAndKids_baby_Hair_ViewHolder> adapter = new FirebaseRecyclerAdapter<BabyAndKids_Hair_Products, Admin_BabyAndKids_baby_Hair_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Admin_BabyAndKids_baby_Hair_ViewHolder holder, int position, @NonNull BabyAndKids_Hair_Products model) {
                holder.tw_title.setText( "Name: " + model.getName());
                holder.tw_brand.setText("Brand: " + model.getBrand());
                holder.tw_price.setText("Price: " + "Rs. " +model.getPrice());
                Picasso.get().load(model.getImageId()).into(holder.imageView);
                holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Admin_BabyKids_BabyHair_Products_View.this, Edit_Product.class);
                        Toast.makeText(Admin_BabyKids_BabyHair_Products_View.this, "Navigating to Edit page", Toast.LENGTH_SHORT).show();
                        intent.putExtra("IDBabyHair", model.getID());
                        intent.putExtra("BabyHair", 5);
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public Admin_BabyAndKids_baby_Hair_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_admin_babyandkids_hair,parent,false);
                Admin_BabyAndKids_baby_Hair_ViewHolder hair_viewHolder = new Admin_BabyAndKids_baby_Hair_ViewHolder(view);
                return hair_viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}