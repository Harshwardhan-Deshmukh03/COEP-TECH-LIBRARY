package com.example.crudoper;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BookAdapter extends FirebaseRecyclerAdapter<mod,BookAdapter.myviewholder> {


    public BookAdapter(@NonNull FirebaseRecyclerOptions<mod> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull mod model) {
        holder.book.setText(model.getBookName());
        holder.number.setText(model.getBookNumber());
        holder.allot.setText(model.getBookstatus());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.object,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView book,number,allot;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            book=(TextView)itemView.findViewById(R.id.bookName);
            number=(TextView) itemView.findViewById(R.id.bookNum);
            allot=(TextView) itemView.findViewById(R.id.issuedNo);
        }
    }


}
