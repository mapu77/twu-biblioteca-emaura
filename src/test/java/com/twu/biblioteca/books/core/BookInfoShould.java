package com.twu.biblioteca.books.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookInfoShould {

    @Test
    public void beAvailableWhenCreated() {
        BookInfo book = new BookInfo("some title");
        assertThat(book.isAvailable(), is(true));
    }

    @Test
    public void beNotAvailableWhenCheckedOut() {
        BookInfo book = new BookInfo("some title");
        book.checkOut();
        assertThat(book.isAvailable(), is(false));
    }

    @Test(expected = BookNotAvailable.class)
    public void throwBookNotAvailableWhenTryingToCheckOutBeingNotAvailable() {
        BookInfo book = new BookInfo("some title");
        book.checkOut();
        book.checkOut();
    }

    @Test(expected = NotAbleToReturnBook.class)
    public void throwNotAbleToReturnWhenTryingToReturnBeingAvailable() {
        BookInfo book = new BookInfo("some title");
        book.returnCopy();
    }

    @Test
    public void beAvailableAfterReturned() {
        BookInfo book = new BookInfo("some title");
        book.checkOut();
        book.returnCopy();
        assertThat(book.isAvailable(), is(true));
    }
}