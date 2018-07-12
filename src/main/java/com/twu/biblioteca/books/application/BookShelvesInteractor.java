package com.twu.biblioteca.books.application;

import com.twu.biblioteca.books.core.BookInfo;
import com.twu.biblioteca.books.core.BookInfoBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookShelvesInteractor implements BookShelves {

    private Map<String, BookInfo> books;

    public BookShelvesInteractor() {
        this.books = new HashMap<>();
    }

    public Collection<BookInfo> listBooks() {
        return new ArrayList<>(this.books.values());
    }

    @Override
    public void checkOut(String bookTitle) {
        if (this.books.containsKey(bookTitle))
            this.books.get(bookTitle).checkOut();
        else throw new BookNotFound();
    }

    public void preloadBooks() {
        BookInfo book1 = new BookInfoBuilder().withTitle("Harry Potter and the Philosopher's Stone").fromAuthor("J.K Rowling").publishedInYear(1997).build();
        BookInfo book2 = new BookInfoBuilder().withTitle("Game of Thrones - A Game of Thrones").fromAuthor("George R. Martin").publishedInYear(1994).build();
        BookInfo book3 = new BookInfoBuilder().withTitle("The Da Vinci Code").fromAuthor("Dan Brown").publishedInYear(2003).checkedOut().build();
        this.books.put(book1.getTitle(), book1);
        this.books.put(book2.getTitle(), book2);
        this.books.put(book3.getTitle(), book3);
    }
}
