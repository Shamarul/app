package com.example.unitkesihatanehealth;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Intent;
import java.util.List;
import java.util.ArrayList;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

public class PatientActivity extends AppCompatActivity {

//    private RecyclerView mBlogList;
//
//    private DatabaseReference mDatabase;
//
//    private DatabaseReference mDatabaseUsers;

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    CasesClass casesClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Button action = findViewById(R.id.btnAction);
        final Button done = findViewById(R.id.btnDone);
        casesClass = new CasesClass();
        listView = (ListView) findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("cases");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.patient_info, R.id.patientInfo, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    casesClass = ds.getValue(CasesClass.class);
                    if(casesClass.getStatus().toString().equalsIgnoreCase("Pending" )||casesClass.getStatus().toString().equalsIgnoreCase("InProgress" )){
                        list.add("Name :" + casesClass.getName().toString()+", Status :"+ casesClass.getStatus().toString() +", Symptoms :"+ casesClass.getSymptoms().toString() +", Condition :" + casesClass.getCondition().toString());
                    }
//                    list.add("Name :" + casesClass.getName().toString()+" Status :"+ casesClass.getStatus().toString() +" Symptoms :"+ casesClass.getSymptoms().toString() +" Condition :" + casesClass.getCondition().toString());
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
                startActivity(new Intent(PatientActivity.this,ActionActivity.class));
                finish();
            }

            });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this,DoneActivity.class));
                finish();
            }

        });




//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
//        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");
//
//        mDatabase.keepSynced(true);
//        mDatabaseUsers.keepSynced(true);
//
//        mBlogList = findViewById(R.id.patientlist);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
//
//        mBlogList.setHasFixedSize(true);
//        mBlogList.setLayoutManager(layoutManager);
//
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseRecyclerAdapter<Blog> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog>(
//                Blog.class,
//                mDatabase
//
//        ) {
//            @Override
//            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
//
//
//                viewHolder.setTitle(model.getTitle());
//                viewHolder.setDescription(model.getDescription());
//                viewHolder.setImage(getApplicationContext() , model.getImage());
//
//            }
//        };
//        mBlogList.setAdapter(firebaseRecyclerAdapter);
//
//    }

//    public static class BlogViewHolder extends RecyclerView.ViewHolder{
//
//        View mView;
//
//        public BlogViewHolder(View itemView) {
//            super(itemView);
//            mView = itemView;
//        }
//
//        public void setTitle(String title){
//
//            TextView post_title = mView.findViewById(R.id.post_title);
//            post_title.setText(title);
//
//        }
//
//        public void setDescription(String description){
//
//            TextView post_description = mView.findViewById(R.id.post_description);
//            post_description.setText(description);
//
//        }
//
//        public void setImage(Context ctx , String image) {
//
//            ImageView post_image = mView.findViewById(R.id.post_image);
//            Picasso.with(ctx).load(image).into(post_image);
//
//        }
//
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_patient);
//
//        // database instance
//        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();


    }
}

