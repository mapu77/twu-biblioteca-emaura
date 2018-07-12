package com.twu.biblioteca.books.application;

import com.twu.biblioteca.books.core.BookInfo;

import java.util.Collection;

public interface BookShelves {
    Collection<BookInfo> listBooks();

    void checkOut(String bookTitle) throws BookNotFound;

    void returnBook(String bookTitle) throws BookNotFound;
}
