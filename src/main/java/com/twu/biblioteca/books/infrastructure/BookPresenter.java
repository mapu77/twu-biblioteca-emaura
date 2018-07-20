package com.twu.biblioteca.books.infrastructure;

import com.twu.biblioteca.books.application.BookNotFound;
import com.twu.biblioteca.books.application.BookShelves;
import com.twu.biblioteca.books.core.Book;
import com.twu.biblioteca.books.core.BookNotAvailable;
import com.twu.biblioteca.books.core.NotAbleToReturnBook;

import java.io.PrintStream;
import java.util.Collection;

public class BookPresenter {
    private static final String SPACE_BETWEEN_COLUMNS = "\t\t";
    private static final String FANCY_LINE = "----------------------------------------";
    private static final String HEADERS = "Title" + SPACE_BETWEEN_COLUMNS + "Author" + SPACE_BETWEEN_COLUMNS + "Publication year\n" + FANCY_LINE;
    private static final String THANK_YOU_FOR_CHECKING_OUT = "Thank you! Enjoy the book";
    private static final String BOOK_NOT_AVAILABLE = "That book is not available";
    private static final String THANK_YOU_FOR_RETURNING = "Thank you for returning the book";
    private static final String NOT_VALID_BOOK = "That is not a valid book to return";

    private final BookShelves bookShelves;
    private final PrintStream output;

    public BookPresenter(BookShelves bookShelves, PrintStream output) {
        this.bookShelves = bookShelves;
        this.output = output;
    }

    public void askForBookReturn() {
        output.println("What book do you want to return?");
    }

    public void askForBookCheckOut() {
        output.println("What book do you want to checkout?");
    }

    public void listAvailableBooks() {
        Collection<Book> availableBooks = bookShelves.listAvailableBooks();
        if (availableBooks.isEmpty()) output.println("There are no books in the shelves");
        else {
            output.println(HEADERS);
            for (Book book : availableBooks) {
                output.println(book.getTitle() + SPACE_BETWEEN_COLUMNS + book.getAuthorName() + SPACE_BETWEEN_COLUMNS + book.getPublicationYear());
            }
        }
    }

    public void checkOutBook(String bookTitle) {
        try {
            bookShelves.checkOut(bookTitle);
            output.println(THANK_YOU_FOR_CHECKING_OUT);
        } catch (BookNotFound | BookNotAvailable e) {
            output.println(BOOK_NOT_AVAILABLE);
        }
    }

    public void returnBook(String bookTitle) {
        try {
            bookShelves.returnBook(bookTitle);
            output.println(THANK_YOU_FOR_RETURNING);
        } catch (BookNotFound | NotAbleToReturnBook e) {
            output.println(NOT_VALID_BOOK);
        }
    }
}
