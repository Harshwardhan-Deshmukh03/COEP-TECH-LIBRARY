package com.example.crudoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reference;
    EditText misbox,passbox;
    Button btn;
    public static final String EXTRA_NAME="com.example.crudoper.extra.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Spinner spinner= findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.choices,android.R.layout.select_dialog_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinner.setAdapter(adapter);
        btn=findViewById(R.id.button12);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String flow=spinner.getSelectedItem().toString();
                if (flow.equals("Student")){
                    loginuser();
                }
                if (flow.equals("Admin")){
                    loginadmin();
                }
                if (flow.equals("Select User")){
                    Toast.makeText(MainActivity.this, "Select User", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
//        spinner.setOnItemClickListener();


    }

    @Override
    protected void onResume() {
        super.onResume();
        Button button=findViewById(R.id.button12);
        button.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
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

    public void onClickLogIn(View view){
        misbox=findViewById(R.id.loginmis);
        passbox=findViewById(R.id.loginpass);
        String mis=misbox.getText().toString();
        String password=passbox.getText().toString();
        String flow;
        if (mis.equals("")||password.equals("")){
            Toast.makeText(this, "Enter MIS and Password", Toast.LENGTH_SHORT).show();
            return;
        }




    }


    public void loginuser(){
        misbox=findViewById(R.id.loginmis);
        passbox=findViewById(R.id.loginpass);
        String mis=misbox.getText().toString();
        String password=passbox.getText().toString();
        if (mis.equals("")||password.equals("")){
            Toast.makeText(this, "Enter MIS and Password", Toast.LENGTH_SHORT).show();
            return;
        }
        readDataUser(mis,password);



    }
    public void loginadmin(){
        misbox=findViewById(R.id.loginmis);
        passbox=findViewById(R.id.loginpass);
        String mis=misbox.getText().toString();
        String password=passbox.getText().toString();
        if (mis.equals("")||password.equals("")){
            Toast.makeText(this, "Enter MIS and Password", Toast.LENGTH_SHORT).show();
            return;
        }
        readDataAdmin(mis,password);
    }

    private void readDataUser(String username,String pass){
        reference= FirebaseDatabase.getInstance().getReference("UserDB");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot= task.getResult();
//                        String name=String.valueOf(dataSnapshot.child("name").getValue());
                        String password=String.valueOf(dataSnapshot.child("password").getValue());
                        if (password.equals(pass)){

                            Intent intent=new Intent(getApplicationContext(),UserHome.class);
                            intent.putExtra(EXTRA_NAME,username);
                            Button b=findViewById(R.id.button12);
                            b.setEnabled(false);
                            startActivity(intent);

                            misbox.setText("");
                            passbox.setText("");
                        }else{
                            Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }


                    }else {
                        Toast.makeText(MainActivity.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void readDataAdmin(String username,String pass){
        reference= FirebaseDatabase.getInstance().getReference("AdminDB");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
//                        Toast.makeText(MainActivity.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot= task.getResult();
                        String password=String.valueOf(dataSnapshot.child("password").getValue());
//                        String username=String.valueOf(dataSnapshot.child("username").getValue());
                        if (password.equals(pass)){
                            Intent intent=new Intent(getApplicationContext(),AdminHome.class);
                            Button b=findViewById(R.id.button12);
                            b.setEnabled(false);
                            startActivity(intent);
                            misbox.setText("");
                            passbox.setText("");
                        }else{
                            Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(MainActivity.this, "Invalid MIS", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}