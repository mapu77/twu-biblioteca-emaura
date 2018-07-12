package com.twu.biblioteca.books.models;

public class BookInfo {

    private final String title;
    private String authorName;
    private int publicationYear;

    BookInfo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    void setAuthorName(String author) {
        this.authorName = author;
    }

    void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}
