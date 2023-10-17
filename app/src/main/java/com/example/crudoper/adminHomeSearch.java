package com.example.crudoper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adminHomeSearch extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<model> list;
    BookAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_search);
        recyclerView=findViewById(R.id.recv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<mod> options=
                new FirebaseRecyclerOptions.Builder<mod>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BookDB"), mod.class)
                        .build();

        adapter=new BookAdapter(options);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void onReturn(View view){
        Intent intent = new Intent(this,AdminHome.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem,menu);
        MenuItem item=menu.findItem(R.id.search_action);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void processSearch(String s){
        FirebaseRecyclerOptions<mod> options=
                new FirebaseRecyclerOptions.Builder<mod>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BookDB").orderByChild("bookName").startAt(s).endAt(s+"\uf8ff"), mod.class)
                        .build();
        adapter=new BookAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,AdminHome.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.slide_out_right,R.anim.slide_in_left);
        super.onBackPressed();
    }
}