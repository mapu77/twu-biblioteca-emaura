package com.twu.biblioteca.books.infrastructure;

import com.twu.biblioteca.books.application.BookNotFound;
import com.twu.biblioteca.books.application.BookShelves;
import com.twu.biblioteca.books.core.BookInfo;
import com.twu.biblioteca.books.core.BookNotAvailable;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookPresenter implements AbstractBookPresenter {
    private static final String SPACE_BETWEEN_COLUMNS = "\t\t";
    private static final String FANCY_LINE = "----------------------------------------";
    private static final String HEADERS = "Title" + SPACE_BETWEEN_COLUMNS + "Author" + SPACE_BETWEEN_COLUMNS + "Publication year\n" + FANCY_LINE;
    private static final String THANK_YOU_FOR_CHECKING_OUT = "Thank you! Enjoy the book";

    private final BookShelves bookShelves;
    private final PrintStream output;

    public BookPresenter(BookShelves bookShelves, PrintStream output) {
        this.bookShelves = bookShelves;
        this.output = output;
    }

    public void listBooks() {
        List<BookInfo> books = (List<BookInfo>) bookShelves.listBooks();
        books.sort(Comparator.comparing(BookInfo::getTitle));
        List<BookInfo> filteredBooks = books.stream().filter(BookInfo::isAvailable).collect(Collectors.toList());
        if (filteredBooks.isEmpty()) output.println("There are no books in the shelves");
        else {
            output.println(HEADERS);
            for (BookInfo bookInfo : filteredBooks) {
                output.println(bookInfo.getTitle() + SPACE_BETWEEN_COLUMNS + bookInfo.getAuthorName() + SPACE_BETWEEN_COLUMNS + bookInfo.getPublicationYear());
            }
        }
    }

    @Override
    public void checkOut(String bookTitle) {
        try {
            bookShelves.checkOut(bookTitle);
            output.println(THANK_YOU_FOR_CHECKING_OUT);
        } catch (BookNotFound | BookNotAvailable e) {
            output.println("That book is not available");
        }
    }
}
