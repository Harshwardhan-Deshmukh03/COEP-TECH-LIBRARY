package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.util.HashMap;

public class userHomeSearchChange extends AppCompatActivity {
    TextView textView;
    public String prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_search_change);
        Intent second=getIntent();
        String pass=second.getStringExtra(MainActivity.EXTRA_NAME);
        setText();
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

    public void setText(){
        textView=findViewById(R.id.textView15);
        Intent second=getIntent();
        String pass=second.getStringExtra(MainActivity.EXTRA_NAME);

        textView.setText("MIS No.: "+pass);
    }
    public void onChange(View view){


        DatabaseReference reference;
        EditText currtxt,newtxt,retxt;


        currtxt=findViewById(R.id.editTextNumberPassword);
        newtxt=findViewById(R.id.editTextNumberPassword2);
        retxt=findViewById(R.id.editTextNumberPassword3);
        Intent intent=getIntent();
        String mis=intent.getStringExtra(UserHome.EXTRA_NAMEE);
        String currpass=currtxt.getText().toString();
        String newpass=newtxt.getText().toString();
        String repass=retxt.getText().toString();
        if (!newpass.equals(repass)){
            Toast.makeText(this, "Enter new Password again", Toast.LENGTH_SHORT).show();
            retxt.setText("");
            return;
        }

        reference= FirebaseDatabase.getInstance().getReference("UserDB");
        reference.child(mis).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot= task.getResult();
//                        String name=String.valueOf(dataSnapshot.child("name").getValue());
                        String password=String.valueOf(dataSnapshot.child("password").getValue());
                        if (password.equals(currpass)){
                            //      passwords match till here
                            HashMap Usere=new HashMap();
                            Usere.put("mis",mis);
                            Usere.put("password",newpass);

                            reference.child(mis).updateChildren(Usere).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()){
//                                        mistxt.setText("");
                                        currtxt.setText("");
                                        newtxt.setText("");
                                        retxt.setText("");
                                        Toast.makeText(userHomeSearchChange.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(userHomeSearchChange.this,MainActivity.class);
                                        Toast.makeText(userHomeSearchChange.this, "Log in with new Password", Toast.LENGTH_SHORT).show();
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);


                                    }
                                    else{
                                        Toast.makeText(userHomeSearchChange.this, "Failed to update", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else {
                            Toast.makeText(userHomeSearchChange.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(userHomeSearchChange.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(userHomeSearchChange.this, "Failed to read", Toast.LENGTH_SHORT).show();
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