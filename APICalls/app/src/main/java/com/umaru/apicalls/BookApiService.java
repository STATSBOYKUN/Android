package com.umaru.apicalls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookApiService {
    @GET("/books")
    Call<BooksResponse> getBooks();

    @GET("/books/{id}")
    Call<Book> getBookById(@Path("id") int id);

    @POST("/books")
    Call<Book> createBook(@Body Book book);

    @PUT("/books/{id}")
    Call<Book> updateBook(@Path("id") int id, @Body Book book);

    @DELETE("/books/{id}")
    Call<Void> deleteBook(@Path("id") int id);
}
