package com.BookSharing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class donee_stationary extends AppCompatActivity {
    FirebaseAuth mAuth;
    static final String[] selmaterial = new String[]{"T square","Compass","Drawing Sheet","Mini Drafter","Templates"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donee_stationary);

        final Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adepter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,selmaterial);
        spinner2.setAdapter(adepter);

        mAuth = FirebaseAuth.getInstance();
    }
};
