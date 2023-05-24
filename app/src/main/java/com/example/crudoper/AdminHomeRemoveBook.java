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

public class AdminHomeRemoveBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_remove_book);
    }

    public void removeBook(View view){

        EditText editText;
        DatabaseReference reference;
        editText=findViewById(R.id.editTextTextPersonName12);
        String serno=editText.getText().toString();
        reference= FirebaseDatabase.getInstance().getReference("BookDB");
        reference.child(serno).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("BookDB");
                        DataSnapshot dataSnapshot = task.getResult();
                        String booksname=String.valueOf(dataSnapshot.child("bookName").getValue());
                        String serno1 = String.valueOf(dataSnapshot.child("bookstatus").getValue());
                        if (serno1.equals("0")) {
                            DatabaseReference reference1=FirebaseDatabase.getInstance().getReference("AvaDB");
                            String blah=booksname+"_"+serno;
                            reference1.child(blah).removeValue();
                            reference.child(serno).removeValue().addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AdminHomeRemoveBook.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                                        editText.setText("");
                                    } else {
                                        Toast.makeText(AdminHomeRemoveBook.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            String str="Book is issued to "+serno1;
                            Toast.makeText(AdminHomeRemoveBook.this, str, Toast.LENGTH_SHORT).show();
                        }
                    }

                    else {
                            Toast.makeText(AdminHomeRemoveBook.this, "Book does not exist", Toast.LENGTH_SHORT).show();
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