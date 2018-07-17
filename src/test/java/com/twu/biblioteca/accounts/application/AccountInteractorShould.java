package com.twu.biblioteca.accounts.application;

import com.twu.biblioteca.accounts.core.Account;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountInteractorShould {

    private AccountRepository accountRepoMock;
    private AccountInteractor accountInteractor;

    @Before
    public void setUp() {
        accountRepoMock = mock(AccountRepository.class);
        accountInteractor = new AccountInteractor(accountRepoMock);
    }

    @Test (expected = AccountNotFound.class)
    public void throwAccountNotFoundWhenAccountIsNotInRepo() {
        when(accountRepoMock.find("aLibraryNumber")).thenReturn(Optional.empty());
        accountInteractor.find("aLibraryNumber");
    }

    @Test
    public void returnAnAccountWhenItIsInTheRepo() {
        Account value = new Account();
        when(accountRepoMock.find("aLibraryNumber")).thenReturn(Optional.of(value));
        Account expectedAccount = accountInteractor.find("aLibraryNumber");
        assertThat(expectedAccount, is(value));
    }
}