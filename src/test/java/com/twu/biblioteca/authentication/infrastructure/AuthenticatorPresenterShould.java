package com.twu.biblioteca.authentication.infrastructure;

import com.twu.biblioteca.authentication.application.Authenticator;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class AuthenticatorPresenterShould {

    private Authenticator authenticationMock;
    private PrintStream outMock;
    private AuthenticatorPresenter presenter;

    @Before
    public void setUp() {
        this.authenticationMock = mock(Authenticator.class);
        this.outMock = mock(PrintStream.class);
        this.presenter = new AuthenticatorPresenter(authenticationMock, outMock);
    }

    @Test
    public void confirmSuccessfulLoginAfterAccessAttempt() {
        when(authenticationMock.allows("some library number", "some password")).thenReturn(true);
        presenter.canAccess("some library number", "some password");
        verify(outMock).println("Successful login");
    }

    @Test
    public void warnLoginFailureAfterAccessAttempt() {
        when(authenticationMock.allows("some library number", "some password")).thenReturn(false);
        presenter.canAccess("some library number", "some password");
        verify(outMock).println("Sorry, incorrect library number or password");
    }

    @Test
    public void warnInvalidLibraryNumberInput() {
        presenter.warnInvalidLibraryNumber();
        verify(outMock).println("Invalid library number (remember xxx-xxxx)");
    }
}