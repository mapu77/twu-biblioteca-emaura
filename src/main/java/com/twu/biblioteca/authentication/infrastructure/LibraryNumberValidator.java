package com.twu.biblioteca.authentication.infrastructure;

class LibraryNumberValidator {
    private final String value;

    LibraryNumberValidator(String value) {
        verify(value);
        this.value = value;
    }

    private void verify(String value) {
        if (!value.matches("(.{3})-(.{4})")) throw new InvalidLibraryNumber();
    }

    String getValue() {
        return this.value;
    }
}
