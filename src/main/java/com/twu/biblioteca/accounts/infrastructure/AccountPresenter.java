package com.twu.biblioteca.accounts.infrastructure;

import com.twu.biblioteca.accounts.application.AccountInteractor;
import com.twu.biblioteca.accounts.core.Account;

import java.io.PrintStream;

public class AccountPresenter {
    private final AccountInteractor accountInteractor;
    private final PrintStream out;

    public AccountPresenter(AccountInteractor accountInteractor, PrintStream out) {
        this.accountInteractor = accountInteractor;
        this.out = out;
    }

    public void showInfoOf(String accountNumber) {
        Account account = accountInteractor.find(accountNumber);
        out.println("Name: " + account.getName());
        out.println("Email: " + account.getEmail());
        out.println("Address: " + account.getAddress());
        out.println("Phone: " + account.getPhoneNumber());
    }
}
