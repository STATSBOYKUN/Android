package com.umaru.apicalls;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksResponse {
    @SerializedName("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    // Nested class for embedded data
    public static class Embedded {
        @SerializedName("books")
        private List<Book> books;

        public List<Book> getBooks() {
            return books;
        }
    }
}