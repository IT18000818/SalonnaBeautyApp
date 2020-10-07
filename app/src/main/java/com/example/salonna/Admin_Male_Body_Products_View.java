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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Admin_Male_Body_Products_View extends AppCompatActivity {

    Button btn_add;
    Button btn_edit;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__male__body__products__view);

        btn_add = (Button)findViewById(R.id.btn_add_male_body);
        //btn_edit = (Button)findViewById(R.id.btn_edit);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Male_Body_Product");


        /*databaseReference = FirebaseDatabase.getInstance().getReference().child("Male_Body_Product").child("1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String brand = dataSnapshot.child("brand").getValue().toString();
                String price = dataSnapshot.child("price").getValue().toString();
                textView1.setText(name);
                textView2.setText(brand);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    }

    public void ButtonClickAddMaleBody(View view) {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Male_Body_Products_View.this, Add_Product.class);
                Toast.makeText(getApplicationContext(),"Navigating to Add Products",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Male_Body_Product> options = new FirebaseRecyclerOptions.Builder<Male_Body_Product>().setQuery(databaseReference, Male_Body_Product.class).build();

        FirebaseRecyclerAdapter<Male_Body_Product,Admin_Male_body_ViewHolder> adapter = new FirebaseRecyclerAdapter<Male_Body_Product, Admin_Male_body_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Admin_Male_body_ViewHolder holder, int position, @NonNull Male_Body_Product model) {
                holder.tw_title.setText( "Name: " + model.getName());
                holder.tw_brand.setText("Brand: " + model.getBrand());
                holder.tw_price.setText("Price: " + "Rs. " + model.getPrice());
                holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Admin_Male_Body_Products_View.this, Edit_Product.class);
                        intent.putExtra("IDMaleBody",model.getID());
                        intent.putExtra("MaleBody",3);
                        Toast.makeText(Admin_Male_Body_Products_View.this, "Navigating to edit product", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });

                //Picasso.get().load(model.getImageId()).into(holder.imageView);
            }

            @NonNull
            @Override
            public Admin_Male_body_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_admin_male_body,parent,false);
                Admin_Male_body_ViewHolder body_viewHolder = new Admin_Male_body_ViewHolder(view);
                return body_viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}