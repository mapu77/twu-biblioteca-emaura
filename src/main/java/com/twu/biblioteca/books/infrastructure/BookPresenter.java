package com.twu.biblioteca.books.infrastructure;

import com.twu.biblioteca.books.application.BookShelves;
import com.twu.biblioteca.books.models.BookInfo;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookPresenter implements AbstractBookPresenter {
    private static final String SPACE_BETWEEN_COLUMNS = "\t\t";
    private static final String FANCY_LINE = "----------------------------------------";
    private static final String HEADERS = "Title" + SPACE_BETWEEN_COLUMNS + "Author" + SPACE_BETWEEN_COLUMNS + "Publication year\n"+FANCY_LINE;
    private final BookShelves bookShelves;
    private final PrintStream output;

    public BookPresenter(BookShelves bookShelves, PrintStream output) {
        this.bookShelves = bookShelves;
        this.output = output;
    }

    public void listBooks() {
        Collection<BookInfo> books = bookShelves.listBooks();
        Collections.sort((List<BookInfo>) books, new Comparator<BookInfo>() {
            public int compare(BookInfo book1, BookInfo book2) {
                return book1.getTitle().compareTo(book2.getTitle());
            }
        });
        if (books.isEmpty()) output.println("There are no books in the shelves");
        else {
            output.println(HEADERS);
            for (BookInfo bookInfo : books) {
                output.println(bookInfo.getTitle() + SPACE_BETWEEN_COLUMNS + bookInfo.getAuthorName() + SPACE_BETWEEN_COLUMNS + bookInfo.getPublicationYear());
            }
        }
    }
}
