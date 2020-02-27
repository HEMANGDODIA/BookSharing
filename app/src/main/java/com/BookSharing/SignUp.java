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
    EditText editTextEmail,editTextPassword,editTextConfirmPassword;
    private FirebaseAuth mAuth;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextConfirmPassword=findViewById(R.id.textInputEditText4);

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
        String uploadId=mRef.push().getKey();

        DatabaseReference nRef = lRef.child(uploadId);
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
        final com.google.android.material.textfield.TextInputEditText fname= findViewById(R.id.textInputEditText3);
        final com.google.android.material.textfield.TextInputEditText lname= findViewById(R.id.textInputEditText2);
        final com.google.android.material.textfield.TextInputEditText mno= findViewById(R.id.textInputEditText5);
        final com.google.android.material.textfield.TextInputEditText cid= findViewById(R.id.textInputEditText6);
        String regexStr = "^[0-9]$";
        String regexStr1 = "^[1721]$";



        String firstname1 =fname.getText().toString().trim();
        String lastname1 =lname.getText().toString().trim();
        String mobileno1 =mno.getText().toString().trim();
        String collegeid1 =cid.getText().toString().trim();
        String Confirmpassword =fname.getText().toString().trim();




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
        if(password.length()<6 )
        {
            editTextPassword.setError("Minimum lenght of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        if(password.isEmpty())
        {

            editTextPassword.setError("Enter Valid Password");
            editTextPassword.requestFocus();
            return;

        }
        if(mno.getText().toString().length()<10 || mobileno1.length()>13 || mobileno1.matches(regexStr)==false  ) {
            Toast.makeText(SignUp.this, "Please enter " + "\n" + " valid phone number", Toast.LENGTH_SHORT).show();
        }
        if(Confirmpassword.isEmpty())
        {
            editTextConfirmPassword.setError("ConfirmPassword is required");
            if (!Confirmpassword.equals(password))
            {
               editTextConfirmPassword.setError("Password do not match");
            }
        }


       /* if (mobileno1.isEmpty())
        {
            mno.setError("Mobile is required");
            if (mobileno1.length()!=10)
            {
                mno.setError("valid Mobile no");
            }
        }*/
        if(cid.getText().toString().length()!=14 || collegeid1.matches(regexStr1)==false  ) {
            Toast.makeText(SignUp.this, "Please enter " + "\n" + " valid College id", Toast.LENGTH_SHORT).show();
        }
        /*if (collegeid1.isEmpty())
        {
            cid.setError("CollegeId is required");
            cid.requestFocus();
            return;
        }*/
        if (firstname1.isEmpty())
        {
            fname.setError("Enter valid Firstname... ");
           if (fname.length()<= 4 && fname.length()>= 12)
           {
               fname.setError("valid name");
           }
        }
        if (lastname1.isEmpty())
        {
            lname.setError("Enter valid LastName");
            if (lname.length()<= 4 && lname.length()>= 12)
            {
                lname.setError("valid name");
            }
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