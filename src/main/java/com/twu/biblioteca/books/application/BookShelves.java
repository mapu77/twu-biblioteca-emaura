package com.twu.biblioteca.books.application;

import com.twu.biblioteca.books.core.Book;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookShelves {

    private final BookRepository bookRepository;

    public BookShelves(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Collection<Book> listAvailableBooks() {
        Collection<Book> books = this.bookRepository.listBooks();
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public void checkOut(String bookTitle) {
        Optional<Book> book = this.bookRepository.find(bookTitle);
        if (book.isPresent()) book.get().checkOut();
        else throw new BookNotFound();
    }

    public void returnBook(String bookTitle) {
        Optional<Book> book = this.bookRepository.find(bookTitle);
        if (book.isPresent()) book.get().returnCopy();
        else throw new BookNotFound();
    }

}
