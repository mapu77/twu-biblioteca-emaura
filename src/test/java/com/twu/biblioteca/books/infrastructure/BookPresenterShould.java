package com.twu.biblioteca.books.infrastructure;

import com.twu.biblioteca.books.application.BookNotFound;
import com.twu.biblioteca.books.application.BookShelves;
import com.twu.biblioteca.books.core.BookBuilder;
import com.twu.biblioteca.books.core.BookNotAvailable;
import com.twu.biblioteca.books.core.NotAbleToReturnBook;
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
        bookShelvesMock = mock(BookShelves.class);
        outMock = mock(PrintStream.class);
        presenter = new BookPresenter(bookShelvesMock, outMock);
    }

    @Test
    public void sayThereAreNoBooksInTheShelves() {
        when(bookShelvesMock.listAvailableBooks()).thenReturn(Collections.emptyList());
        presenter.listAvailableBooks();
        verify(outMock).println("There are no books in the shelves");
    }

    @Test
    public void showTheHeadersBeforeAnyBookInfo() {
        when(bookShelvesMock.listAvailableBooks()).thenReturn(Collections.singletonList(
                new BookBuilder()
                        .withTitle("Harry Potter and the Philosopher's Stone")
                        .fromAuthor("J.K. Rowling")
                        .publishedInYear(1997)
                        .build()));
        InOrder inOrder = inOrder(outMock);
        presenter.listAvailableBooks();
        inOrder.verify(outMock).println("Title\t\tAuthor\t\tPublication year\n----------------------------------------");
    }

    @Test
    public void showAsManyBooksAsThereAreInTheShelves() {
        when(bookShelvesMock.listAvailableBooks()).thenReturn(Arrays.asList(
                new BookBuilder()
                        .withTitle("Harry Potter and the Philosopher's Stone")
                        .fromAuthor("J.K. Rowling")
                        .publishedInYear(1997)
                        .build(),
                new BookBuilder()
                        .withTitle("Game of Thrones - A Game of Thrones")
                        .fromAuthor("George R. Martin")
                        .publishedInYear(1996)
                        .build()));
        InOrder inOrder = inOrder(outMock);
        presenter.listAvailableBooks();
        inOrder.verify(outMock).println("Title\t\tAuthor\t\tPublication year\n----------------------------------------");
        inOrder.verify(outMock, times(2)).println(anyString());
    }

    @Test
    public void sayThankYouWhenCheckingOutABook() {
        when(bookShelvesMock.listAvailableBooks()).thenReturn(Collections.singletonList(
                new BookBuilder()
                        .withTitle("Harry Potter and the Philosopher's Stone")
                        .fromAuthor("J.K. Rowling")
                        .publishedInYear(1997)
                        .build()));
        presenter.checkOutBook("Harry Potter and the Philosopher's Stone");
        verify(outMock).println("Thank you! Enjoy the book");
    }

    @Test
    public void sayTheBookIsNotAvailableWhenTheBookIsCheckedOut() {
        doThrow(BookNotAvailable.class).when(bookShelvesMock).checkOut(anyString());
        presenter.checkOutBook("Harry Potter and the Philosopher's Stone");
        verify(outMock).println("That book is not available");
    }

    @Test
    public void sayTheBookIsNotAvailableWhenTheBookIsNotInTheShelves() {
        doThrow(BookNotFound.class).when(bookShelvesMock).checkOut(anyString());
        presenter.checkOutBook("Harry Potter and the Philosopher's Stone");
        verify(outMock).println("That book is not available");
    }

    @Test
    public void sayThanksForReturningABook() {
        when(bookShelvesMock.listAvailableBooks()).thenReturn(Collections.singletonList(
                new BookBuilder()
                        .withTitle("Harry Potter and the Philosopher's Stone")
                        .fromAuthor("J.K. Rowling")
                        .publishedInYear(1997)
                        .checkedOut()
                        .build()));
        presenter.returnBook("Harry Potter and the Philosopher's Stone");
        verify(outMock).println("Thank you for returning the book");
    }

    @Test
    public void sayTheBookIsNotValidToReturnWhenBookIsNotFound() {
        doThrow(BookNotFound.class).when(bookShelvesMock).returnBook(anyString());
        presenter.returnBook("Harry Potter and the Philosopher's Stone");
        verify(outMock).println("That is not a valid book to return");
    }

    @Test
    public void sayTheBookIsNotValidToReturnWhenBookIsYetAvailable() {
        doThrow(NotAbleToReturnBook.class).when(bookShelvesMock).returnBook(anyString());
        presenter.returnBook("Harry Potter and the Philosopher's Stone");
        verify(outMock).println("That is not a valid book to return");
    }

    @Test
    public void askForBookCheckOut() {
        presenter.askForBookCheckOut();
        verify(outMock).println("What book do you want to checkout?");
    }

    @Test
    public void askForBookReturn() {
        presenter.askForBookReturn();
        verify(outMock).println("What book do you want to return?");
    }

}