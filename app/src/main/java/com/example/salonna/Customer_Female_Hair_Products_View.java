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

public class Customer_Female_Hair_Products_View extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__female__hair__products__view);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Female_Hair_Product");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Female_Hair_Product> options = new FirebaseRecyclerOptions.Builder<Female_Hair_Product>().setQuery(databaseReference, Female_Hair_Product.class).build();

        FirebaseRecyclerAdapter<Female_Hair_Product,Customer_Female_hair_ViewHolder> adapter = new FirebaseRecyclerAdapter<Female_Hair_Product, Customer_Female_hair_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Customer_Female_hair_ViewHolder holder, int position, @NonNull Female_Hair_Product model) {
                holder.tw_title.setText( "Name: " + model.getName());
                holder.tw_brand.setText("Brand: " + model.getBrand());
                holder.tw_price.setText("Price: " + "Rs. " + model.getPrice());
                Picasso.get().load(model.getImageId()).into(holder.imageView);
            }

            @NonNull
            @Override
            public Customer_Female_hair_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_customer_female_hair,parent,false);
                Customer_Female_hair_ViewHolder hair_viewHolder = new Customer_Female_hair_ViewHolder(view);
                return hair_viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}