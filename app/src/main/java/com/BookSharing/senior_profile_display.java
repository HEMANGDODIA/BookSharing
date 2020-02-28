package com.BookSharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class senior_profile_display extends AppCompatActivity {

    private static final String TAG ="senior_profile_display" ;
    private FirebaseDatabase mfirebasedatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthstatelistener;
    private DatabaseReference mRef;
    private String uid;
    ListView mlistview;
    TextView fname_t,lname_t,email_t,mobile_t,collegeid_t;
    //ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_profile_display);
        mAuth=FirebaseAuth.getInstance();
        mfirebasedatabase=FirebaseDatabase.getInstance();
        mRef=mfirebasedatabase.getReference();
        FirebaseUser user=mAuth.getCurrentUser();
        uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mlistview=findViewById(R.id.ListView);



        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())

                {
                    Userinformation uinfo=new Userinformation();
                    uinfo.setFirstname(ds.child(uid).getValue(Userinformation.class).getFirstname());
                    uinfo.setEmail(ds.child(uid).getValue(Userinformation.class).getEmail());
                    uinfo.setLastname(ds.child(uid).getValue(Userinformation.class).getLastname());
                    uinfo.setMobilenumber(ds.child(uid).getValue(Userinformation.class).getMobilenumber());
                    uinfo.setCollegeid(ds.child(uid).getValue(Userinformation.class).getCollegeid());
                    Log.d(TAG,""+uinfo.getCollegeid());
                    Log.d(TAG,""+uinfo.getFirstname());
                    Log.d(TAG,""+uinfo.getLastname());
                    Log.d(TAG,""+uinfo.getEmail());
                    Log.d(TAG,""+uinfo.getMobilenumber());
                    ArrayList<String> array=new ArrayList<>();
                    array.add(uinfo.getFirstname());
                    array.add(uinfo.getLastname());
                    array.add(uinfo.getEmail());
                    array.add(uinfo.getMobilenumber());
                    array.add(uinfo.getCollegeid());
                    ArrayAdapter adapter=new ArrayAdapter(senior_profile_display.this,android.R.layout.simple_list_item_1,array);
                    mlistview.setAdapter(adapter);

                }
                //showdata(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /*private void showdata(DataSnapshot dataSnapshot) {

    }*/

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }
}
