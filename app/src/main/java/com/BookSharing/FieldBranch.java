package com.BookSharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class FieldBranch extends AppCompatActivity {
   FirebaseAuth mAuth;

   Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_branch);

        final AutoCompleteTextView actv1 = (AutoCompleteTextView)findViewById(R.id.actv1);
        final AutoCompleteTextView actv2 = (AutoCompleteTextView)findViewById(R.id.actv2);
        final AutoCompleteTextView actv3 = (AutoCompleteTextView)findViewById(R.id.actv3);
        final AutoCompleteTextView actv4 = (AutoCompleteTextView)findViewById(R.id.actv4);
        ImageView image = (ImageView)findViewById(R.id.image);
        ImageView image2 = (ImageView)findViewById(R.id.image2);
        ImageView image3 = (ImageView)findViewById(R.id.image3);
        ImageView image4 = (ImageView)findViewById(R.id.image4);
        actv1.setThreshold(2);
        actv2.setThreshold(2);
        actv3.setThreshold(2);

        ArrayAdapter<String>adepter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,field);
        actv1.setAdapter(adepter);
        ArrayAdapter<String>adepter1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,branch);
        actv2.setAdapter(adepter1);
        ArrayAdapter<String> adepter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,year);
        actv3.setAdapter(adepter2);
        ArrayAdapter<Integer>adepter3 = new ArrayAdapter<Integer>(this,android.R.layout.simple_dropdown_item_1line,semester);
        actv4.setAdapter(adepter3);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv1.showDropDown();
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv2.showDropDown();
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv3.showDropDown();
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv4.showDropDown();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        button=findViewById(R.id.SubmitField);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FieldBranch.this,Choice.class);
                startActivity(intent);

            }
        });

    }
    private static final String[] field = new String[]{"ENGINEERING","BSC","DIPLOMA"};
    private static final String[] branch = new String[]{"COMPUTER","MECHANICAL","PHYSICS","CIVIL","CHEMISRTY","ELECTRICAL","IT","MATHS","AUTO"};
    private static final String[] year = new String[]{"1st","2nd","3rd","4th"};
    private static final Integer[] semester = new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4),
            Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8)};
}
