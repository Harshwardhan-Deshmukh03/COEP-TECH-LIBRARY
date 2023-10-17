package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class adminAddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_book);
    }
    public void addBook(View view){
        EditText editText,editText1,editText2;
        editText=findViewById(R.id.editTextTextPersonName9);
        editText1=findViewById(R.id.editTextTextPersonName6);
        editText2=findViewById(R.id.editTextTextPersonName10);
        String book=editText.getText().toString();
        String publication=editText1.getText().toString();
        String serial=editText2.getText().toString();
        if (book.equals("")){
            Toast.makeText(this, "Enter book name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(publication.equals("")){
            Toast.makeText(this, "Enter publication name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(serial.equals("")){
            Toast.makeText(this, "Enter serial number", Toast.LENGTH_SHORT).show();
            return;
        }
        // we will check the number of books issued if it is already 2 then we will raise the toast

        // we will check the books and see if the isIssued is there and if the book is not issued then we will put isIssued to 1
        // we will go to the user and check if the number of books columns and add suitably

    }
}