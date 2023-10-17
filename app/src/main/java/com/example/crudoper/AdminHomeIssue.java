package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
import java.util.HashMap;

public class AdminHomeIssue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_issue);
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

    public void onClickIssue(View view){
        EditText mistxt,sernotxt,datetxt;
        mistxt=findViewById(R.id.issuemis);
        sernotxt=findViewById(R.id.issueserial);
        datetxt=findViewById(R.id.issuedate);
        String mis=mistxt.getText().toString();
        String serno=sernotxt.getText().toString();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("UserDB");
        reference.child(mis).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){

                        DataSnapshot dataSnapshot= task.getResult();
                        String book1=String.valueOf(dataSnapshot.child("oneName").getValue());
                        String book2=String.valueOf(dataSnapshot.child("twoName").getValue());
                        if (book1.equals("Not Issued")){
                            DatabaseReference reference1= FirebaseDatabase.getInstance().getReference("BookDB");
                            reference1.child(serno).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (task.isSuccessful()){
                                        if (task.getResult().exists()){

                                            DataSnapshot dataSnapshot1= task.getResult();
                                            String name=String.valueOf(dataSnapshot1.child("bookstatus").getValue());
                                            String bookname=String.valueOf(dataSnapshot1.child("bookName").getValue());
                                            String number=String.valueOf(dataSnapshot1.child("bookNumber").getValue());
                                            String issue=datetxt.getText().toString();
                                            String duedate=addSevenDays(issue);
                                            if (name.equals("0")){
                                                HashMap Usere=new HashMap();
                                                Usere.put("bookNumber",serno);
                                                Usere.put("bookstatus",mis);

                                                DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference("BookDB");
                                                databaseReference2.child(serno).updateChildren(Usere);
                                                HashMap userupdate=new HashMap();
                                                userupdate.put("oneName",bookname);
                                                userupdate.put("oneSerial",number);
                                                userupdate.put("oneIssue",issue);
                                                userupdate.put("oneDue",duedate);
                                                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("UserDB");
                                                databaseReference.child(mis).updateChildren(userupdate);
                                                DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("AvaDB");
                                                String blah=bookname+"_"+number;
                                                databaseReference1.child(blah).removeValue();
                                                Toast.makeText(AdminHomeIssue.this, "Book Issued", Toast.LENGTH_SHORT).show();
                                                mistxt.setText("");
                                                sernotxt.setText("");
                                                datetxt.setText("");
                                            }
                                            else {
                                                Toast.makeText(AdminHomeIssue.this, "Book is Already Issued", Toast.LENGTH_SHORT).show();
                                            }


                                        }else {
                                            Toast.makeText(AdminHomeIssue.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(AdminHomeIssue.this, "Failed to read", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else if (book2.equals("Not Issued")) {
                            DatabaseReference reference1= FirebaseDatabase.getInstance().getReference("BookDB");
                            reference1.child(serno).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (task.isSuccessful()){
                                        if (task.getResult().exists()){
//                                            Toast.makeText(AdminHomeIssue.this, "Successfully read", Toast.LENGTH_SHORT).show();
                                            DataSnapshot dataSnapshot1= task.getResult();
                                            String name=String.valueOf(dataSnapshot1.child("bookstatus").getValue());
                                            String bookname=String.valueOf(dataSnapshot1.child("bookName").getValue());
                                            String number=String.valueOf(dataSnapshot1.child("bookNumber").getValue());
                                            String issue=datetxt.getText().toString();
                                            String duedate=addSevenDays(issue);
                                            if (name.equals("0")){
                                                HashMap Usere=new HashMap();
                                                Usere.put("bookNumber",serno);
                                                Usere.put("bookstatus",mis);

                                                DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference("BookDB");
                                                databaseReference2.child(serno).updateChildren(Usere);
                                                HashMap userupdate=new HashMap();
                                                userupdate.put("twoName",bookname);
                                                userupdate.put("twoSerial",number);
                                                userupdate.put("twoIssue",issue);
                                                userupdate.put("towDue",duedate);
                                                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("UserDB");
                                                databaseReference.child(mis).updateChildren(userupdate);
                                                DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("AvaDB");
                                                String blah=bookname+"_"+number;
                                                databaseReference1.child(blah).removeValue();
                                                Toast.makeText(AdminHomeIssue.this, "Book Issued", Toast.LENGTH_SHORT).show();
                                                mistxt.setText("");
                                                sernotxt.setText("");
                                                datetxt.setText("");

                                            }
                                            else {
                                                Toast.makeText(AdminHomeIssue.this, "Book is Already Issued", Toast.LENGTH_SHORT).show();
                                            }


                                        }else {
                                            Toast.makeText(AdminHomeIssue.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(AdminHomeIssue.this, "Failed to read", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



                        }
                        else{
                            Toast.makeText(AdminHomeIssue.this, "User has Maximum Amount of Books Issued", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(AdminHomeIssue.this, "User not exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}