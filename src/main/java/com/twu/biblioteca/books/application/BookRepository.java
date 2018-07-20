package com.twu.biblioteca.books.application;

import com.twu.biblioteca.books.core.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {
    Collection<Book> listBooks();

    Optional<Book> find(String bookTitle);
}
