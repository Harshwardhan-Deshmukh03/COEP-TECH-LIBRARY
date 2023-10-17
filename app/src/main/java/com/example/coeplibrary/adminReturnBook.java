package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class adminReturnBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_return_book);
    }
    public void onclick(View view){
        EditText editText,editText1,editText2,editText3,editText4;
        editText=findViewById(R.id.editTextTextPersonName11);
        editText1=findViewById(R.id.editTextTextPersonName18);
        editText2=findViewById(R.id.editTextTextPersonName20);
        editText3=findViewById(R.id.editTextTextPersonName19);
        editText4=findViewById(R.id.editTextTextPersonName17);

    }
}