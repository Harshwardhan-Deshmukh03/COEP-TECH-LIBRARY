package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class userHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
    }

    public void clickone(View view){
        Intent intent= new Intent(this,userMyBooks.class);
        startActivity(intent);
    }


    public void clicktwo(View view){
        Intent intent= new Intent(this, userChangePassword.class);
        startActivity(intent);
    }


    public void clickthree(View view){
        Intent intent= new Intent(this,userSearch.class);
        startActivity(intent);
    }



    public void clickfour(View view){
        Intent intent= new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
