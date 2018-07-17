package com.twu.biblioteca.authentication.infrastructure;

import org.junit.Test;

public class LibraryNumberValidatorShould {

    @Test(expected = InvalidLibraryNumber.class)
    public void throwInvalidLibraryNumberWhenCreatingALibraryNumberWithBadFormattedValue() {
        new LibraryNumberValidator("some bad library number");
    }
}