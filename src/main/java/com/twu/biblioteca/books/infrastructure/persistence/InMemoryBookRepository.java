package com.twu.biblioteca.books.infrastructure.persistence;

import com.twu.biblioteca.books.application.BookRepository;
import com.twu.biblioteca.books.core.Book;
import com.twu.biblioteca.books.core.BookBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryBookRepository implements BookRepository {
    private Map<String, Book> books;

    public InMemoryBookRepository(Collection<String[]> books) {
        this.books = books.stream().collect(Collectors.toMap(mapKey(),
                mapBook()));
    }

    @Override
    public Collection<Book> listBooks() {
        return this.books.values();
    }

    @Override
    public Optional<Book> find(String bookTitle) {
        return this.books.values().stream().filter(book -> book.getTitle().equals(bookTitle)).findFirst();
    }

    private Function<String[], Book> mapBook() {
        return values -> new BookBuilder().withTitle(values[0]).fromAuthor(values[1]).publishedInYear(Integer.parseInt(values[2])).build();
    }

    private Function<String[], String> mapKey() {
        return values -> values[0];
    }
}
