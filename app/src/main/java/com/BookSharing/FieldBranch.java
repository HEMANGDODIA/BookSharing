package com.BookSharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class FieldBranch extends AppCompatActivity {
   FirebaseAuth mAuth;

   Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_branch);

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
}
