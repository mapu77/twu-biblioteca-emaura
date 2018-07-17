package com.twu.biblioteca.accounts.application;

import com.twu.biblioteca.accounts.core.Account;

import java.util.Optional;

public class AccountInteractor {
    private final AccountRepository accountRepository;

    public AccountInteractor(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account find(String libraryNumber) {
        Optional<Account> account = this.accountRepository.find(libraryNumber);
        if (account.isPresent()) return account.get();
        else throw new AccountNotFound();
    }
}
