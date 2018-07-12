package com.twu.biblioteca.books.core;

public class BookInfo {

    private final String title;
    private String authorName;
    private int publicationYear;
    private boolean checkedOut;

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

    void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public boolean isAvailable() {
        return !checkedOut;
    }

    public void checkOut() {
        if (this.isAvailable()) checkedOut = true;
        else throw new BookNotAvailable();
    }
}
