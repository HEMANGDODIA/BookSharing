package com.BookSharing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class HomePage extends AppCompatActivity {
    Button button_dBooks;
    Button button_dStationary;
    Button button_dNotes;
    Button button_dReferences;

    private AppBarConfiguration mAppBarConfiguration;
    ProgressDialog pDialog;
    RecyclerView rv;
    ArrayList<String> Bookname,AutherName,Datails,MobileNO;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase fd;
    DatabaseReference ref;
    TextView profilename,profileemailadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        Bookname=new ArrayList<>();
        AutherName=new ArrayList<>();
        Datails=new ArrayList<>();
        MobileNO=new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        fd = FirebaseDatabase.getInstance();
        ref = fd.getReference().child("Books").child(FirebaseAuth.getInstance().getUid());
        Bookname.clear();
        AutherName.clear();
        Datails.clear();
        MobileNO.clear();
        /*ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    HashMap<String, String> ob = (HashMap) child.getValue();
                    String Books = ob.get("Books");
                    String Material = ob.get("Material");
                    String Notes = ob.get("Notes");
                    for (DataSnapshot child2 : child.getChildren()) {
                        for (DataSnapshot child3 : child2.getChildren()) {
                            Map<String, String> ob2 = (Map) child3.getValue();
                            jobid.add(ob2.get("JobId"));
                            compid.add(ob2.get("Companyid"));
                            company.add(comp);
                            sector.add(sect);
                            description.add(desc);
                            jobtitle.add(ob2.get("JobTitle"));
                            location.add(ob2.get("Location"));
                            experience.add(ob2.get("Experience"));
                            salary.add(ob2.get("Salary"));



                        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);
        Toolbar toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_books, R.id.nav_stationary_material,
                R.id.nav_short_note, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        init();
        button_dBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(HomePage.this,donee_books.class);
                startActivity(in);
            }
        });
        button_dStationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1=new Intent(HomePage.this,donee_stationary.class);
                startActivity(in1);


            }
        });
        button_dNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2=new Intent(HomePage.this,donee_notes.class);
                startActivity(in2);


            }
        });
        button_dReferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in3=new Intent(HomePage.this,donee_references.class);
                startActivity(in3);
            }
        });

    }
    void init(){
        button_dBooks=(Button)findViewById(R.id.dbutton);
        button_dStationary=(Button)findViewById(R.id.dbutton1);
        button_dNotes=(Button)findViewById(R.id.dbutton2);
        button_dReferences=(Button)findViewById(R.id.dbutton3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater=getMenuInflater();

        inflater.inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.senior_profile:
                seniorprofile();
                break;
            case R.id.Logout:
                logout();
                break;
        }
        return true;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this,MainActivity.class));


    }


    private void seniorprofile() {
        Intent mi = new Intent(HomePage.this,senior_profile_display.class);
        startActivity(mi);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
