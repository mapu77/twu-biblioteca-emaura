package com.twu.biblioteca.books.application;

import com.twu.biblioteca.books.core.BookInfo;
import com.twu.biblioteca.books.infrastructure.BookShelves;

import java.util.Collection;
import java.util.Optional;

public class BookShelvesInteractor implements BookShelves {

    private final BookRepository bookRepository;

    public BookShelvesInteractor(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Collection<BookInfo> listBooks() {
        return this.bookRepository.listBooks();
    }

    @Override
    public void checkOut(String bookTitle) {
        Optional<BookInfo> book = this.bookRepository.find(bookTitle);
        if (book.isPresent()) book.get().checkOut();
        else throw new BookNotFound();
    }

    @Override
    public void returnBook(String bookTitle) {
        Optional<BookInfo> book = this.bookRepository.find(bookTitle);
        if (book.isPresent()) book.get().returnCopy();
        else throw new BookNotFound();
    }

}
