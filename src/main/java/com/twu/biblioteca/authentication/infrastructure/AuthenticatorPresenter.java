package com.twu.biblioteca.authentication.infrastructure;

import com.twu.biblioteca.authentication.application.Authenticator;

import java.io.PrintStream;

public class AuthenticatorPresenter {
    private final Authenticator authenticator;
    private final PrintStream out;

    public AuthenticatorPresenter(Authenticator authenticator, PrintStream out) {
        this.authenticator = authenticator;
        this.out = out;
    }

    public boolean canAccess(String libraryNumber, String password) {
        if (this.authenticator.allows(libraryNumber, password)) {
            out.println("Successful login");
            return true;
        } else {
            out.println("Sorry, incorrect library number or password");
            return false;
        }
    }

    public void warnInvalidLibraryNumber() {
        out.println("Invalid library number (remember xxx-xxxx)");
    }
}
