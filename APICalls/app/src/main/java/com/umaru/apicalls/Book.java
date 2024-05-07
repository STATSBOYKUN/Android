package com.umaru.apicalls;

import com.google.gson.annotations.SerializedName;

public class Book {
    private String title;
    private String author;
    private String id; // Added book ID field

    @SerializedName("_links")
    private Links links;

    public Book() {
    }
    // Getters and setters for existing fields
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters and setters for new field
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    // Nested class for links
    public static class Links {
        @SerializedName("self")
        private Link self;

        @SerializedName("book")
        private Link book;

        public Link getSelf() {
            return self;
        }

        public void setSelf(Link self) {
            this.self = self;
        }

        public Link getBook() {
            return book;
        }

        public void setBook(Link book) {
            this.book = book;
        }
    }

    // Nested class for a single link
    public static class Link {
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}

