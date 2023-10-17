package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class adminRemoveBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remove_book);
    }
    public void  removeBook(View view){
        EditText editText,editText1,editText2;
        editText=findViewById(R.id.editTextTextPersonName5);
        editText1=findViewById(R.id.editTextTextPersonName7);
        editText2=findViewById(R.id.editTextTextPersonName8);
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
        // we will check the isIssued in the column for the corresponding book
        // if the book isIssued then we will go to the books data and then put there available
        // we will go and put in the users thing that the book is returned



    }
}