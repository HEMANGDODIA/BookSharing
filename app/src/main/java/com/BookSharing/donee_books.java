package com.BookSharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.BookSharing.ui.Bookdata;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class donee_books extends AppCompatActivity {
    FirebaseAuth mAuth;
    static final String[] selectbook = new String[]{"Database Management System","Data Structure&Algorithm","Java","Operating System"};

    DatabaseReference databaseReference;
    String CurrentUser_ID;
    Spinner spinner1;
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donee_books);

        spinner1 = findViewById(R.id.spinner1);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        //ArrayAdapter<String> adepter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,selectbook);
        //spinner1.setAdapter(adepter);

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




                mAdapter = new MoviesAdapter(tDlist);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
//                Log.e("1111111",tDlist.get(0).getUrl());
/*

                for(int i=0;i<tDlist.size();i++){
                    Toast.makeText(donee_books.this,"TaskTitle = "+tDlist.get(i).getAuthersName(),Toast.LENGTH_LONG).show();
                }*/


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
