package com.twu.biblioteca;

import com.twu.biblioteca.authentication.application.Authenticator;
import com.twu.biblioteca.authentication.infrastructure.AuthenticatorInputController;
import com.twu.biblioteca.authentication.infrastructure.AuthenticatorPresenter;
import com.twu.biblioteca.authentication.infrastructure.InvalidLibraryNumber;
import com.twu.biblioteca.authentication.infrastructure.persistance.InMemoryAccessRepository;
import com.twu.biblioteca.books.application.BookRepository;
import com.twu.biblioteca.books.application.BookShelvesInteractor;
import com.twu.biblioteca.books.infrastructure.AbstractBookPresenter;
import com.twu.biblioteca.books.infrastructure.BookInputController;
import com.twu.biblioteca.books.infrastructure.BookPresenter;
import com.twu.biblioteca.books.infrastructure.persistance.InMemoryBookRepository;
import com.twu.biblioteca.movies.application.MovieShelvesInteractor;
import com.twu.biblioteca.movies.infrastructure.MovieInputController;
import com.twu.biblioteca.movies.infrastructure.MoviePresenter;
import com.twu.biblioteca.movies.infrastructure.persistance.InMemoryMovieRepository;

import java.util.Arrays;

public class App {

    private static String account;
    private static InMemoryAccessRepository accessRepository = new InMemoryAccessRepository(
            Arrays.asList(new String[][]{new String[]{"123-1234", "1234"}}));
    private static InMemoryMovieRepository movieRepository = new InMemoryMovieRepository(Arrays.asList(
            new String[]{"Psycho", "Alfred Hitchcock", "1960", "8.5"},
            new String[]{"West Side Story", "Jerome Robbins", "1961", null},
            new String[]{"Star Wars: A New Hope", "George Lucas", "1977", "8.6"}
    ));
    private static BookRepository bookRepository = new InMemoryBookRepository(Arrays.asList(
            new String[]{"Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1997"},
            new String[]{"Game of Thrones: A Game of Thrones", "George R. Martin", "1996"}));

    public static void main(String[] args) {
        AppPresenter appPresenter = new AppPresenter(System.out);
        AppInputController user = new AppInputController(System.in);

        BookShelvesInteractor bookShelves = new BookShelvesInteractor(bookRepository);
        BookInputController bookInputController = new BookInputController(System.in);
        AbstractBookPresenter bookPresenter = new BookPresenter(bookShelves, System.out);

        MovieInputController movieInputController = new MovieInputController(System.in);
        MovieShelvesInteractor movieShelves = new MovieShelvesInteractor(movieRepository);
        MoviePresenter moviePresenter = new MoviePresenter(movieShelves, System.out);

        appPresenter.sayWelcome();

        AuthenticatorInputController authenticatorInputController = new AuthenticatorInputController(System.in);
        Authenticator authenticator = new Authenticator(accessRepository);
        AuthenticatorPresenter authenticatorPresenter = new AuthenticatorPresenter(authenticator, System.out);
        while (account == null) {
            try {
                appPresenter.askForLibraryNumber();
                String libraryNumber = authenticatorInputController.entersLibraryNumber();
                appPresenter.askForPassword();
                String password = authenticatorInputController.entersPassword();
                if (authenticatorPresenter.canAccess(libraryNumber, password)) account = libraryNumber;
            } catch (InvalidLibraryNumber e) {
                authenticatorPresenter.warnInvalidLibraryNumber();
            }
        }
        appPresenter.showMenu();
        while (!user.wantsToExit()) {
            switch (user.getSelectedOption()) {
                case 1:
                    bookPresenter.listAvailableBooks();
                    break;
                case 2:
                    bookPresenter.askForBookCheckOut();
                    String bookTitle = bookInputController.readBookTitle();
                    bookPresenter.checkOutBook(bookTitle);
                    break;
                case 3:
                    bookPresenter.askForBookReturn();
                    bookTitle = bookInputController.readBookTitle();
                    bookPresenter.returnBook(bookTitle);
                    break;
                case 4:
                    moviePresenter.listAvailableMovies();
                    break;
                case 5:
                    moviePresenter.askForMovieCheckOut();
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
