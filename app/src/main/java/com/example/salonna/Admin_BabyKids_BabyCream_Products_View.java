package com.example.salonna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Admin_BabyKids_BabyCream_Products_View extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Button btn_add;
    Button btn_edit;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__baby_kids__baby_cream__products__view);

        btn_add = findViewById(R.id.btn_add_baby_cream);
        btn_edit = findViewById(R.id.btn_edit_cream);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Cream_Product");

    }

    public void AddProductDetailsBabyCream(View view){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_BabyKids_BabyCream_Products_View.this, Add_Product.class);
                intent.putExtra("Category",19); //in order to get in to main Baby and kids category
                Toast.makeText(getApplicationContext(),"Navigating to Add products Category View", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<BabyAndKids_Cream_Products> options = new FirebaseRecyclerOptions.Builder<BabyAndKids_Cream_Products>().setQuery(databaseReference, BabyAndKids_Cream_Products.class).build();

        FirebaseRecyclerAdapter<BabyAndKids_Cream_Products,Admin_BabyAndKids_baby_Cream_ViewHolder> adapter = new FirebaseRecyclerAdapter<BabyAndKids_Cream_Products, Admin_BabyAndKids_baby_Cream_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Admin_BabyAndKids_baby_Cream_ViewHolder holder, int position, @NonNull BabyAndKids_Cream_Products model) {
                holder.tw_title.setText( "Name: " + model.getName());
                holder.tw_brand.setText("Brand: " + model.getBrand());
                holder.tw_price.setText("Price: " +(int) model.getPrice() + " $");
                Picasso.get().load(model.getImageId()).into(holder.imageView);
                holder.ViewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Admin_BabyKids_BabyCream_Products_View.this, Edit_Product.class);
                        intent.putExtra("ID",model.getID());
                        Toast.makeText(Admin_BabyKids_BabyCream_Products_View.this, "Navigating to edit product", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });
                holder.Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FuckMyass(model.getID());

                    }
                });
            }

            @NonNull
            @Override
            public Admin_BabyAndKids_baby_Cream_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_row_babyandkids_baby_cream,parent,false);
                Admin_BabyAndKids_baby_Cream_ViewHolder cream_viewHolder = new Admin_BabyAndKids_baby_Cream_ViewHolder(view);
                return cream_viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public void FuckMyass(String ID){
        DatabaseReference delteref= FirebaseDatabase.getInstance().getReference().child("BabyAndKids_Cream_Product").child(ID);
        delteref.removeValue();
        Toast.makeText(this, "Fuck My ass, Fuk your dick", Toast.LENGTH_SHORT).show();

    }
}