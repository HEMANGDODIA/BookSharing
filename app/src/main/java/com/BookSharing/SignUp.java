package com.BookSharing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity  implements View.OnClickListener {

    ProgressBar progressBar;
    EditText editTextEmail,editTextPassword;
    private FirebaseAuth mAuth;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        progressBar=findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.buttonSignup).setOnClickListener(this);
        findViewById(R.id.textView1).setOnClickListener(this);

//        final EditText emailname = findViewById(R.id.editTextEmail);
//        final com.google.android.material.textfield.TextInputEditText fname= findViewById(R.id.textInputEditText3);
//        final com.google.android.material.textfield.TextInputEditText lname= findViewById(R.id.textInputEditText2);
//        final com.google.android.material.textfield.TextInputEditText mno= findViewById(R.id.textInputEditText5);
//        final com.google.android.material.textfield.TextInputEditText cid= findViewById(R.id.textInputEditText6);
//        Button btn_submit = findViewById(R.id.buttonSignup);
//
//        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
//        final DatabaseReference lRef = mRef.child("profile info");



//        btn_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email1 = emailname.getText().toString().trim();
//                String firstname1 =fname.getText().toString().trim();
//                String lastname1 =lname.getText().toString().trim();
//                String mobileno1 =mno.getText().toString().trim();
//                String collegeid1 =cid.getText().toString().trim();
//
//                DatabaseReference nRef = lRef.child(firstname1);
//                nRef.child("email").setValue(email1);
//                nRef.child("firstname").setValue(firstname1);
//                nRef.child("lastname").setValue(lastname1);
//                nRef.child("mobile number").setValue(mobileno1);
//                nRef.child("college id").setValue(collegeid1);
//            }
//        });
    }

//    void fun(){
//        final EditText emailname = findViewById(R.id.editTextEmail);
//        final com.google.android.material.textfield.TextInputEditText fname= findViewById(R.id.textInputEditText3);
//        final com.google.android.material.textfield.TextInputEditText lname= findViewById(R.id.textInputEditText2);
//        final com.google.android.material.textfield.TextInputEditText mno= findViewById(R.id.textInputEditText5);
//        final com.google.android.material.textfield.TextInputEditText cid= findViewById(R.id.textInputEditText6);
//    }

    @Override
    public void onClick(View v) {
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference lRef = mRef.child("profile info");
        final EditText emailname = findViewById(R.id.editTextEmail);
        final com.google.android.material.textfield.TextInputEditText fname= findViewById(R.id.textInputEditText3);
        final com.google.android.material.textfield.TextInputEditText lname= findViewById(R.id.textInputEditText2);
        final com.google.android.material.textfield.TextInputEditText mno= findViewById(R.id.textInputEditText5);
        final com.google.android.material.textfield.TextInputEditText cid= findViewById(R.id.textInputEditText6);
        String email1 = emailname.getText().toString().trim();
        String firstname1 =fname.getText().toString().trim();
        String lastname1 =lname.getText().toString().trim();
        String mobileno1 =mno.getText().toString().trim();
        String collegeid1 =cid.getText().toString().trim();

        DatabaseReference nRef = lRef.child(firstname1);
        nRef.child("email").setValue(email1);
        nRef.child("firstname").setValue(firstname1);
        nRef.child("lastname").setValue(lastname1);
        nRef.child("mobile number").setValue(mobileno1);
        nRef.child("college id").setValue(collegeid1);
        switch (v.getId())
        {
            case R.id.buttonSignup:
                registerUser();
                break;

            case R.id.textView1:
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    private void registerUser() {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
          editTextEmail.setError("Please enter a valid Email");
          editTextEmail.requestFocus();
          return;
        }
        if(password.length()<6)
        {
            editTextPassword.setError("Minimum lenght of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
               if (task.isSuccessful())
               {
                   finish();
                   Intent intent=new Intent(SignUp.this,Profile.class);
                   intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(intent);
               }
               else
               {
                   if (task.getException() instanceof FirebaseAuthUserCollisionException)
                   {
                       Toast.makeText(getApplicationContext(),"You Are Already Registered",Toast.LENGTH_SHORT).show();
                   }else
                   {
                       Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });
    }
}
