package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class AdminHomeReturnBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_return_book);
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
        if (diff>7) {
            return (diff-7) * ppd;
        }
        return 0;
    }

    public void onReturn(View view){

        EditText mistxt,serialtxt,datetxt;
        mistxt=findViewById(R.id.returnmis);
        serialtxt=findViewById(R.id.returnserial);
        datetxt=findViewById(R.id.returndate);
        String mis=mistxt.getText().toString();
        String serialnumber=serialtxt.getText().toString();
        String returndate=datetxt.getText().toString();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("UserDB");
        reference.child(mis).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        Toast.makeText(AdminHomeReturnBook.this, "User already exists", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot= task.getResult();
                        String book1=String.valueOf(dataSnapshot.child("oneSerial").getValue());
                        String book2=String.valueOf(dataSnapshot.child("twoSerial").getValue());
                        if (book1.equals(serialnumber)){
                            DatabaseReference reference1= FirebaseDatabase.getInstance().getReference("UserDB");
                            reference1.child(mis).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (task.isSuccessful()){
                                        if (task.getResult().exists()){
                                            Toast.makeText(AdminHomeReturnBook.this, "Successfully read", Toast.LENGTH_SHORT).show();
                                            DataSnapshot dataSnapshot1= task.getResult();
                                            String nameofbook=String.valueOf(dataSnapshot1.child("oneName").getValue());
                                            String issuedate=String.valueOf(dataSnapshot1.child("oneIssue").getValue());
                                            String currFine=String.valueOf(dataSnapshot1.child("fine").getValue());
                                            String newFine=Integer.toString(getfine(returndate,issuedate,2)+Integer.parseInt(currFine));
                                            HashMap Usere=new HashMap();
                                            Usere.put("mis",mis);
                                            Usere.put("oneIssue","-");
                                            Usere.put("oneName","Not Issued");
                                            Usere.put("oneSerial","-");
                                            Usere.put("oneDue","-");
                                            Usere.put("fine",newFine);
                                            DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference("UserDB");
                                            databaseReference2.child(mis).updateChildren(Usere);
                                            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("BookDB");
                                            HashMap Useree=new HashMap();
                                            Useree.put("bookNumber",serialnumber);
                                            String status="0";
                                            Useree.put("bookstatus",status);
                                            databaseReference.child(serialnumber).updateChildren(Useree);
                                            databaseReference=FirebaseDatabase.getInstance().getReference("AvaDB");
                                            String blah=nameofbook+"_"+serialnumber;
                                            availableHelper bookava=new availableHelper(nameofbook,serialnumber,blah);
                                            databaseReference.child(blah).setValue(bookava);
                                            mistxt.setText("");
                                            serialtxt.setText("");
                                            datetxt.setText("");
                                            Toast.makeText(AdminHomeReturnBook.this, "Book Returned", Toast.LENGTH_SHORT).show();


                                        }
                                        else {
                                            Toast.makeText(AdminHomeReturnBook.this, "Book is Already Issued", Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(AdminHomeReturnBook.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else if (book2.equals(serialnumber)) {
                            DatabaseReference reference1= FirebaseDatabase.getInstance().getReference("UserDB");
                            reference1.child(mis).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (task.isSuccessful()){
                                        if (task.getResult().exists()){
                                            Toast.makeText(AdminHomeReturnBook.this, "Successfully read", Toast.LENGTH_SHORT).show();
                                            DataSnapshot dataSnapshot1= task.getResult();
                                            String bookee=String.valueOf(dataSnapshot1.child("twoName").getValue());
                                            String issuedate=String.valueOf(dataSnapshot1.child("twoIssue").getValue());
                                            String currFine=String.valueOf(dataSnapshot1.child("fine").getValue());
                                            String newFine=Integer.toString(getfine(returndate,issuedate,2)+Integer.parseInt(currFine));
                                            HashMap Usere=new HashMap();
                                            Usere.put("mis",mis);
                                            Usere.put("twoIssue","-");
                                            Usere.put("twoName","Not Issued");
                                            Usere.put("twoSerial","-");
                                            Usere.put("towDue","-");
                                            Usere.put("fine",newFine);
                                            DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference("UserDB");
                                            databaseReference2.child(mis).updateChildren(Usere);
                                            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("BookDB");
                                            HashMap Useree=new HashMap();
                                            Useree.put("bookNumber",serialnumber);
                                            Useree.put("bookstatus","0");
                                            databaseReference.child(serialnumber).updateChildren(Useree);
                                            String blah=bookee+"_"+serialnumber;
                                            databaseReference2=FirebaseDatabase.getInstance().getReference("AvaDB");
                                            availableHelper avaBook=new availableHelper(bookee,serialnumber,blah);
                                            databaseReference2.child(blah).setValue(avaBook);
                                            mistxt.setText("");
                                            serialtxt.setText("");
                                            datetxt.setText("");
                                            Toast.makeText(AdminHomeReturnBook.this, "Book Returned", Toast.LENGTH_SHORT).show();




                                        }
                                        else {
                                            Toast.makeText(AdminHomeReturnBook.this, "Book is Already Issued", Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(AdminHomeReturnBook.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(AdminHomeReturnBook.this, "Book is not issued to User", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(AdminHomeReturnBook.this, "User not exists", Toast.LENGTH_SHORT).show();
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