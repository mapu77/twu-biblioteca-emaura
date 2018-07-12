package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class OptionPresenterShould {

    private PrintStream outMock;
    private OptionPresenter optionPresenter;

    @Before
    public void setUp() {
        outMock = mock(PrintStream.class);
        optionPresenter = new OptionPresenter(outMock);
    }

    @Test
    public void showAWelcomeMessage() {
        optionPresenter.sayWelcome();
        verify(outMock).println("Welcome to the Bangalore Public Library system");
    }

    @Test
    public void showTwoFancyLinesWhenWelcoming() {
        optionPresenter.sayWelcome();
        verify(outMock, times(2)).println("----------------------------------------------");
    }

    @Test
    public void showMenuOptions() {
        optionPresenter.showMenu();
        InOrder inOrder = inOrder(outMock);
        inOrder.verify(outMock).println("Choose an option:");
        inOrder.verify(outMock).println("\t1. List books");
        inOrder.verify(outMock).println("\t2. Checkout a book");
        inOrder.verify(outMock).println("\t3. Return a book");
        inOrder.verify(outMock).println("\t0. Exit");
        verifyNoMoreInteractions(outMock);
    }
}
