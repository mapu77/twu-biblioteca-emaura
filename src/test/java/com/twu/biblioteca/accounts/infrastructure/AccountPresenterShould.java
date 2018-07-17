package com.twu.biblioteca.accounts.infrastructure;

import com.twu.biblioteca.accounts.application.AccountInteractor;
import com.twu.biblioteca.accounts.core.AccountBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class AccountPresenterShould {
    private AccountInteractor accountInteractorMock;
    private PrintStream outMock;
    private AccountPresenter accountPresenter;

    @Before
    public void setUp() {
        accountInteractorMock = mock(AccountInteractor.class);
        outMock = mock(PrintStream.class);
        accountPresenter = new AccountPresenter(accountInteractorMock, outMock);
    }

    @Test
    public void printAccountNameWhenAskingForAccountDetails() {
        when(accountInteractorMock.find("Some account")).thenReturn(new AccountBuilder().withName("A name").build());
        accountPresenter.showInfoOf("Some account");
        verify(outMock).println("Name: A name");
    }

    @Test
    public void printAccountEmailWhenAskingForAccountDetails() {
        when(accountInteractorMock.find("Some account")).thenReturn(new AccountBuilder().withEmail("An email").build());
        accountPresenter.showInfoOf("Some account");
        verify(outMock).println("Email: An email");
    }

    @Test
    public void printAccountAddressWhenAskingForAccountDetails() {
        when(accountInteractorMock.find("Some account")).thenReturn(new AccountBuilder().withAddress("An address").build());
        accountPresenter.showInfoOf("Some account");
        verify(outMock).println("Address: An address");
    }

    @Test
    public void printAccountPhoneNumberWhenAskingForAccountDetails() {
        when(accountInteractorMock.find("Some account")).thenReturn(new AccountBuilder().withPhoneNumber("A phone number").build());
        accountPresenter.showInfoOf("Some account");
        verify(outMock).println("Phone: A phone number");
    }
}