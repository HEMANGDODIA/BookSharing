package com.BookSharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.BookSharing.ui.Bookdata;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class donee_books extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String CurrentUser_ID;
    Spinner spinner1;
    private RecyclerView recyclerView;
    private BookAdapter mAdapter;
    static final String[] selectbook = new String[]{"Database Management System","Data Structure&Algorithm","Java","Operating System"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donee_books);

        final Spinner spinner3 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adepter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,selectbook);
        spinner3.setAdapter(adepter);

        mAuth = FirebaseAuth.getInstance();
        spinner1 = findViewById(R.id.spinner1);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ongetData();
    }

    private void ongetData()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        CurrentUser_ID=FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference.child("books").child(CurrentUser_ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                long value=dataSnapshot.getChildrenCount();

             List<Bookdata> tDlist = new ArrayList<>();

                for (DataSnapshot d: dataSnapshot.getChildren()){
                    Bookdata tD = d.getValue(Bookdata.class);
                    tDlist.add(tD);
                }




                mAdapter = new BookAdapter(tDlist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
               //Log.e("1111111",tDlist.get(0).getUrl());


                /*for(int i=0;i<tDlist.size();i++){
                    Toast.makeText(donee_books.this,"TaskTitle = "+tDlist.get(i).getAuthersName(),Toast.LENGTH_LONG).show();
                }*/


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
