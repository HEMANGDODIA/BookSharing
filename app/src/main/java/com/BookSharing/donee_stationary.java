package com.BookSharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.BookSharing.ui.Materialdata;
import com.BookSharing.ui.Notesdata;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class donee_stationary extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    static final String[] selmaterial = new String[]{"T square","Compass","Drawing Sheet","Mini Drafter","Templates"};
    String CurrentUser_ID;
    Spinner spinner1;
    private RecyclerView recyclerView;
    private MaterialAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donee_stationary);

        final Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adepter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,selmaterial);
        spinner2.setAdapter(adepter);

        mAuth = FirebaseAuth.getInstance();
        spinner1 = findViewById(R.id.spinner1);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ongetData();
    }

    private void ongetData() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        CurrentUser_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.child("Material").child(CurrentUser_ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                long value = dataSnapshot.getChildrenCount();

                List<Materialdata> tDlist = new ArrayList<>();

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Materialdata tD = d.getValue(Materialdata.class);
                    tDlist.add(tD);
                }
                mAdapter = new MaterialAdapter(tDlist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
};

