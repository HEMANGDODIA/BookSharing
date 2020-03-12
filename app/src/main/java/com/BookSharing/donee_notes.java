package com.BookSharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.BookSharing.ui.Notesdata;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class donee_notes extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String CurrentUser_ID;
    Spinner spinner1;
    private RecyclerView recyclerView;
    private NotesAdapter mAdapter;
    static final String[] choosenotes = new String[]{"Flowchart", "Diagram", "Practicepaper", "Queries"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donee_notes);

        final Spinner spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<String> adepter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, choosenotes);
        spinner3.setAdapter(adepter);

        mAuth = FirebaseAuth.getInstance();

        spinner1 = findViewById(R.id.spinner1);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ongetData();
    }

    private void ongetData() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        CurrentUser_ID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.child("Notes").child(CurrentUser_ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                long value = dataSnapshot.getChildrenCount();

                List<Notesdata> tDlist = new ArrayList<>();

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Notesdata tD = d.getValue(Notesdata.class);
                    tDlist.add(tD);
                }
                mAdapter = new NotesAdapter(tDlist);
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
}

