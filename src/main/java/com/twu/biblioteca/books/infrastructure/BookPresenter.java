package com.twu.biblioteca.books.infrastructure;

import com.twu.biblioteca.books.application.BookNotFound;
import com.twu.biblioteca.books.application.BookShelvesInteractor;
import com.twu.biblioteca.books.core.BookInfo;
import com.twu.biblioteca.books.core.BookNotAvailable;
import com.twu.biblioteca.books.core.NotAbleToReturnBook;

import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BookPresenter {
    private static final String SPACE_BETWEEN_COLUMNS = "\t\t";
    private static final String FANCY_LINE = "----------------------------------------";
    private static final String HEADERS = "Title" + SPACE_BETWEEN_COLUMNS + "Author" + SPACE_BETWEEN_COLUMNS + "Publication year\n" + FANCY_LINE;
    private static final String THANK_YOU_FOR_CHECKING_OUT = "Thank you! Enjoy the book";
    private static final String BOOK_NOT_AVAILABLE = "That book is not available";
    private static final String THANK_YOU_FOR_RETURNING = "Thank you for returning the book";
    private static final String NOT_VALID_BOOK = "That is not a valid book to return";

    private final BookShelvesInteractor bookShelves;
    private final PrintStream output;

    public BookPresenter(BookShelvesInteractor bookShelves, PrintStream output) {
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
        Collection<BookInfo> books = bookShelves.listBooks();
        List<BookInfo> availableBooks = books.stream().filter(BookInfo::isAvailable).collect(Collectors.toList());
        if (availableBooks.isEmpty()) output.println("There are no books in the shelves");
        else {
            output.println(HEADERS);
            for (BookInfo bookInfo : availableBooks) {
                output.println(bookInfo.getTitle() + SPACE_BETWEEN_COLUMNS + bookInfo.getAuthorName() + SPACE_BETWEEN_COLUMNS + bookInfo.getPublicationYear());
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
