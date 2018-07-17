package com.twu.biblioteca.authentication.application;

import java.util.Optional;

public class Authenticator {
    private final AccessRepository accessRepository;

    public Authenticator(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    public boolean allows(String aLibraryNumber, String password) {
        Optional<String> libraryNumber = accessRepository.find(aLibraryNumber);
        return libraryNumber.filter(s -> accessRepository.matches(s, password)).isPresent();
    }

}
