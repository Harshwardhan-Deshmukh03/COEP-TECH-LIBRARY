package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class admin_addremove extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_addremove);
    }
    public void onAdd(View view){
        EditText editText;
        editText=findViewById(R.id.editTextTextPersonName28);
        String stree=editText.getText().toString();
        if (stree.equals("")){
            Toast.makeText(this, "Enter MIS no.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stree.length()!=10){
            Toast.makeText(this, "Enter valid MIS no.", Toast.LENGTH_SHORT).show();
            return;
        }
        //now we will send the query
        // we will need to send the mis to the password table and enter the data
        // we will need to add the mis no. to the second table and we will add the rest of the columns as blank



    }
    public void onRemove(View view){
        EditText editText;
        int numberOfBooks=0;

        editText=findViewById(R.id.editTextTextPersonName27);
        String stree=editText.getText().toString();
        // we will check the number of books that person has issued

        if (numberOfBooks==0){
            // we will perform the task of removing the data of the user
            Toast.makeText(this, "Account removed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "User has books issued, collect them to proceed.", Toast.LENGTH_SHORT).show();
        }
    }
}