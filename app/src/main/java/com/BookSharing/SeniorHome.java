package com.BookSharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeniorHome extends AppCompatActivity {

    Button button_sBooks;
    Button button_sStationary;
    Button button_sNotes;
    Button button_sReferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_home2);

        init();
        button_sBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SeniorHome.this,BooksSenior.class);
                startActivity(in);
            }
        });
        button_sStationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent in1=new Intent(SeniorHome.this,Material_senior.class);
                 startActivity(in1);


            }
        });
        button_sNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2=new Intent(SeniorHome.this,Notes_senior.class);
                startActivity(in2);


            }
        });
        button_sReferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3=new Intent(SeniorHome.this,ReferenceSenior.class);
                startActivity(in3);
            }
        });






    }

    void init(){
        button_sBooks=(Button)findViewById(R.id.button);
        button_sStationary=(Button)findViewById(R.id.button1);
        button_sNotes=(Button)findViewById(R.id.button2);
        button_sReferences=(Button)findViewById(R.id.button3);

    }
}
