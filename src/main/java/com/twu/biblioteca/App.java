package com.twu.biblioteca;

import com.twu.biblioteca.infrastructure.WelcomePresenter;

public class App {

    public static void main(String[] args) {
        WelcomePresenter welcomePresenter = new WelcomePresenter(System.out);
        welcomePresenter.sayWelcome();
    }
}
