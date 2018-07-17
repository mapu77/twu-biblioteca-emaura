package com.twu.biblioteca.accounts.application;

import com.twu.biblioteca.accounts.core.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> find(String aLibraryNumber);
}
