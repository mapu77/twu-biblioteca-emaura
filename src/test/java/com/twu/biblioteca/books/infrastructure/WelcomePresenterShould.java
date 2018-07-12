package com.twu.biblioteca.books.infrastructure;

import com.twu.biblioteca.WelcomePresenter;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class WelcomePresenterShould {

    @Test
    public void showAWelcomeMessage() {
        PrintStream fakeOut = mock(PrintStream.class);
        WelcomePresenter welcomePresenter = new WelcomePresenter(fakeOut);
        welcomePresenter.sayWelcome();
        verify(fakeOut).println("Welcome to the Bangalore Public Library system");
    }

    @Test
    public void showTwoFancyLinesWhenWelcoming() {
        PrintStream fakeOut = mock(PrintStream.class);
        WelcomePresenter welcomePresenter = new WelcomePresenter(fakeOut);
        welcomePresenter.sayWelcome();
        verify(fakeOut, times(2)).println("----------------------------------------------");
    }

    @Test
    public void leaveALineAfterTheWelcome() {
        PrintStream fakeOut = mock(PrintStream.class);
        WelcomePresenter welcomePresenter = new WelcomePresenter(fakeOut);
        welcomePresenter.sayWelcome();
        verify(fakeOut).println("\n");
    }
}
