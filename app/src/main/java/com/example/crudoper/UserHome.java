package com.example.crudoper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UserHome extends AppCompatActivity {
    public static final String EXTRA_NAMEE="com.example.crudoper.extra.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to Log out?")
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

    public void onClickSearch(View view){
//        Toast.makeText(this, "This feature is coming soon !", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,userHomeSearch.class);
        Intent second=getIntent();
        String pass=second.getStringExtra(MainActivity.EXTRA_NAME);
        intent.putExtra(MainActivity.EXTRA_NAME,pass);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    public void onChangepass(View view){
        Intent second=getIntent();
        String pass=second.getStringExtra(MainActivity.EXTRA_NAME);
        Intent intent=new Intent(this,userHomeSearchChange.class);
        intent.putExtra(MainActivity.EXTRA_NAME,pass);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    public void onLogOut(View view){
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    public void onMyBooks(View view){
        Intent intent=new Intent(this,userHomeMyBooks.class);
        Intent second=getIntent();
        String pass=second.getStringExtra(MainActivity.EXTRA_NAME);
        intent.putExtra(MainActivity.EXTRA_NAME,pass.toString());
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }
    public void onCheckfine(View view){
        Intent intent=new Intent(this,userHomeCheckFine.class);
        Intent second=getIntent();
        String pass=second.getStringExtra(MainActivity.EXTRA_NAME);
        intent.putExtra(MainActivity.EXTRA_NAME,pass.toString());
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }

}