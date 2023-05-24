package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class delete extends AppCompatActivity {

    DatabaseReference reference;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
    }
    public void onclickdelete(View view){
        EditText getmis=findViewById(R.id.deletetext);
        String misno=getmis.getText().toString();
        if (!misno.isEmpty()){
                    deletedata(misno);
                }
                else {
            Toast.makeText(delete.this, "Enter the MIS", Toast.LENGTH_SHORT).show();
        }
    }
    public void deletedata(String mis){
        EditText getmis=findViewById(R.id.deletetext);
        reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.child(mis).removeValue().addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(delete.this, "Successful deleted", Toast.LENGTH_SHORT).show();
                    getmis.setText("");
                }
                else{
                    Toast.makeText(delete.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}