package com.twu.biblioteca.authentication.infrastructure;

import com.twu.biblioteca.helpers.TestHelper;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuthenticatorInputShould {

    private AuthenticatorInput inputController;

    @Test
    public void readLibraryNumberFromUser() {
        System.setIn(new ByteArrayInputStream(TestHelper.createValidLibraryNumber().getBytes()));
        inputController = new AuthenticatorInput(System.in);
        assertThat(inputController.entersLibraryNumber(), is("123-1234"));
    }

    @Test
    public void readPasswordFromUser() {
        System.setIn(new ByteArrayInputStream("87q5".getBytes()));
        inputController = new AuthenticatorInput(System.in);
        assertThat(inputController.entersPassword(), is("87q5"));
    }

}