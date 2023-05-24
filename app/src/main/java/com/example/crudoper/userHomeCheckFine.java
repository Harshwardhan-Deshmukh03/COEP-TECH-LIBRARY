package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userHomeCheckFine extends AppCompatActivity {
    TextView textView,textview2;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_check_fine);
        settext();

    }
    public void onPaying(View view){
        Intent intent=new Intent(this,userHomeFine.class);
        startActivity(intent);
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
    public void onPay(View view){
        Toast.makeText(this, "This feature is coming soon !", Toast.LENGTH_SHORT).show();
    }

    public void settext(){
        textView=findViewById(R.id.showingmis2);
        Intent second=getIntent();
        String pass=second.getStringExtra(MainActivity.EXTRA_NAME);
        textView.setText("MIS No. "+pass);

        reference= FirebaseDatabase.getInstance().getReference("UserDB");
        reference.child(pass).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot= task.getResult();
                        String name=String.valueOf(dataSnapshot.child("fine").getValue());
                        textview2=findViewById(R.id.showingfine2);
                        textview2.setText("Fine: "+name);

                    }else {
                        Toast.makeText(userHomeCheckFine.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(userHomeCheckFine.this, "Failed to read", Toast.LENGTH_SHORT).show();
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