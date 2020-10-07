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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class Admin_BabyKids_BabyWash_Products_View extends AppCompatActivity {

    Button btn_add;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__baby_kids__baby_wash__products__view);
        btn_add = findViewById(R.id.btn_add_baby_wash);


        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Wash_Product");
    }

    public void AddProductDetailsBabyWash(View view){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_BabyKids_BabyWash_Products_View.this, Add_Product.class);
                intent.putExtra("Category",19); //in order to get in to main Baby and kids category
                intent.putExtra("babyWash",24);
                Toast.makeText(getApplicationContext(),"Navigating to Admin Baby and Kids Wash Products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<BabyAndKids_Wash_Products> options = new FirebaseRecyclerOptions.Builder<BabyAndKids_Wash_Products>().setQuery(databaseReference, BabyAndKids_Wash_Products.class).build();

        FirebaseRecyclerAdapter<BabyAndKids_Wash_Products,Admin_BabyAndKids_baby_Wash_ViewHolder> adapter = new FirebaseRecyclerAdapter<BabyAndKids_Wash_Products, Admin_BabyAndKids_baby_Wash_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Admin_BabyAndKids_baby_Wash_ViewHolder holder, int position, @NonNull BabyAndKids_Wash_Products model) {
                holder.tw_title.setText( "Name: " + model.getName());
                holder.tw_brand.setText("Brand: " + model.getBrand());
                holder.tw_price.setText("Price: " + "Rs. " +model.getPrice());
                Picasso.get().load(model.getImageId()).into(holder.imageView);
                holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Admin_BabyKids_BabyWash_Products_View.this, Edit_Product.class);
                        Toast.makeText(Admin_BabyKids_BabyWash_Products_View.this, "Navigating to Edit page", Toast.LENGTH_SHORT).show();
                        intent.putExtra("IDBabyWash", model.getID());
                        intent.putExtra("BabyWash", 24);
                        intent.putExtra("Category",19); //in order to get in to main Baby and kids category
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public Admin_BabyAndKids_baby_Wash_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_admin_babyandkids_wash,parent,false);
                Admin_BabyAndKids_baby_Wash_ViewHolder cream_viewHolder = new Admin_BabyAndKids_baby_Wash_ViewHolder(view);
                return cream_viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}