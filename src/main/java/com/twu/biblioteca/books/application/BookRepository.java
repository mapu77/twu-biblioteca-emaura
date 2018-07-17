package com.twu.biblioteca.books.application;

import com.twu.biblioteca.books.core.BookInfo;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {
    Collection<BookInfo> listBooks();

    Optional<BookInfo> find(String bookTitle);
}
