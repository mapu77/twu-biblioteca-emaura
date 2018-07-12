package com.twu.biblioteca.books.application;

import com.twu.biblioteca.books.models.BookInfo;

import java.util.Collection;

public interface BookShelves {
    Collection<BookInfo> listBooks();
}
