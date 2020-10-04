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

public class Admin_Female_Body_Products_View extends AppCompatActivity {

    Button btn_add;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__female__body__products__view);

        btn_add = (Button)findViewById(R.id.btn_add_female_body);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Female_Body_Product");
    }

    public void ButtonClickAddFemaleBody(View view) {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Female_Body_Products_View.this, Add_Product.class);
                intent.putExtra("Category",17); //in order to get in to main female category
                Toast.makeText(getApplicationContext(),"Navigating to Add Products",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Female_Body_Product> options = new FirebaseRecyclerOptions.Builder<Female_Body_Product>().setQuery(databaseReference, Female_Body_Product.class).build();

        FirebaseRecyclerAdapter<Female_Body_Product,Admin_Female_body_ViewHolder> adapter = new FirebaseRecyclerAdapter<Female_Body_Product, Admin_Female_body_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Admin_Female_body_ViewHolder holder, int position, @NonNull Female_Body_Product model) {
                holder.tw_title.setText( "Name: " + model.getName());
                holder.tw_brand.setText("Brand: " + model.getBrand());
                holder.tw_price.setText("Price: " +(int) model.getPrice() + " $");
                Picasso.get().load(model.getImageId()).into(holder.imageView);
            }



            @NonNull
            @Override
            public Admin_Female_body_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_admin_female_body,parent,false);
                Admin_Female_body_ViewHolder body_viewHolder = new Admin_Female_body_ViewHolder(view);
                return body_viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}