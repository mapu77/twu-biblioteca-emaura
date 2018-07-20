package com.twu.biblioteca.books.infrastructure;

import java.io.InputStream;
import java.util.Scanner;

public class BookInput {
    private final Scanner scanner;

    public BookInput(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String readBookTitle() {
        return scanner.nextLine();
    }
}
