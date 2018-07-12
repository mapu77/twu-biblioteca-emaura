package com.twu.biblioteca.books.application;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookShelvesInteractorShould {
    @Test
    public void returnAnEmptyCollectionWhenThereAreNoBooksInIt() {
        BookShelves bookShelves = new BookShelvesInteractor();
        assertThat(bookShelves.listBooks().isEmpty(), is(true));
    }
}