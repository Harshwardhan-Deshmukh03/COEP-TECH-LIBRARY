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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class update extends AppCompatActivity {

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    public void onclickupdate(View view){
        EditText editmis,editpass,editconfirm;
        String mis,newpass,confirmpass;
        editmis=findViewById(R.id.editTextTextPersonName6);
        editpass=findViewById(R.id.editTextTextPersonName7);
        editconfirm=findViewById(R.id.editTextTextPersonName8);
        mis=editmis.getText().toString();
        newpass=editpass.getText().toString();
        confirmpass=editconfirm.getText().toString();
        if (newpass.equals(confirmpass)){
                    updatedata(mis,newpass);
        }
        else{
            Toast.makeText(update.this, "Passwords dont match", Toast.LENGTH_SHORT).show();
        }
    }
    public void updatedata(String mis,String newpass){
        EditText editmis,editpass,editconfirm;
        editmis=findViewById(R.id.editTextTextPersonName6);
        editpass=findViewById(R.id.editTextTextPersonName7);
        editconfirm=findViewById(R.id.editTextTextPersonName8);
        HashMap Usere=new HashMap();
        Usere.put("mis",mis);
        Usere.put("password",newpass);

        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(mis).updateChildren(Usere).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    editmis.setText("");
                    editpass.setText("");
                    editconfirm.setText("");
                    Toast.makeText(update.this, "Successfully updated", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(update.this, "Failed to update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}