package com.twu.biblioteca.authentication.infrastructure.persistence;

import com.twu.biblioteca.authentication.application.AccessRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryAccessRepository implements AccessRepository {

    private final Map<String, String> allowedUsers;

    public InMemoryAccessRepository(Collection<String[]> allowedUsers) {
        this.allowedUsers = allowedUsers.stream().collect(Collectors.toMap(values -> values[0], values -> values[1]));
    }

    @Override
    public Optional<String> find(String validLibraryNumber) {
        return this.allowedUsers.keySet().stream().filter(key -> key.equals(validLibraryNumber)).findFirst();
    }

    @Override
    public boolean matches(String aValidLibraryNumber, String password) {
        return this.allowedUsers.get(aValidLibraryNumber).equals(password);
    }
}
