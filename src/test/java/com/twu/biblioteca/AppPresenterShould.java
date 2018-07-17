package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class AppPresenterShould {

    private PrintStream outMock;
    private AppPresenter appPresenter;

    @Before
    public void setUp() {
        outMock = mock(PrintStream.class);
        appPresenter = new AppPresenter(outMock);
    }

    @Test
    public void showAWelcomeMessage() {
        appPresenter.sayWelcome();
        verify(outMock).println("Welcome to the Bangalore Public Library system");
    }

    @Test
    public void showTwoFancyLinesWhenWelcoming() {
        appPresenter.sayWelcome();
        verify(outMock, times(2)).println("----------------------------------------------");
    }

    @Test
    public void showMenuOptions() {
        appPresenter.showMenu();
        InOrder inOrder = inOrder(outMock);
        inOrder.verify(outMock).println("Choose an option:");
        inOrder.verify(outMock).println("\t1. List books");
        inOrder.verify(outMock).println("\t2. Checkout a book");
        inOrder.verify(outMock).println("\t3. Return a book");
        inOrder.verify(outMock).println("\t4. List movies");
        inOrder.verify(outMock).println("\t5. Checkout a movie");
        inOrder.verify(outMock).println("\t6. Show account details");
        inOrder.verify(outMock).println("\t0. Exit");
        verifyNoMoreInteractions(outMock);
    }

    @Test
    public void askForUserLibraryNumber() {
        appPresenter.askForLibraryNumber();
        verify(outMock).print("Library number: ");
    }

    @Test
    public void askForUserPassword() {
        appPresenter.askForPassword();
        verify(outMock).print("Password: ");
    }
}
