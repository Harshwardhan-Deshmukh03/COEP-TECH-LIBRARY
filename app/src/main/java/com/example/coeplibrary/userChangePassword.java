package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class userChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_changepassword);
    }
    public void onClickChangePass(View view){
        EditText editText,editText1,editText2,editText3;
        editText3=findViewById(R.id.editTextTextPersonName29);
        editText=findViewById(R.id.editTextTextPersonName26);
        editText1=findViewById((R.id.editTextNumberPassword));
        editText2=findViewById(R.id.editTextNumberPassword2);
        String pre=editText.getText().toString();
        String newonce=editText1.getText().toString();
        String newconfirm=editText2.getText().toString();
        // first check if the mis is present if not then raise a toast
        if (!newonce.equals(newconfirm)){
            editText2.setText("");
            Toast.makeText(this, "Enter confirm password again", Toast.LENGTH_LONG).show();
        }
        // then we will proceed to change password and enter the password in the table 1.
    }
}