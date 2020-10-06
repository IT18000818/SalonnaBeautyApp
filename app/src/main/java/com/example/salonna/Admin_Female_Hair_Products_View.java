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
import com.squareup.picasso.Picasso;

public class Admin_Female_Hair_Products_View extends AppCompatActivity {

    Button btn_add;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__female__hair__products__view);
        btn_add = (Button) findViewById(R.id.btn_add_female_hair);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Female_Hair_Product");
    }

    public void ButtonClickAddFemaleHair(View view) {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Female_Hair_Products_View.this, Add_Product.class);
                intent.putExtra("Category",17); //in order to get in to main female category
                intent.putExtra("femaleHair",3);    //in order to validate female hair
                Toast.makeText(getApplicationContext(),"Navigating to Add Products",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Female_Hair_Product> options = new FirebaseRecyclerOptions.Builder<Female_Hair_Product>().setQuery(databaseReference, Female_Hair_Product.class).build();

        FirebaseRecyclerAdapter<Female_Hair_Product,Admin_Female_hair_ViewHolder> adapter = new FirebaseRecyclerAdapter<Female_Hair_Product, Admin_Female_hair_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Admin_Female_hair_ViewHolder holder, int position, @NonNull Female_Hair_Product model) {
                holder.tw_title.setText( "Name: " + model.getName());
                holder.tw_brand.setText("Brand: " + model.getBrand());
                holder.tw_price.setText("Price: " + "Rs. " + model.getPrice());
                Picasso.get().load(model.getImageId()).into(holder.imageView);
                holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Admin_Female_Hair_Products_View.this, Edit_Product.class);
                        intent.putExtra("IDFemaleHair",model.getID());
                        intent.putExtra("FemaleHair",4);
                        Toast.makeText(Admin_Female_Hair_Products_View.this, "Navigating to edit product", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public Admin_Female_hair_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_admin_female_hair,parent,false);
                Admin_Female_hair_ViewHolder hair_viewHolder = new Admin_Female_hair_ViewHolder(view);
                return hair_viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}