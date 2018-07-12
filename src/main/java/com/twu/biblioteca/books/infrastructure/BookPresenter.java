package com.twu.biblioteca.books.infrastructure;

import com.twu.biblioteca.books.application.BookShelves;
import com.twu.biblioteca.books.models.BookInfo;

import java.io.PrintStream;
import java.util.Collection;

public class BookPresenter implements AbstractBookPresenter {
    private final BookShelves bookShelves;
    private final PrintStream output;

    public BookPresenter(BookShelves bookShelves, PrintStream output) {
        this.bookShelves = bookShelves;
        this.output = output;
    }

    public void listBooks() {
        Collection<BookInfo> books = bookShelves.listBooks();
        if (books.isEmpty()) output.println("There are no books in the shelves");
        else {
            for (BookInfo book : books) {
                output.println(book.getTitle());
            }
        }
    }
}
