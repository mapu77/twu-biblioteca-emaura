package com.twu.biblioteca.books.infrastructure;

import java.io.InputStream;
import java.util.Scanner;

public class BookInputController {
    private final Scanner scanner;

    public BookInputController(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String readBookTitle() {
        return scanner.nextLine();
    }
}
