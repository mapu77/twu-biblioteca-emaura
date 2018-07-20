package com.twu.biblioteca.books.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookShould {

    @Test
    public void beAvailableWhenCreated() {
        Book book = new Book("some title");
        assertThat(book.isAvailable(), is(true));
    }

    @Test
    public void beNotAvailableWhenCheckedOut() {
        Book book = new Book("some title");
        book.checkOut();
        assertThat(book.isAvailable(), is(false));
    }

    @Test(expected = BookNotAvailable.class)
    public void throwBookNotAvailableWhenTryingToCheckOutBeingNotAvailable() {
        Book book = new Book("some title");
        book.checkOut();
        book.checkOut();
    }

    @Test(expected = NotAbleToReturnBook.class)
    public void throwNotAbleToReturnWhenTryingToReturnBeingAvailable() {
        Book book = new Book("some title");
        book.returnCopy();
    }

    @Test
    public void beAvailableAfterReturned() {
        Book book = new Book("some title");
        book.checkOut();
        book.returnCopy();
        assertThat(book.isAvailable(), is(true));
    }
}