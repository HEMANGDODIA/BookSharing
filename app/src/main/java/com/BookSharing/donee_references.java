package com.BookSharing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class donee_references extends AppCompatActivity {
    FirebaseAuth mAuth;
    static final String[] selectsubject = new String[]{"Maths","C language","Physics","Environmental Studies","Civil","Engineering Graphics"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donee_references);

        final Spinner spinner4 = findViewById(R.id.spinner4);
        ArrayAdapter<String> adepter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,selectsubject);
        spinner4.setAdapter(adepter);

        mAuth = FirebaseAuth.getInstance();
    }
};
