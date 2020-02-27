package com.BookSharing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class donee_notes extends AppCompatActivity {
    FirebaseAuth mAuth;
    static final String[] choosenotes = new String[]{"Flowchart","Diagram","Practicepaper","Queries"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donee_notes);

        final Spinner spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<String> adepter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,choosenotes);
        spinner3.setAdapter(adepter);

        mAuth = FirebaseAuth.getInstance();
    }
};
