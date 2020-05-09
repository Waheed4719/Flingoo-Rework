package com.example.practice.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Helpers.BookHelper;
import com.example.practice.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    ArrayList<BookHelper> Book;

    public CardAdapter(ArrayList<BookHelper> Book) {
        this.Book = Book;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        BookHelper bookHelper = Book.get(position);
        holder.image.setImageResource(bookHelper.getImage());
        holder.title.setText(bookHelper.getTitle());
        holder.author.setText(bookHelper.getAuthor());

    }


    @Override
    public int getItemCount() {
        return Book.size();
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title,author;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
        }
    }
}
