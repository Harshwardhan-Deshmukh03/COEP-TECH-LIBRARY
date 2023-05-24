package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class read extends AppCompatActivity {
    EditText misText;
    DatabaseReference reference;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        misText=findViewById(R.id.editTextTextPersonName9);
        btn=findViewById(R.id.button4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mis=misText.getText().toString();
                if(!mis.isEmpty()){
                    readData(mis);
                }
                else{
                    Toast.makeText(read.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public int getfine(String date1,String date2,int ppd){
        DateTimeFormatter dateTimeFormatter= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        }
        LocalDate localDate= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localDate = LocalDate.parse(date1,dateTimeFormatter);
        }
        LocalDate localDate1= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localDate1 = LocalDate.parse(date2,dateTimeFormatter);
        }
        int diff= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            diff = (int) ChronoUnit.DAYS.between(localDate1,localDate);
        }
        if (diff<8){
            return 0;
        }
        return (diff-7)*ppd;
    }

    public static String addSevenDays(String date) {
        DateTimeFormatter formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        }
        LocalDate localDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            localDate = LocalDate.parse(date, formatter);
        }
        LocalDate newDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            newDate = localDate.plusDays(7);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return newDate.format(formatter);
        }
        return "";
    }
    private void readData(String username){
        reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(read.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot= task.getResult();
                        String name=String.valueOf(dataSnapshot.child("name").getValue());
                        String username=String.valueOf(dataSnapshot.child("username").getValue());
                        TextView textView,textView1;
                        textView=findViewById(R.id.showUserID);
                        textView.setText(username);
                        textView1=findViewById(R.id.showname);
                        textView1.setText(name);

                    }else {
                        Toast.makeText(read.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(read.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}