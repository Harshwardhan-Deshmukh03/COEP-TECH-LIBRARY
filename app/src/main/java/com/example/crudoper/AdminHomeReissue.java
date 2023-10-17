package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
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

public class AdminHomeReissue extends AppCompatActivity {
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_reissue);
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


    public void onReissue(View view){
        EditText mistxt,serialtxt,datetxt;
        mistxt=findViewById(R.id.reissuemis);
        serialtxt=findViewById(R.id.reissueserial);
        datetxt=findViewById(R.id.reissuedate);
        String mis=mistxt.getText().toString();
        String serialnumber=serialtxt.getText().toString();
        String reissuedate=datetxt.getText().toString();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("UserDB");
        reference.child(mis).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){

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

                                            DataSnapshot dataSnapshot1= task.getResult();
                                            String issuedate=String.valueOf(dataSnapshot1.child("oneIssue").getValue());
                                            String currFine=String.valueOf(dataSnapshot1.child("fine").getValue());
                                            String newFine=Integer.toString(getfine(reissuedate,issuedate,2)+Integer.parseInt(currFine));
                                            String newduedate=addSevenDays(reissuedate);


                                                HashMap Usere=new HashMap();
                                                Usere.put("mis",mis);
                                                Usere.put("oneIssue",reissuedate);
                                                Usere.put("oneDue",newduedate);
                                                Usere.put("fine",newFine);

                                                DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference("UserDB");
                                                databaseReference2.child(mis).updateChildren(Usere);
                                                mistxt.setText("");
                                                serialtxt.setText("");
                                                datetxt.setText("");
                                            Toast.makeText(AdminHomeReissue.this, "Book Reissued", Toast.LENGTH_SHORT).show();

                                            }
                                            else {
                                                Toast.makeText(AdminHomeReissue.this, "Book is Already Issued", Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            Toast.makeText(AdminHomeReissue.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
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

                                            DataSnapshot dataSnapshot1= task.getResult();
                                            String issuedate=String.valueOf(dataSnapshot1.child("oneIssue").getValue());
                                            String currFine=String.valueOf(dataSnapshot1.child("fine").getValue());
                                            String newFine=Integer.toString(getfine(issuedate,reissuedate,2)+Integer.parseInt(currFine));
                                            String newduedate=addSevenDays(reissuedate);


                                            HashMap Usere=new HashMap();
                                            Usere.put("mis",mis);
                                            Usere.put("twoIssue",reissuedate);
                                            Usere.put("towDue",newduedate);
                                            Usere.put("fine",newFine);

                                            DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference("UserDB");
                                            databaseReference2.child(mis).updateChildren(Usere);
                                            mistxt.setText("");
                                            serialtxt.setText("");
                                            datetxt.setText("");
                                            Toast.makeText(AdminHomeReissue.this, "Book Reissued", Toast.LENGTH_SHORT).show();


                                        }
                                        else {
                                            Toast.makeText(AdminHomeReissue.this, "Book is Already Issued", Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(AdminHomeReissue.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(AdminHomeReissue.this, "Book is not issued to User", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(AdminHomeReissue.this, "User not exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);
    }
}