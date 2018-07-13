package com.twu.biblioteca;

import com.twu.biblioteca.books.application.BookShelvesInteractor;
import com.twu.biblioteca.books.infrastructure.AbstractBookPresenter;
import com.twu.biblioteca.books.infrastructure.BookInputController;
import com.twu.biblioteca.books.infrastructure.BookPresenter;

public class App {

    public static void main(String[] args) {
        AppPresenter appPresenter = new AppPresenter(System.out);
        appPresenter.sayWelcome();
        appPresenter.showMenu();
        AppInputController user = new AppInputController(System.in);

        BookShelvesInteractor bookShelves = new BookShelvesInteractor();
        bookShelves.preloadBooks();
        BookInputController bookInputController = new BookInputController(System.in);
        AbstractBookPresenter bookPresenter = new BookPresenter(bookShelves, System.out);
        while (!user.wantsToExit()) {
            switch (user.getSelectedOption()) {
                case 1:
                    bookPresenter.listBooks();
                    break;
                case 2:
                    appPresenter.askForBookCheckOut();
                    String bookTitle = bookInputController.readBookTitle();
                    bookPresenter.checkOut(bookTitle);
                    break;
                case 3:
                    appPresenter.askForBookReturn();
                    bookTitle = bookInputController.readBookTitle();
                    bookPresenter.returnBook(bookTitle);
                    break;
                default:
                    appPresenter.sayInvalidOption();
            }
            appPresenter.showMenu();
        }
        appPresenter.sayFarewell();
    }

}
