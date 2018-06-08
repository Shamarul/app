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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // database instance
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        // interface components
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final EditText passwordConfirm = findViewById(R.id.passwordConfirm);
        final EditText fullname = findViewById(R.id.fullname);
        final EditText id = findViewById(R.id.idnumber);
        final RadioButton staff = findViewById(R.id.staff);
        final RadioButton student = findViewById(R.id.student);
        final RadioButton uitm = findViewById(R.id.uitm);
        final RadioButton nr = findViewById(R.id.nonresident);
        final EditText address = findViewById(R.id.address);
        final Button register = findViewById(R.id.button1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == register) {
                    registerUser();
                }

            }

            public void registerUser(){
                // get value from interface component
                final String usernameTxt = username.getText().toString().trim();
                final String passwordTxt = password.getText().toString().trim();
                final String passwordConfirmTxt = passwordConfirm.getText().toString().trim();
                final String fullnameTxt = fullname.getText().toString().trim();
                final String idTxt = id.getText().toString().trim();
                final String addressTxt = address.getText().toString().trim();

                // check for correct confirm password
                if (passwordTxt.equals(passwordConfirmTxt)) {

                    // check for existing user
                    myRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(usernameTxt).exists() || dataSnapshot.child(idTxt).exists()) {
                                Toast.makeText(getApplicationContext(), "User already exist", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                // check for privilege
                                String privilege = "";

                                if (staff.isChecked()) {
                                    privilege = "staff";
                                } else if (student.isChecked()) {
                                    privilege = "user";
                                }
                                String location = "";
                                if (uitm.isChecked()) {
                                        location = "uitm";
                                } else if(nr.isChecked()) {
                                        location = "non-resident";
                                    }

                                // add data into database
                                myRef.child("users/" + usernameTxt).setValue(new UserClass( addressTxt, fullnameTxt, idTxt, location, passwordTxt, privilege));
                                Toast.makeText(getApplicationContext(), "User successfully registered", Toast.LENGTH_SHORT).show();

                                Intent backMainTest = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(backMainTest);
 //                               startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                finish();

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                }


            }
        });

        }
    }
