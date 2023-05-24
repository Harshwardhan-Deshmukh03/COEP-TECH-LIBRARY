package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userHomeMyBooks extends AppCompatActivity {
    TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_my_books);
        Intent second=getIntent();
        String pass=second.getStringExtra(MainActivity.EXTRA_NAME);
//        Toast.makeText(this,pass , Toast.LENGTH_SHORT).show();
        textView=findViewById(R.id.textView12);
        textView.setText("MIS no. "+pass);
        setText(pass);




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,UserHome.class);
        i.putExtras(getIntent());
        startActivity(i);
        finish();
    }

    public void setText(String misno){
        TextView firstbook,secondbook;
        firstbook=findViewById(R.id.textView13);
        secondbook=findViewById(R.id.textView14);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("UserDB");
        reference.child(misno).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot= task.getResult();
                        String firstname=String.valueOf(dataSnapshot.child("oneName").getValue());
                        String firstno=String.valueOf(dataSnapshot.child("oneSerial").getValue());
                        String firstdue=String.valueOf(dataSnapshot.child("oneDue").getValue());
                        String firsttaken=String.valueOf(dataSnapshot.child("oneIssue").getValue());
                        String secondname=String.valueOf(dataSnapshot.child("twoName").getValue());
                        String secondno=String.valueOf(dataSnapshot.child("twoSerial").getValue());
                        String seconddue=String.valueOf(dataSnapshot.child("towDue").getValue());
                        String secondtaken=String.valueOf(dataSnapshot.child("twoIssue").getValue());
                        firstbook.setText("First book:\nBook Name: "+firstname+"\nBook Number: "+firstno+"\nBook Issue Date: "+firsttaken+"\nSubmission Date: "+firstdue);
                        secondbook.setText("Second book:\nBook Name: "+secondname+"\nBook Number: "+secondno+"\nBook Issue Date: "+secondtaken+"\nSubmission Date: "+seconddue);


                    }else {

                    }
                }
                else{
                    Toast.makeText(userHomeMyBooks.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}