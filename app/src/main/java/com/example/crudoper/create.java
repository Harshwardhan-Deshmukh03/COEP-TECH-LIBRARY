package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create extends AppCompatActivity {
    String username,name,mis,password;
    FirebaseDatabase db;
    DatabaseReference reference;
    EditText nametxt,usernametxt,passtxt,mistxt;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        button=findViewById(R.id.createbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametxt=findViewById(R.id.editTextTextPersonName);
                usernametxt=findViewById(R.id.editTextTextPersonName2);
                passtxt=findViewById(R.id.editTextTextPersonName3);
                mistxt=findViewById(R.id.editTextTextPersonName4);
                name=nametxt.getText().toString();
                username=usernametxt.getText().toString();
                mis=mistxt.getText().toString();
                password=passtxt.getText().toString();
                if (!name.isEmpty()&&!username.isEmpty()&&!mis.isEmpty()&& !password.isEmpty()){
                    users user=new users(username,name,mis,password);
                    db= FirebaseDatabase.getInstance();
                    reference=db.getReference("Users");
                    reference.child(mis).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            nametxt.setText("");
                            usernametxt.setText("");
                            mistxt.setText("");
                            passtxt.setText("");
                            Toast.makeText(create.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}