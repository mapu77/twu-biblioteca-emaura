package com.twu.biblioteca.books.core;

public class BookInfoBuilder {
    private String title;
    private String author;
    private int publicationYear;
    private boolean checkedOut;

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

    public Book build() {
        Book book = new Book(title);
        book.setAuthorName(this.author);
        book.setPublicationYear(this.publicationYear);
        book.setCheckedOut(checkedOut);
        return book;

    }

    public BookInfoBuilder checkedOut() {
        this.checkedOut = true;
        return this;
    }
}
