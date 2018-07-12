package com.twu.biblioteca.books.infrastructure;

import com.twu.biblioteca.books.application.BookNotFound;
import com.twu.biblioteca.books.application.BookShelves;
import com.twu.biblioteca.books.application.BookShelvesInteractor;
import com.twu.biblioteca.books.core.BookInfoBuilder;
import com.twu.biblioteca.books.core.BookNotAvailable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class BookPresenterShould {

    private BookShelves bookShelvesMock;
    private PrintStream outMock;
    private BookPresenter presenter;

    @Before
    public void setUp() {
        bookShelvesMock = mock(BookShelvesInteractor.class);
        outMock = mock(PrintStream.class);
        presenter = new BookPresenter(bookShelvesMock, outMock);
    }

    @Test
    public void sayThereAreNoBooksInTheShelves() {
        when(bookShelvesMock.listBooks()).thenReturn(Collections.emptyList());
        presenter.listBooks();
        verify(outMock).println("There are no books in the shelves");
    }

    @Test
    public void showTheHeadersBeforeAnyBookInfo() {
        when(bookShelvesMock.listBooks()).thenReturn(Arrays.asList(
                new BookInfoBuilder()
                        .withTitle("Harry Potter and the Philosopher's Stone")
                        .fromAuthor("J.K. Rowling")
                        .publishedInYear(1997)
                        .build()));
        InOrder inOrder = inOrder(outMock);
        presenter.listBooks();
        inOrder.verify(outMock).println("Title\t\tAuthor\t\tPublication year\n----------------------------------------");
    }

    @Test
    public void showAsManyBooksAsThereAreInTheShelves() {
        when(bookShelvesMock.listBooks()).thenReturn(Arrays.asList(
                new BookInfoBuilder()
                        .withTitle("Harry Potter and the Philosopher's Stone")
                        .fromAuthor("J.K. Rowling")
                        .publishedInYear(1997)
                        .build(),
                new BookInfoBuilder()
                        .withTitle("Game of Thrones - A Game of Thrones")
                        .fromAuthor("George R. Martin")
                        .publishedInYear(1996)
                        .build()));
        InOrder inOrder = inOrder(outMock);
        presenter.listBooks();
        inOrder.verify(outMock).println("Title\t\tAuthor\t\tPublication year\n----------------------------------------");
        inOrder.verify(outMock, times(2)).println(anyString());
    }

    @Test
    public void showBooksAlphabeticallySorted() {
        when(bookShelvesMock.listBooks()).thenReturn(Arrays.asList(
                new BookInfoBuilder()
                        .withTitle("Harry Potter and the Philosopher's Stone")
                        .fromAuthor("J.K. Rowling")
                        .publishedInYear(1997)
                        .build(),
                new BookInfoBuilder()
                        .withTitle("Game of Thrones - A Game of Thrones")
                        .fromAuthor("George R. Martin")
                        .publishedInYear(1996)
                        .build()));
        presenter.listBooks();
        InOrder inOrder = inOrder(outMock);
        inOrder.verify(outMock).println("Title\t\tAuthor\t\tPublication year\n----------------------------------------");
        inOrder.verify(outMock).println("Game of Thrones - A Game of Thrones\t\tGeorge R. Martin\t\t1996");
        inOrder.verify(outMock).println("Harry Potter and the Philosopher's Stone\t\tJ.K. Rowling\t\t1997");
    }

    @Test
    public void notShowCheckOutBooks() {
        when(bookShelvesMock.listBooks()).thenReturn(Arrays.asList(
                new BookInfoBuilder()
                        .withTitle("Harry Potter and the Philosopher's Stone")
                        .fromAuthor("J.K. Rowling")
                        .publishedInYear(1997)
                        .checkedOut()
                        .build()));

        presenter.listBooks();
        verify(outMock).println("There are no books in the shelves");
    }

    @Test
    public void sayThankYouWhenCheckingOutABook() {
        when(bookShelvesMock.listBooks()).thenReturn(Arrays.asList(
                new BookInfoBuilder()
                        .withTitle("Harry Potter and the Philosopher's Stone")
                        .fromAuthor("J.K. Rowling")
                        .publishedInYear(1997)
                        .build()));
        presenter.checkOut("Harry Potter and the Philosopher's Stone");
        verify(outMock).println("Thank you! Enjoy the book");
    }

    @Test
    public void sayTheBookIsNotAvailableWhenTheBookIsCheckedOut() {
        doThrow(BookNotAvailable.class).when(bookShelvesMock).checkOut(anyString());
        presenter.checkOut("Harry Potter and the Philosopher's Stone");
        verify(outMock).println("That book is not available");
    }

    @Test
    public void sayTheBookIsNotAvailableWhenTheBookIsNotInTheShelves() {
        doThrow(BookNotFound.class).when(bookShelvesMock).checkOut(anyString());
        presenter.checkOut("Harry Potter and the Philosopher's Stone");
        verify(outMock).println("That book is not available");
    }
}