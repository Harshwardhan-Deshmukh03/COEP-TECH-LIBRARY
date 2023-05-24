package com.example.crudoper;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AvaAdapter extends FirebaseRecyclerAdapter<model,AvaAdapter.myviewholder> {
    public AvaAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.bookname.setText(model.getAvaName());
        holder.bookno.setText(model.getAvaNo());
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder{
        TextView bookname,bookno;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            bookname=(TextView)itemView.findViewById(R.id.booksName);
            bookno=(TextView)itemView.findViewById(R.id.booksNum);

        }
    }

}
