package com.twu.biblioteca.books.application;

import com.twu.biblioteca.books.models.BookInfo;

import java.util.ArrayList;
import java.util.Collection;

public class BookShelvesInteractor implements BookShelves {

    public Collection<BookInfo> listBooks() {
        return new ArrayList<BookInfo>();
    }
}
