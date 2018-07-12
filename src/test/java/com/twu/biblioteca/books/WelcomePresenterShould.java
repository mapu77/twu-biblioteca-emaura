package com.twu.biblioteca.books;

import com.twu.biblioteca.WelcomePresenter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class WelcomePresenterShould {

    private PrintStream outMock;
    private WelcomePresenter welcomePresenter;

    @Before
    public void setUp() {
        outMock = mock(PrintStream.class);
        welcomePresenter = new WelcomePresenter(outMock);
    }

    @Test
    public void showAWelcomeMessage() {
        welcomePresenter.sayWelcome();
        verify(outMock).println("Welcome to the Bangalore Public Library system");
    }

    @Test
    public void showTwoFancyLinesWhenWelcoming() {
        welcomePresenter.sayWelcome();
        verify(outMock, times(2)).println("----------------------------------------------");
    }

    @Test
    public void leaveALineAfterTheWelcome() {
        welcomePresenter.sayWelcome();
        verify(outMock).println("\n");
    }

    @Test
    public void showMenuOptions() {
        welcomePresenter.showMenu();
        InOrder inOrder = inOrder(outMock);
        inOrder.verify(outMock).println("Choose an option:");
        inOrder.verify(outMock).println("\t1. List books");
    }
}
