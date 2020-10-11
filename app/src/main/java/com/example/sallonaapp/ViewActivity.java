package com.example.sallonaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.view.LayoutInflater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Treatment");

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Treatment> options = new FirebaseRecyclerOptions.Builder<Treatment>().setQuery(databaseReference, Treatment.class).build();

        FirebaseRecyclerAdapter<Treatment,Activity5_ViewHolder> adapter = new FirebaseRecyclerAdapter<Treatment, Activity5_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Activity5_ViewHolder holder, int position, @NonNull Treatment model) {
                holder.v_name.setText("Name: " + model.getName());
                holder.v_date.setText("Date: " + model.getDate());
                holder.v_time.setText("Time: " + model.getTime());
                holder.v_phone.setText("Phone: " + model.getPhone());
                holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ViewActivity.this,Activity10.class);
                        Toast.makeText(ViewActivity.this, "Navigating to edit page", Toast.LENGTH_SHORT).show();
                        intent.putExtra("ID",model.getID());
                        startActivity(intent);
                    }
                });
                holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Delete(model.getID());
                    }
                });
            }
            @NonNull
            @Override
            public Activity5_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_row,parent,false);
                Activity5_ViewHolder viewHolder = new Activity5_ViewHolder(view);
                return viewHolder;
            }

        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public void Delete(String ID){
        DatabaseReference deleteRef= FirebaseDatabase.getInstance().getReference().child("Treatment").child(ID);
        deleteRef.removeValue();
        Toast.makeText(this, "Delete was successful", Toast.LENGTH_SHORT).show();
    }
}