package com.twu.biblioteca;

import java.io.InputStream;
import java.util.Scanner;

class UserInput {
    private final Scanner scanner;
    private Integer selectedOption;

    UserInput(InputStream inputScanner) {
        this.scanner = new Scanner(inputScanner);
    }

    boolean wantsToExit() {
        if (scanner.hasNext()) {
            this.selectedOption = Integer.valueOf(scanner.nextLine());
            return this.selectedOption == AppOptions.EXIT.ordinal();
        }
        return false;
    }

    Integer getSelectedOption() {
        return this.selectedOption;
    }

}
