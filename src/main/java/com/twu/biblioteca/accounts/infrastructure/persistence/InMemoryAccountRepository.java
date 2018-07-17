package com.twu.biblioteca.accounts.infrastructure.persistence;

import com.twu.biblioteca.accounts.application.AccountRepository;
import com.twu.biblioteca.accounts.core.Account;
import com.twu.biblioteca.accounts.core.AccountBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryAccountRepository implements AccountRepository {
    private Map<String, Account> accounts;

    public InMemoryAccountRepository(Collection<String[]> accounts) {
        this.accounts = accounts.stream().collect(Collectors.toMap(mapKey(), mapAccount()));
    }

    private Function<String[], Account> mapAccount() {
        return values -> new AccountBuilder().withName(values[0]).withEmail(values[1]).withAddress(values[2]).withPhoneNumber(values[3]).build();
    }

    private Function<String[], String> mapKey() {
        return values -> values[4];
    }

    @Override
    public Optional<Account> find(String aLibraryNumber) {
        return this.accounts.containsKey(aLibraryNumber) ? Optional.of(this.accounts.get(aLibraryNumber)) : Optional.empty();
    }
}
