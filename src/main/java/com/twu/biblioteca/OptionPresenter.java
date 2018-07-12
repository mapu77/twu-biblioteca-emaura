package com.twu.biblioteca;

import java.io.PrintStream;

class OptionPresenter {
    private static final String WELCOME_MESSAGE = "Welcome to the Bangalore Public Library system";
    private static final String FANCY_LINE = "----------------------------------------------";
    private static final String END_OF_LINE = "\n";

    private final PrintStream output;

    OptionPresenter(PrintStream output) {
        this.output = output;
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
        output.println("\t0. Exit");
    }

    void askForCheckOut() {
        output.println("What book do you want to checkout?");
    }

    void askForReturn() {
        output.println("What book do you want to return?");
    }

    void sayInvalidOption() {
        output.println("Select a valid option!");
        System.out.println();
    }
}
