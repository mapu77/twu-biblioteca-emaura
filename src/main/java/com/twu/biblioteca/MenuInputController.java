package com.twu.biblioteca;

import java.io.InputStream;
import java.util.Scanner;

class MenuInputController {
    private final Scanner scanner;
    private Integer selectedOption;

    MenuInputController(InputStream inputScanner) {
        this.scanner = new Scanner(inputScanner);
    }

    boolean wantsToExit() {
        if (scanner.hasNext()) {
            this.selectedOption = Integer.valueOf(scanner.nextLine());
            return this.selectedOption == 0;
        }
        return false;
    }

    Integer getSelectedOption() {
        return this.selectedOption;
    }

    String readBookTitle() {
        return scanner.nextLine();
    }
}
