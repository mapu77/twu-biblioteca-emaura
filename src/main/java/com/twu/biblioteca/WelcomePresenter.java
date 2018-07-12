package com.twu.biblioteca;

import java.io.PrintStream;

public class WelcomePresenter {
    private static final String WELCOME_MESSAGE = "Welcome to the Bangalore Public Library system";
    private static final String FANCY_LINE = "----------------------------------------------";
    private static final String END_OF_LINE = "\n";

    private final PrintStream output;

    public WelcomePresenter(PrintStream output) {
        this.output = output;
    }

    public void sayWelcome() {
        output.println(FANCY_LINE);
        output.println(WELCOME_MESSAGE);
        output.println(FANCY_LINE);
        output.println(END_OF_LINE);
    }
}
