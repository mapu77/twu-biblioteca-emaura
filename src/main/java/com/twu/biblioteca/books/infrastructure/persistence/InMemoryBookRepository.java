package com.twu.biblioteca.books.infrastructure.persistence;

import com.twu.biblioteca.books.application.BookRepository;
import com.twu.biblioteca.books.core.BookInfo;
import com.twu.biblioteca.books.core.BookInfoBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryBookRepository implements BookRepository {
    private Map<String, BookInfo> books;

    public InMemoryBookRepository(Collection<String[]> books) {
        this.books = books.stream().collect(Collectors.toMap(mapKey(),
                mapBook()));
    }

    @Override
    public Collection<BookInfo> listBooks() {
        return this.books.values();
    }

    @Override
    public Optional<BookInfo> find(String bookTitle) {
        return this.books.values().stream().filter(book -> book.getTitle().equals(bookTitle)).findFirst();
    }

    private Function<String[], BookInfo> mapBook() {
        return values -> new BookInfoBuilder().withTitle(values[0]).fromAuthor(values[1]).publishedInYear(Integer.parseInt(values[2])).build();
    }

    private Function<String[], String> mapKey() {
        return values -> values[0];
    }
}
