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

public class NoticeActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    CasesClass casesClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Button back = findViewById(R.id.btnBack);
        casesClass = new CasesClass();
        listView = (ListView) findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("cases");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.notice_info, R.id.noticeInfo, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    casesClass = ds.getValue(CasesClass.class);
                    if (casesClass.getStatus().toString().equalsIgnoreCase("InProgress")) {
                        list.add(casesClass.getName().toString() +" "+ casesClass.getInstruction().toString());
                    }
//                    list.add("Name :" + casesClass.getName().toString()+" Status :"+ casesClass.getStatus().toString() +" Symptoms :"+ casesClass.getSymptoms().toString() +" Condition :" + casesClass.getCondition().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(NoticeActivity.this, UserhomeActivity.class));
                finish();
            }

        });

    }
}