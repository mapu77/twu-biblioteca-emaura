package com.twu.biblioteca.authentication.infrastructure;

import java.io.InputStream;
import java.util.Scanner;

public class AuthenticatorInputController {

    private final Scanner scanner;

    public AuthenticatorInputController(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public String entersPassword() {
        return this.scanner.nextLine();
    }

    public String entersLibraryNumber() {
        return new LibraryNumberValidator(this.scanner.nextLine()).getValue();
    }
}