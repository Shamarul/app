package com.example.unitkesihatanehealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
//    CasesClass casesClass;
    UserClass userClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Button action = findViewById(R.id.btnAction);
        final Button done = findViewById(R.id.btnDone);
        userClass = new UserClass();
        listView = (ListView) findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.patient_info, R.id.patientInfo, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    userClass = ds.getValue(UserClass.class);
                        list.add("Student ID: "+ userClass.getId().toString() +", Address :" + userClass.getAddress().toString() + ", Location :" + userClass.getLocation().toString() );


                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, ActionActivity.class));
                finish();
            }

        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, DoneActivity.class));
                finish();
            }

        });
    }

}