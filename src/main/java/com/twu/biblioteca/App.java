package com.twu.biblioteca;

import com.twu.biblioteca.books.application.BookShelvesInteractor;
import com.twu.biblioteca.books.infrastructure.AbstractBookPresenter;
import com.twu.biblioteca.books.infrastructure.BookInputController;
import com.twu.biblioteca.books.infrastructure.BookPresenter;
import com.twu.biblioteca.movies.application.MovieShelvesInteractor;
import com.twu.biblioteca.movies.infrastructure.MovieInputController;
import com.twu.biblioteca.movies.infrastructure.MoviePresenter;

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
        MovieShelvesInteractor movieShelves = new MovieShelvesInteractor();
        movieShelves.preload();
        MoviePresenter moviePresenter = new MoviePresenter(movieShelves, System.out);
        while (!user.wantsToExit()) {
            switch (user.getSelectedOption()) {
                case 1:
                    bookPresenter.listBooks();
                    break;
                case 2:
                    appPresenter.askForBookCheckOut();
                    String bookTitle = bookInputController.readBookTitle();
                    bookPresenter.checkOutBook(bookTitle);
                    break;
                case 3:
                    appPresenter.askForBookReturn();
                    bookTitle = bookInputController.readBookTitle();
                    bookPresenter.returnBook(bookTitle);
                    break;
                case 4:
                    moviePresenter.listMovies();
                    break;
                case 5:
                    appPresenter.askForMovieCheckOut();
                    MovieInputController movieInputController = new MovieInputController(System.in);
                    String movieName = movieInputController.readMovieName();
                    moviePresenter.checkOutMovie(movieName);
                    break;
                default:
                    appPresenter.sayInvalidOption();
            }
            appPresenter.showMenu();
        }
        appPresenter.sayFarewell();
    }

}
