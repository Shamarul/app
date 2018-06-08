package com.example.unitkesihatanehealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DoneActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        final EditText name = findViewById(R.id.name);
        final RadioButton come = findViewById(R.id.come);
        final RadioButton wait = findViewById(R.id.wait);
        final Button action = findViewById(R.id.btnsubmit);
        final Button back = findViewById(R.id.btnback);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("cases");

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String usernameTxt = name.getText().toString().trim();

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.child(usernameTxt).exists() || usernameTxt.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "User did not exist", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            DatabaseReference hopperRef = ref.child(usernameTxt);
                            Map<String, Object> hopperUpdates = new HashMap<>();
                            hopperUpdates.put("status", "Done");

                            hopperRef.updateChildren(hopperUpdates);

                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(DoneActivity.this,StaffActivity.class));
                            finish();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoneActivity.this, StaffActivity.class));
                finish();
            }

        });




    }
}
