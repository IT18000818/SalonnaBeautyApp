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

public class Customer_BabyKids_BabyCream_Products_View extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__baby_kids__baby_cream__products__view);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Cream_Product");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<BabyAndKids_Cream_Products> options = new FirebaseRecyclerOptions.Builder<BabyAndKids_Cream_Products>().setQuery(databaseReference, BabyAndKids_Cream_Products.class).build();

        FirebaseRecyclerAdapter<BabyAndKids_Cream_Products,Customer_BabyAndKids_cream_ViewHolder> adapter = new FirebaseRecyclerAdapter<BabyAndKids_Cream_Products, Customer_BabyAndKids_cream_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Customer_BabyAndKids_cream_ViewHolder holder, int position, @NonNull BabyAndKids_Cream_Products model) {
                holder.tw_title.setText( "Name: " + model.getName());
                holder.tw_brand.setText("Brand: " + model.getBrand());
                holder.tw_price.setText("Price: " +(int) model.getPrice() + " $");
                Picasso.get().load(model.getImageId()).into(holder.imageView);
            }

            @NonNull
            @Override
            public Customer_BabyAndKids_cream_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_customer_babyandkids_cream,parent,false);
                Customer_BabyAndKids_cream_ViewHolder cream_viewHolder = new Customer_BabyAndKids_cream_ViewHolder(view);
                return cream_viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}