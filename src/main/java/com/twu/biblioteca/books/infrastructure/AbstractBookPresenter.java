package com.twu.biblioteca.books.infrastructure;

public interface AbstractBookPresenter {
    void askForBookReturn();

    void askForBookCheckOut();

    void listBooks();

    void checkOutBook(String bookTitle);

    void returnBook(String bookTitle);
}
