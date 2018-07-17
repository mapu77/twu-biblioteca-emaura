package com.twu.biblioteca.accounts.infrastructure.persistence;

import com.twu.biblioteca.accounts.application.AccountRepository;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class InMemoryAccountRepositoryShould {

    @Test
    public void returnAnEmptyAccountWhenNotFindingOne() {
        AccountRepository accountRepository = new InMemoryAccountRepository(Collections.emptyList());
        assertThat(accountRepository.find("some account number"), is(Optional.empty()));
    }

    @Test
    public void returnAnAccountWhenFindingIt() {
        AccountRepository accountRepository = new InMemoryAccountRepository(Arrays.asList(new String[][] {
                new String[] {"Some name", "Some email", "Some address", "Some phone number", "some account number"}
        }));
        assertThat(accountRepository.find("some account number").isPresent(), is(true));
        assertThat(accountRepository.find("some account number").get().getName(), is("Some name"));
        assertThat(accountRepository.find("some account number").get().getEmail(), is("Some email"));
        assertThat(accountRepository.find("some account number").get().getAddress(), is("Some address"));
        assertThat(accountRepository.find("some account number").get().getPhoneNumber(), is("Some phone number"));
    }
}