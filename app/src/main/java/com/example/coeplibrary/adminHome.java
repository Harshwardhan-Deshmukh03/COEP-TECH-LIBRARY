package com.example.coeplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class adminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

    }
    public void clickone(View view){
        Intent intent= new Intent(this,userSearch.class);
        startActivity(intent);
    }
    public void clicktwo(View view){
        Intent intent= new Intent(this,adminAddBook.class);
        startActivity(intent);
    }
    public void clickthree(View view){
        Intent intent= new Intent(this,adminRemoveBook.class);
        startActivity(intent);
    }
    public void clickfour(View view){
        Intent intent= new Intent(this,adminIssueToUser.class);
        startActivity(intent);
    }
    public void clickfive(View view){
        Intent intent= new Intent(this,adminReturnBook.class);
        startActivity(intent);
    }
    public void clicksix(View view){
        Intent intent= new Intent(this,adminReissue.class);
        startActivity(intent);
    }

    public void clickseven(View view){
        Intent intent=new Intent(this,admin_addremove.class);
        startActivity(intent);
    }
    public void clickeight(View view){
        Intent intent=new Intent(this,admin_checkfine.class);
        startActivity(intent);
    }
    public void clicknine(View view){
        Intent intent= new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}