package com.twu.biblioteca.authentication.application;

import com.twu.biblioteca.helpers.TestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticatorShould {

    private Authenticator authenticator;
    private AccessRepository accessRepositoryMock;

    @Before
    public void setUp() {
        accessRepositoryMock = mock(AccessRepository.class);
        authenticator = new Authenticator(accessRepositoryMock);
    }

    @Test
    public void allowAuthenticatedUsers() {
        when(accessRepositoryMock.find(TestHelper.createValidLibraryNumber())).thenReturn(Optional.of("som string"));
        when(accessRepositoryMock.matches(anyString(), anyString())).thenReturn(true);
        boolean isAllowed = authenticator.allows(TestHelper.createValidLibraryNumber(), "any string");
        assertThat(isAllowed, is(true));
    }

    @Test
    public void notAllowInvalidLibraryNumbers() {
        when(accessRepositoryMock.find(TestHelper.createValidLibraryNumber())).thenReturn(Optional.empty());
        boolean isAllowed = authenticator.allows(TestHelper.createValidLibraryNumber(), "any string");
        assertThat(isAllowed, is(false));

    }
}