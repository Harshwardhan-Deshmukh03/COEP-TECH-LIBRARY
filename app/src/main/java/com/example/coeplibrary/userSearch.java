package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class userSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
    }
    public void onSearch(View view){
        EditText editText,editText1;
        editText=findViewById(R.id.editTextTextPersonName3);
        editText1=findViewById(R.id.editTextTextPersonName4);
        String book= editText.getText().toString();
        String publication=editText1.getText().toString();
        if (book.equals("")) {
            Toast.makeText(this, "Enter book name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(publication.equals("")){
            Toast.makeText(this, "Enter publication name", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}