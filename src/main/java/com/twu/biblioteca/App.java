package com.twu.biblioteca;

import com.twu.biblioteca.accounts.application.AccountInteractor;
import com.twu.biblioteca.accounts.infrastructure.AccountPresenter;
import com.twu.biblioteca.accounts.infrastructure.persistence.InMemoryAccountRepository;
import com.twu.biblioteca.authentication.application.Authenticator;
import com.twu.biblioteca.authentication.infrastructure.AuthenticatorInput;
import com.twu.biblioteca.authentication.infrastructure.AuthenticatorPresenter;
import com.twu.biblioteca.authentication.infrastructure.InvalidLibraryNumber;
import com.twu.biblioteca.authentication.infrastructure.persistence.InMemoryAccessRepository;
import com.twu.biblioteca.books.application.BookRepository;
import com.twu.biblioteca.books.application.BookShelves;
import com.twu.biblioteca.books.infrastructure.BookInput;
import com.twu.biblioteca.books.infrastructure.BookPresenter;
import com.twu.biblioteca.books.infrastructure.persistence.InMemoryBookRepository;
import com.twu.biblioteca.movies.application.MovieShelves;
import com.twu.biblioteca.movies.infrastructure.MovieInput;
import com.twu.biblioteca.movies.infrastructure.MoviePresenter;
import com.twu.biblioteca.movies.infrastructure.persistance.InMemoryMovieRepository;

import java.util.Arrays;

import static com.twu.biblioteca.AppOptions.INVALID_OPTION;

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
    private static InMemoryAccountRepository accountRepository = new InMemoryAccountRepository(Arrays.asList(
            new String[][]{new String[]{"Eduard Maura", "emaura@thoughtworks.com", "Fake Street 123", "+1 123 456 789", "123-1234"}}));

    public static void main(String[] args) {
        AppPresenter appPresenter = new AppPresenter(System.out);
        UserInput userInput = new UserInput(System.in);

        BookShelves bookShelves = new BookShelves(bookRepository);
        BookInput bookInput = new BookInput(System.in);
        BookPresenter bookPresenter = new BookPresenter(bookShelves, System.out);

        MovieInput movieInput = new MovieInput(System.in);
        MovieShelves movieShelves = new MovieShelves(movieRepository);
        MoviePresenter moviePresenter = new MoviePresenter(movieShelves, System.out);

        appPresenter.sayWelcome();

        AuthenticatorInput authenticatorInput = new AuthenticatorInput(System.in);
        Authenticator authenticator = new Authenticator(accessRepository);
        AuthenticatorPresenter authenticatorPresenter = new AuthenticatorPresenter(authenticator, System.out);

        AccountInteractor accountInteractor = new AccountInteractor(accountRepository);
        AccountPresenter accountPresenter = new AccountPresenter(accountInteractor, System.out);
        while (account == null) {
            try {
                appPresenter.askForLibraryNumber();
                String libraryNumber = authenticatorInput.entersLibraryNumber();
                appPresenter.askForPassword();
                String password = authenticatorInput.entersPassword();
                if (authenticatorPresenter.canAccess(libraryNumber, password)) account = libraryNumber;
            } catch (InvalidLibraryNumber e) {
                authenticatorPresenter.warnInvalidLibraryNumber();
            }
        }
        appPresenter.showMenu();
        while (!userInput.wantsToExit()) {
            Integer selectedOption = userInput.getSelectedOption();
            AppOptions appOption;
            try {
                appOption = AppOptions.values()[selectedOption];
            } catch (ArrayIndexOutOfBoundsException e) {
                appOption = INVALID_OPTION;
            }
            switch (appOption) {
                case LIST_BOOKS:
                    bookPresenter.listAvailableBooks();
                    break;
                case CHECKOUT_BOOK:
                    bookPresenter.askForBookCheckOut();
                    String bookTitle = bookInput.readBookTitle();
                    bookPresenter.checkOutBook(bookTitle);
                    break;
                case RETURN_BOOK:
                    bookPresenter.askForBookReturn();
                    bookTitle = bookInput.readBookTitle();
                    bookPresenter.returnBook(bookTitle);
                    break;
                case LIST_MOVIES:
                    moviePresenter.listAvailableMovies();
                    break;
                case CHECKOUT_MOVIE:
                    moviePresenter.askForMovieCheckOut();
                    String movieName = movieInput.readMovieName();
                    moviePresenter.checkOutMovie(movieName);
                    break;
                case ACCOUNT_DETAILS:
                    accountPresenter.showInfoOf(account);
                    break;
                default:
                    appPresenter.sayInvalidOption();
            }
            appPresenter.showMenu();
        }
        appPresenter.sayFarewell();
    }

}
