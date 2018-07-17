package com.twu.biblioteca.authentication.application;

import java.util.Optional;

public interface AccessRepository {
    Optional<String> find(String validLibraryNumber);

    boolean matches(String aValidLibraryNumber, String password);
}
