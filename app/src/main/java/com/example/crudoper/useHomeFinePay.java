package com.example.crudoper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class useHomeFinePay extends AppCompatActivity {
    Button btn;
    EditText amount,note,name,upirituralid;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_home_fine_pay);
        amount=findViewById(R.id.payfine);
        note=findViewById(R.id.paynote);
        name=findViewById(R.id.payName);
        upirituralid=findViewById(R.id.payid);
        btn=findViewById(R.id.paybutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText().toString().trim())){
                    Toast.makeText(useHomeFinePay.this, "Name is Invalid", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(upirituralid.getText().toString().trim())) {
                    Toast.makeText(useHomeFinePay.this, "UPI id is invalid", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(note.getText().toString().trim())) {
                    Toast.makeText(useHomeFinePay.this, "Note is invalid", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(amount.getText().toString().trim())) {
                    Toast.makeText(useHomeFinePay.this, "Amount is invalid", Toast.LENGTH_SHORT).show();
                }
                else{
                    payUsingUpi("Harshwardhan Deshmukh","harshwardhanrdd@okhdfcbank",note.getText().toString(),amount.getText().toString());
                }
            }
        });
    }
    void payUsingUpi(String name,String upiId,String note,String amount){
        String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
        int GOOGLE_PAY_REQUEST_CODE = 123;
        Log.e("main","name"+name+"--up--"+upiId+"--"+note+"--"+amount);
        Uri uri=Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa",upiId)
                .appendQueryParameter("pn",name)
                .appendQueryParameter("tn",note)
                .appendQueryParameter("am",amount)
                .appendQueryParameter("cu","INR")
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        Activity activity;
//        activity.startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
    }
}