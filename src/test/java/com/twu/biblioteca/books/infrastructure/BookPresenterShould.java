package com.twu.biblioteca.books.infrastructure;

import com.twu.biblioteca.books.application.BookShelves;
import com.twu.biblioteca.books.application.BookShelvesInteractor;
import com.twu.biblioteca.books.models.BookInfo;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class BookPresenterShould {

    @Test
    public void sayThereAreNoBooksInTheShelves() {
        BookShelves bookShelvesMock = mock(BookShelvesInteractor.class);
        when(bookShelvesMock.listBooks()).thenReturn(Collections.<BookInfo>emptyList());
        PrintStream outMock = mock(PrintStream.class);
        BookPresenter presenter = new BookPresenter(bookShelvesMock, outMock);
        presenter.listBooks();
        verify(outMock).println("There are no books in the shelves");
    }

    @Test
    public void showTheTitleOfEachBookInTheShelves() {
        BookShelves bookShelvesMock = mock(BookShelvesInteractor.class);
        when(bookShelvesMock.listBooks()).thenReturn(Arrays.asList(new BookInfo("Harry Potter"), new BookInfo("Game Of Thrones")));
        PrintStream outMock = mock(PrintStream.class);
        BookPresenter presenter = new BookPresenter(bookShelvesMock, outMock);
        presenter.listBooks();
        verify(outMock, times(1)).println("Harry Potter");
        verify(outMock, times(1)).println("Game Of Thrones");

    }
}