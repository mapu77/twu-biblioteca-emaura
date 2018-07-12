package com.twu.biblioteca;

import com.twu.biblioteca.books.application.BookShelvesInteractor;
import com.twu.biblioteca.books.infrastructure.AbstractBookPresenter;
import com.twu.biblioteca.books.infrastructure.BookPresenter;

public class App {

    public static void main(String[] args) {
        WelcomePresenter welcomePresenter = new WelcomePresenter(System.out);
        welcomePresenter.sayWelcome();
        welcomePresenter.showMenu();
        AbstractBookPresenter abstractBookPresenter = new BookPresenter(new BookShelvesInteractor(), System.out);
        abstractBookPresenter.listBooks();
    }
}
