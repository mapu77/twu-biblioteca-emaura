package com.twu.biblioteca.books.models;

public class BookInfoBuilder {
    private String title;
    private String author;
    private int publicationYear;

    public BookInfoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BookInfoBuilder fromAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookInfoBuilder publishedInYear(int year) {
        this.publicationYear = year;
        return this;
    }

    public BookInfo build() {
        BookInfo bookInfo = new BookInfo(title);
        bookInfo.setAuthorName(this.author);
        bookInfo.setPublicationYear(this.publicationYear);
        return bookInfo;

    }
}
