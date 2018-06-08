package com.example.unitkesihatanehealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        final Button device = findViewById(R.id.button2);
        final Button reports = findViewById(R.id.button3);
        final Button logout = findViewById(R.id.logout);

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StaffActivity.this,PatientActivity.class));
                finish();

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StaffActivity.this,MainActivity.class));
                finish();

            }
        });

    }
}
