package com.umaru.apicalls;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewBook extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Book> bookArrayList;
    private BookRVAdapter bookRVAdapter;
    private BookApiService apiService;
    private RecyclerView bookRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        apiService = APIClient.getRetrofitInstance().create(BookApiService.class);

        // Call the asynchronous method
        getBooks();
    }

    private void getBooks() {
        Call<BooksResponse> call = apiService.getBooks();
        call.enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                if (response.isSuccessful()) {
                    BooksResponse booksResponse = response.body();
                    if (booksResponse != null && booksResponse.getEmbedded() != null) {
                        List<Book> books = booksResponse.getEmbedded().getBooks();
                        if (books != null) {
                            // Update your UI or perform other operations with the received books
                            bookArrayList = books;
                            bookRVAdapter = new BookRVAdapter(bookArrayList, ViewBook.this);
                            bookRV = findViewById(R.id.idRVBook);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewBook.this, RecyclerView.VERTICAL, false);
                            bookRV.setLayoutManager(linearLayoutManager);
                            bookRV.setAdapter(bookRVAdapter);

                            // Log the books with IDs and links
                            for (Book book : books) {
                                Log.d(TAG, "Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", ID: " + book.getId());
                                if (book.getLinks() != null) {
                                    Log.d(TAG, "Self link: " + book.getLinks().getSelf().getHref());
                                    Log.d(TAG, "Book link: " + book.getLinks().getBook().getHref());
                                }
                            }
                        } else {
                            Log.e(TAG, "Books list is null");
                        }
                    } else {
                        Log.e(TAG, "Response body or _embedded is null");
                    }
                } else {
                    Log.e(TAG, "Error fetching books. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e(TAG, "Error fetching books", t);
            }
        });
    }

}
