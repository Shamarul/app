package com.example.unitkesihatanehealth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class UserhomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();

        String value = "Welcome to UK Apps "+extras.getString("username");
        final String username = extras.getString("username");
        //The key argument here must match that used in the other activity


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);

        TextView textView = (TextView) findViewById(R.id.textViewName2);
        textView.setText(value);

        Button butnotice = findViewById(R.id.notice);
        Button butreport = findViewById(R.id.reportsick);
        Button logout = findViewById(R.id.logout);

        butnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UserhomeActivity.this,NoticeActivity.class));


            }
        });

        butreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value=username;
                Intent intent = new Intent(UserhomeActivity.this , UserActivity.class);
                intent.putExtra("username",value);
                startActivity(intent);
                finish();

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UserhomeActivity.this,MainActivity.class));
                finish();


            }
        });

    }
}
