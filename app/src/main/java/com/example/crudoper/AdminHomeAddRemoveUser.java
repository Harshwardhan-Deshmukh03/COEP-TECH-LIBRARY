package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHomeAddRemoveUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_add_remove_user);
    }
    public void onclickadd(View view){

        DatabaseReference reference;
        EditText nametxt,mistxt;
        String name,oneName,mis,password,oneSerial,oneIssue,oneDue,twoName,twoSerial,twoIssue,twoDue,fine;
        nametxt=findViewById(R.id.editTextTextPersonName10);
        mistxt=findViewById(R.id.editTextTextPersonName5);
        password="4321";
        oneName="Not Issued";
        twoName="Not Issued";
        oneSerial="-";
        twoSerial="-";
        oneIssue="-";
        twoIssue="-";
        oneDue="-";
        twoDue="-";
        fine="0";
        name=nametxt.getText().toString();
        mis=mistxt.getText().toString();
        reference= FirebaseDatabase.getInstance().getReference("UserDB");
        reference.child(mis).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(AdminHomeAddRemoveUser.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (!name.isEmpty()&&!mis.isEmpty()){
                            FirebaseDatabase db= FirebaseDatabase.getInstance();
                            DatabaseReference reference1;
                            reference1=db.getReference("UserDB");
                            userHelper user=new userHelper(name,mis,password,oneName,oneSerial,oneIssue,oneDue,twoName,twoSerial,twoIssue,twoDue,fine);
                            reference1.child(mis).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    nametxt.setText("");
                                    mistxt.setText("");
                                    Toast.makeText(AdminHomeAddRemoveUser.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                }
            }
        });
    }

    public void onclickremove(View view){

        EditText editText;
        DatabaseReference reference;
        editText=findViewById(R.id.editTextTextPersonName11);
        String mis=editText.getText().toString();
        reference= FirebaseDatabase.getInstance().getReference("UserDB");
        reference.child(mis).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()) {

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("UserDB");
                        DataSnapshot dataSnapshot = task.getResult();
                        String due = String.valueOf(dataSnapshot.child("fine").getValue());
                        if (due.equals("0")){
                            reference.child(mis).removeValue().addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AdminHomeAddRemoveUser.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                                        editText.setText("");
                                    } else {
                                        Toast.makeText(AdminHomeAddRemoveUser.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    }
                        else{
                            Toast.makeText(AdminHomeAddRemoveUser.this, "Collect fine from User before closing account", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(AdminHomeAddRemoveUser.this, "User does not exist", Toast.LENGTH_SHORT).show();

                        
                    }
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