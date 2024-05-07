package com.umaru.apicalls;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateBookActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText titleEdt, authorEdt;
    private Button updateBookBtn, deleteBookBtn;

    private BookApiService apiService;

    String id, title, author;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_update_book);

        titleEdt = findViewById(R.id.titleEdtEditText);
        authorEdt = findViewById(R.id.authorEdtEditText);
        updateBookBtn = findViewById(R.id.updateButton);
        deleteBookBtn = findViewById(R.id.deleteButton);

        apiService = APIClient.getRetrofitInstance().create(BookApiService.class);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");

        titleEdt.setText(title);
        authorEdt.setText(author);

        updateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book(titleEdt.getText().toString(), authorEdt.getText().toString());
                updateBook(Integer.parseInt(id), book);
                Toast.makeText(UpdateBookActivity.this, "Book telah di-Update..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateBookActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        deleteBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBook(Integer.parseInt(id));
                Toast.makeText(UpdateBookActivity.this, "Book telah di-Hapus..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateBookActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void updateBook(int id, Book book) {
        Call<Book> call = apiService.updateBook(id, book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    Book book = response.body();
                    Log.d(TAG, "Title: " + book.getTitle() + ", Author: " + book.getAuthor());
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.e(TAG, "Error updating book", t);
            }
        });
    }

    private void deleteBook(int id) {
        Call<Void> call = apiService.deleteBook(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "Book deleted successfully");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error deleting book", t);
            }
        });
    }
}
