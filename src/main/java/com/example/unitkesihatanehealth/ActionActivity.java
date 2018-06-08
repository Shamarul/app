package com.example.unitkesihatanehealth;

import android.content.Intent;
import android.drm.DrmStore;
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

import java.sql.Ref;
import java.util.HashMap;
import java.util.Map;

public class ActionActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

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

                            String instruct = "";

                            if (come.isChecked()) {
                                instruct = "Please Come to UK";
                            } else if (wait.isChecked()) {
                                instruct = "Wait, We Are Coming";
                            }

                            DatabaseReference hopperRef = ref.child(usernameTxt);
                            Map<String, Object> hopperUpdates = new HashMap<>();
                            hopperUpdates.put("status", "InProgress");
                            hopperUpdates.put("instruction", instruct);

                            hopperRef.updateChildren(hopperUpdates);

                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(ActionActivity.this,StaffActivity.class));
                            if (come.isChecked()) {
                                finish();
                            } else {
                                Intent intent = new Intent(ActionActivity.this , DetailActivity.class);

                                startActivity(intent);
                            }
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
                startActivity(new Intent(ActionActivity.this, StaffActivity.class));
                finish();
            }

        });




    }
}
