package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.InternalTokenProvider;

public class userHomeFine extends AppCompatActivity {
    TextView textView;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_fine);
        showdata();
//        Intent intent=getIntent();
//        String prev=intent.getStringExtra(UserHome.EXTRA_NAMEE);
//        Toast.makeText(this, prev, Toast.LENGTH_SHORT).show();
//        textView=findViewById(R.id.showingmis);
//        textView.setText(prev);
//        reference= FirebaseDatabase.getInstance().getReference("UserDB");
//        reference.child(prev).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()){
//                    if (task.getResult().exists()){
//                        DataSnapshot dataSnapshot= task.getResult();
//                        String fine=String.valueOf(dataSnapshot.child("fine").getValue());
//                        TextView textView;
//                        textView=findViewById(R.id.showingfine);
//                        textView.setText(fine);
//
//                    }else {
//                        Toast.makeText(userHomeFine.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else{
//                    Toast.makeText(userHomeFine.this, "Failed to read", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
    }
    public void showdata(){
        TextView textView1,textView2;
        textView1=findViewById(R.id.showingmis);
        textView2=findViewById(R.id.showingfine);
        Intent intent=getIntent();
        String Mis;
        Mis=intent.getStringExtra(MainActivity.EXTRA_NAME);
        textView1.setText(Mis);
        Toast.makeText(this, Mis, Toast.LENGTH_SHORT).show();
        return;
    }



}