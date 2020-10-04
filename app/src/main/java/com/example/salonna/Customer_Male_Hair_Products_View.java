package com.example.salonna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Customer_Male_Hair_Products_View extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__male__hair__products__view);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Male_Hair_Product");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Male_Hair_Product> options = new FirebaseRecyclerOptions.Builder<Male_Hair_Product>().setQuery(databaseReference, Male_Hair_Product.class).build();

        FirebaseRecyclerAdapter<Male_Hair_Product,Customer_Male_hair_ViewHolder> adapter = new FirebaseRecyclerAdapter<Male_Hair_Product, Customer_Male_hair_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Customer_Male_hair_ViewHolder holder, int position, @NonNull Male_Hair_Product model) {
                holder.tw_title.setText( "Name: " + model.getName());
                holder.tw_brand.setText("Brand: " + model.getBrand());
                holder.tw_price.setText("Price: " +(int) model.getPrice() + " $");
                Picasso.get().load(model.getImageId()).into(holder.imageView);
            }


            @NonNull
            @Override
            public Customer_Male_hair_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_customer_male_hair,parent,false);
                Customer_Male_hair_ViewHolder hair_viewHolder = new Customer_Male_hair_ViewHolder(view);
                return hair_viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}