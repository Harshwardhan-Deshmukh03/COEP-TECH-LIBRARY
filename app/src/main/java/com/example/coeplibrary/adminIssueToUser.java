package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class adminIssueToUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_issuetouser);
    }
    public void onclick(View view){
        EditText editText,editText1,editText2,editText3,editText4;
        editText=findViewById(R.id.editTextTextPersonName13);
        editText1=findViewById(R.id.editTextTextPersonName12);
        editText2=findViewById(R.id.editTextTextPersonName14);
        editText3=findViewById(R.id.editTextTextPersonName15);
        editText4=findViewById(R.id.editTextTextPersonName16);

    }
}