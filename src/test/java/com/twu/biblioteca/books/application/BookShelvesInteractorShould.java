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

    @Test
    public void haveSomeBooksWhenPreloaded() {
        BookShelvesInteractor bookShelves = new BookShelvesInteractor();
        bookShelves.preloadBooks();
        assertThat(bookShelves.listBooks().isEmpty(), is(false));
    }

    @Test(expected = BookNotFound.class)
    public void throwBookNotFoundWhenTryingToCheckOutABookThatIsNotAvailable() {
        BookShelvesInteractor bookShelves = new BookShelvesInteractor();
        bookShelves.checkOut("not existing book");
    }

    @Test(expected = BookNotFound.class)
    public void throwBookNotFoundWhenTryingToReturnABookThatIsNotAvailable() {
        BookShelvesInteractor bookShelves = new BookShelvesInteractor();
        bookShelves.returnBook("not existing book");
    }
}