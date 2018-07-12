package com.twu.biblioteca.books.infrastructure;

public interface AbstractBookPresenter {
    void listBooks();

    void checkOut(String bookTitle);

    void returnBook(String bookTitle);
}
