package com.umaru.apicalls;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.ViewHolder> {
    private List<Book> bookArrayList;
    private Context context;

    public BookRVAdapter(List<Book> bookArrayList, Context context) {
        this.bookArrayList = bookArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.book_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = bookArrayList.get(position);

        holder.idTV.setText(book.getId());
        holder.titleTV.setText(book.getTitle());
        holder.authorTV.setText(book.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateBookActivity.class);
                i.putExtra("id", book.getId());
                i.putExtra("title", book.getTitle());
                i.putExtra("author", book.getAuthor());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bookArrayList.size() == 0) {
            return 1;
        }

        return bookArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV, authorTV, idTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTV = itemView.findViewById(R.id.idTextView);
            titleTV = itemView.findViewById(R.id.titleTextView);
            authorTV = itemView.findViewById(R.id.authorTextView);
        }
    }
}
