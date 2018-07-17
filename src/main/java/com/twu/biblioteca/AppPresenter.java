package com.twu.biblioteca;

import java.io.PrintStream;

class AppPresenter {
    private static final String WELCOME_MESSAGE = "Welcome to the Bangalore Public Library system";
    private static final String FANCY_LINE = "----------------------------------------------";

    private final PrintStream output;

    AppPresenter(PrintStream output) {
        this.output = output;
    }

    void sayFarewell() {
        output.println("Thanks for using our system");
    }

    void sayWelcome() {
        output.println(FANCY_LINE);
        output.println(WELCOME_MESSAGE);
        output.println(FANCY_LINE);
    }

    void showMenu() {
        output.println("Choose an option:");
        output.println("\t1. List books");
        output.println("\t2. Checkout a book");
        output.println("\t3. Return a book");
        output.println("\t4. List movies");
        output.println("\t5. Checkout a movie");
        output.println("\t0. Exit");
    }

    void sayInvalidOption() {
        output.println("Select a valid option!");
        output.println();
    }

    void askForLibraryNumber() {
        output.print("Library number: ");
    }

    void askForPassword() {
        output.print("Password: ");
    }
}
