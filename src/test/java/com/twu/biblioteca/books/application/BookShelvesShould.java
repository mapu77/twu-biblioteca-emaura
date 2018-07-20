package com.twu.biblioteca.books.application;

import com.twu.biblioteca.books.core.Book;
import com.twu.biblioteca.books.core.BookBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookShelvesShould {

    private BookRepository bookRepoMock;
    private BookShelves bookShelves;

    @Before
    public void setUp() {
        this.bookRepoMock = mock(BookRepository.class);
        bookShelves = new BookShelves(bookRepoMock);
    }

    @Test
    public void onlyListAvailableBooks() {
        when(bookRepoMock.listBooks()).thenReturn(Arrays.asList(
                new BookBuilder().withTitle("title 1").checkedOut().build(),
                new BookBuilder().withTitle("title 2").build()));
        Collection<Book> availableBooks = bookShelves.listAvailableBooks();
        assertThat(availableBooks.size(), is(1));
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