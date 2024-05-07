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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BookApiService apiService;
    private EditText title, author;
    private Button addBook, lihatBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = APIClient.getRetrofitInstance().create(BookApiService.class);

        title = findViewById(R.id.titleEditText);
        author = findViewById(R.id.authorEditText);
        addBook = findViewById(R.id.tambahButton);
        lihatBook = findViewById(R.id.lihatButton);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleBook = title.getText().toString();
                String authorBook = author.getText().toString();

                if (titleBook.isEmpty() && authorBook.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Lengkapilah semua data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                Book book = new Book(titleBook, authorBook);
                createBook(book);
                Toast.makeText(MainActivity.this, "Data Book Telah Ditambahkan", Toast.LENGTH_SHORT).show();

                title.setText("");
                author.setText("");
            }
        });

        lihatBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewBook.class);
                startActivity(i);
            }
        });
    }

    private void createBook(Book book) {
        Call<Book> call = apiService.createBook(book);
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
                Log.e(TAG, "Error creating book", t);
            }
        });
    }
}