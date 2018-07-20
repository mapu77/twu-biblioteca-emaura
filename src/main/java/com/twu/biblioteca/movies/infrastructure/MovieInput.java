package com.twu.biblioteca.movies.infrastructure;

import java.io.InputStream;
import java.util.Scanner;

public class MovieInput {
    private final Scanner scanner;

    public MovieInput(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public String readMovieName() {
        return scanner.nextLine();
    }
}
