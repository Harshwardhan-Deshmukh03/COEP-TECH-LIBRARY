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

public class AdminHomeAddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_add_book);
    }
    public void onClickAddBook(View view){

        DatabaseReference reference;
        EditText booknametxt,bookpubtxt,bookserialtxt;
        String bookname,bookpub,bookserno,bookstatus,avaTag;
        booknametxt=findViewById(R.id.addbookname);
        bookpubtxt=findViewById(R.id.addbookpublication);
        bookserialtxt=findViewById(R.id.addbookserial);
        bookstatus="0";
        bookname=booknametxt.getText().toString();
        bookpub=bookpubtxt.getText().toString();
        bookserno=bookserialtxt.getText().toString();
        avaTag=bookname+"_"+bookserno;





        reference= FirebaseDatabase.getInstance().getReference("BookDB");
        reference.child(bookserno).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(AdminHomeAddBook.this, "Book already exists", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (!bookname.isEmpty()&&!bookpub.isEmpty()&&!bookserno.isEmpty()){
                            FirebaseDatabase db= FirebaseDatabase.getInstance();
                            DatabaseReference reference1,reference2;
                            reference1=db.getReference("BookDB");
                            bookHelper book=new bookHelper(bookname,bookserno,bookpub,bookstatus);
                            reference2=db.getReference("AvaDB");
                            availableHelper bookava=new availableHelper(bookname,bookserno,avaTag);
                            reference2.child(avaTag).setValue(bookava);
                            reference1.child(bookserno).setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    booknametxt.setText("");
                                    bookpubtxt.setText("");
                                    bookserialtxt.setText("");
                                    Toast.makeText(AdminHomeAddBook.this, "Successfully Added to Library", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
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