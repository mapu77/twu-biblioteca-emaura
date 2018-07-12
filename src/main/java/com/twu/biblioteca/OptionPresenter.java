package com.twu.biblioteca;

import java.io.PrintStream;

public class OptionPresenter {
    private static final String WELCOME_MESSAGE = "Welcome to the Bangalore Public Library system";
    private static final String FANCY_LINE = "----------------------------------------------";
    private static final String END_OF_LINE = "\n";

    private final PrintStream output;

    public OptionPresenter(PrintStream output) {
        this.output = output;
    }

    public void sayWelcome() {
        output.println(FANCY_LINE);
        output.println(WELCOME_MESSAGE);
        output.println(FANCY_LINE);
        output.println(END_OF_LINE);
    }

    public void showMenu() {
        output.println("Choose an option:");
        output.println("\t1. List books");
        output.println("\t2. Checkout book");
        output.println("\t0. Exit");
    }
}
