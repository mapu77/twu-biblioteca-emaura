package com.twu.biblioteca.books.application;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookShelvesInteractorShould {

    private BookRepository bookRepoMock;
    private BookShelvesInteractor bookShelves;

    @Before
    public void setUp() {
        this.bookRepoMock = mock(BookRepository.class);
        bookShelves = new BookShelvesInteractor(bookRepoMock);
    }

    @Test(expected = BookNotFound.class)
    public void throwBookNotFoundWhenTryingToCheckOutABookThatIsNotAvailable() {
        when(bookRepoMock.find("not existing book")).thenReturn(Optional.empty());
        bookShelves.checkOut("not existing book");
    }

    @Test(expected = BookNotFound.class)
    public void throwBookNotFoundWhenTryingToReturnABookThatIsNotAvailable() {
        when(bookRepoMock.find("not existing book")).thenReturn(Optional.empty());
        bookShelves.returnBook("not existing book");
    }
}