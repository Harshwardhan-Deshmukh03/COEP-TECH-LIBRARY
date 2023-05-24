package com.example.crudoper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder>  {

    Context context;
    ArrayList<model> list;

    public myAdapter() {
    }

    public myAdapter(Context context, ArrayList<model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        model mod=list.get(position);
        holder.bookName.setText(mod.getAvaName());
        holder.bookSerial.setText(mod.getAvaNo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView bookName,bookSerial,bookcode;

    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        bookName=itemView.findViewById(R.id.bookName);
        bookSerial=itemView.findViewById(R.id.bookNum);


    }
}
}
