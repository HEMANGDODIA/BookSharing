package com.BookSharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choice extends AppCompatActivity {

    Button donee;
    Button donor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        donee=findViewById(R.id.buttonjunior);
        donor=findViewById(R.id.buttonsenior);
        donee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choice.this,HomePage.class);
                startActivity(intent);
            }
        });
        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choice.this,SeniorHome.class);
                startActivity(intent);
            }
        });
    }
}
