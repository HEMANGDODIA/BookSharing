package com.BookSharing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class donee_books extends AppCompatActivity {
    FirebaseAuth mAuth;
    static final String[] selectbook = new String[]{"Database Management System","Data Structure&Algorithm","Java","Operating System"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donee_books);

        final Spinner spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adepter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,selectbook);
        spinner1.setAdapter(adepter);

        mAuth = FirebaseAuth.getInstance();
    }

};
