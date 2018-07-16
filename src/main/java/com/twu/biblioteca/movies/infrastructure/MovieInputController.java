package com.twu.biblioteca.movies.infrastructure;

import java.io.InputStream;
import java.util.Scanner;

public class MovieInputController {
    private final Scanner scanner;

    public MovieInputController(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public String readMovieName() {
        return scanner.nextLine();
    }
}
