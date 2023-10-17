package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class adminReissue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reissue);
    }
    public void onclick(View view){
        EditText editText,editText1,editText2,editText3,editText4;
        editText=findViewById(R.id.editTextTextPersonName21);
        editText1=findViewById(R.id.editTextTextPersonName22);
        editText2=findViewById(R.id.editTextTextPersonName24);
        editText3=findViewById(R.id.editTextTextPersonName23);
        editText4=findViewById(R.id.editTextTextPersonName25);

    }
}