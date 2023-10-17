package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button crashButton = new Button(this);
        crashButton.setText("Test Crash");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });
        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Button button;
        button=findViewById(R.id.showHideButton);
        EditText editText;
        editText=findViewById(R.id.editTextTextPersonName2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button.getText().equals("Show")){
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editText.setSelection(editText.getText().toString().length());
                    button.setText("Hide");
                }
                else{
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    button.setText("Show");
                }
                editText.setSelection(editText.getText().toString().length());
            }
        });
    }


public void onclick(View view){
        EditText editText,editText1;
        editText=findViewById(R.id.editTextTextPersonName);
        editText1=findViewById(R.id.editTextTextPersonName2);
        String mis= editText.getText().toString();
        String pass=editText1.getText().toString();
        if (mis.equals("112103033")|| mis.equals("user")) {
            if (pass.equals("CI999")|| pass.equals("user")) {
                Intent intent = new Intent(this, userHome.class);
                editText1.setText("");
                editText.setText("");
                startActivity(intent);
                return;
            }
            else {
                Toast.makeText(this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                editText1.setText("");

            }
        } else if (mis.equals("111222333")|| mis.equals("admin")) {
            if(pass.equals("CI888")||pass.equals("admin")){

                Intent intent = new Intent(this, adminHome.class);
                startActivity(intent);
                editText1.setText("");
                editText.setText("");
                return;
            }
            else{
                Toast.makeText(this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                editText1.setText("");
            }

        } else{

            Toast.makeText(this, "Enter valid MIS", Toast.LENGTH_SHORT).show();
            editText.setText("");
            editText1.setText("");
        }
}

}