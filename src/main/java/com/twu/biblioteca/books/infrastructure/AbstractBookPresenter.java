package com.twu.biblioteca.books.infrastructure;

public interface AbstractBookPresenter {
    void listBooks();

    void checkOutBook(String bookTitle);

    void returnBook(String bookTitle);
}
