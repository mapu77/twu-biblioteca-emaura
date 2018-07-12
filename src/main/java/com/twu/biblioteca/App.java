package com.twu.biblioteca;

import com.twu.biblioteca.books.application.BookShelvesInteractor;
import com.twu.biblioteca.books.infrastructure.AbstractBookPresenter;
import com.twu.biblioteca.books.infrastructure.BookPresenter;

public class App {

    public static void main(String[] args) {
        OptionPresenter optionPresenter = new OptionPresenter(System.out);
        optionPresenter.sayWelcome();
        optionPresenter.showMenu();
        MenuInputController user = new MenuInputController(System.in);
        BookShelvesInteractor bookShelves = new BookShelvesInteractor();
        bookShelves.preloadBooks();
        AbstractBookPresenter bookPresenter = new BookPresenter(bookShelves, System.out);
        while (!user.wantsToExit()) {
            switch (user.getSelectedOption()) {
                case 1:
                    bookPresenter.listBooks();
                    break;
                case 2:
                    optionPresenter.askForCheckOut();
                    String bookTitle = user.readBookTitle();
                    bookPresenter.checkOut(bookTitle);
                    break;
                case 3:
                    optionPresenter.askForReturn();
                    bookTitle = user.readBookTitle();
                    bookPresenter.returnBook(bookTitle);
                    break;
                default:
                    optionPresenter.sayInvalidOption();
            }
            optionPresenter.showMenu();
        }
        System.out.println("Thanks for using our system");
    }

}
