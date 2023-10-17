package com.example.crudoper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.InputStream;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to Log Out?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    public void onClickAddRemove(View view){
        Intent intent =new Intent(this,AdminHomeAddRemoveUser.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }
    public void onclickLogout(View view){
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }
    public void onClickAddBook(View view){
        Intent intent=new Intent(this,AdminHomeAddBook.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }
    public void onClickRemoveBook(View view){
        Intent intent=new Intent(this,AdminHomeRemoveBook.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }
    public void onClickIssue(View view){
        Intent intent=new Intent(this,AdminHomeIssue.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }
    public void onClickReturn(View view){
        Intent intent=new Intent(this,AdminHomeReturnBook.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


    }
    public void onClickReissue(View view){
        Intent intent=new Intent(this,AdminHomeReissue.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }
    public void onClickSearch(View view){
        Intent intent= new Intent(this,adminHomeSearch.class);
        startActivity(intent);

    }

}