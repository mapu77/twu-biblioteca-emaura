package com.twu.biblioteca.movies.infrastructure;

import com.twu.biblioteca.movies.application.MovieNotFound;
import com.twu.biblioteca.movies.application.MovieShelves;
import com.twu.biblioteca.movies.core.MovieBuilder;
import com.twu.biblioteca.movies.core.MovieNotAvailable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class MoviePresenterShould {
    private MovieShelves movieShelvesMock;
    private PrintStream fakeOutput;
    private MoviePresenter presenter;

    @Before
    public void setUp() {
        movieShelvesMock = mock(MovieShelves.class);
        fakeOutput = mock(PrintStream.class);
        presenter = new MoviePresenter(movieShelvesMock, fakeOutput);
    }

    @Test
    public void sayThereAreNoFilmsWhenThereAreNoFilmsInTheShelves() {
        when(movieShelvesMock.listAvailableMovies()).thenReturn(Collections.emptyList());
        presenter.listAvailableMovies();
        verify(fakeOutput).println("There are no movies in the shelves");
    }

    @Test
    public void printHeadersFirstWhenThereAreMoviesToList() {
        when(movieShelvesMock.listAvailableMovies()).thenReturn(Collections.singletonList(
                new MovieBuilder("Test film 1").fromDirector("fake Director").releasedInYear(1990).ratedWithA(8.0).build()));
        presenter.listAvailableMovies();
        verify(fakeOutput).println("Name\t\t\t\tDirector\t\t\t\tRelease year\t\t\t\tRating" +
                "\n-------------------------------------------------------------------------------");
    }

    @Test
    public void printAsManyMoviesAsThereAreInTheShelves() {
        when(movieShelvesMock.listAvailableMovies()).thenReturn(Arrays.asList(
                new MovieBuilder("West Side Story").fromDirector("Jerome Robbins").releasedInYear(1961).ratedWithA(7.6).build(),
                new MovieBuilder("Psicosi").fromDirector("Alfred Hitchcock").releasedInYear(1960).ratedWithA(8.5).build()
        ));
        presenter.listAvailableMovies();
        InOrder inOrder = inOrder(fakeOutput);
        inOrder.verify(fakeOutput).println("West Side Story\t\t\t\tJerome Robbins\t\t\t\t1961\t\t\t\t7.6");
        inOrder.verify(fakeOutput).println("Psicosi\t\t\t\tAlfred Hitchcock\t\t\t\t1960\t\t\t\t8.5");
    }

    @Test
    public void printUnratedMoviesWithUnratedTag() {
        when(movieShelvesMock.listAvailableMovies()).thenReturn(Collections.singletonList(
                new MovieBuilder("Not rated movie").fromDirector("Director").releasedInYear(1961).build()
        ));
        presenter.listAvailableMovies();
        verify(fakeOutput).println("Not rated movie\t\t\t\tDirector\t\t\t\t1961\t\t\t\tUnrated");
    }

    @Test
    public void sayThankYouWhenCheckingOutAMovie() {
        when(movieShelvesMock.listAvailableMovies()).thenReturn(Collections.singletonList(
                new MovieBuilder("West Side Story").fromDirector("Jerome Robbins").releasedInYear(1961).ratedWithA(7.6).build()));
        presenter.checkOutMovie("Harry Potter and the Philosopher's Stone");
        verify(fakeOutput).println("Thank you! Enjoy the movie");
    }

    @Test
    public void sayTheMovieIsNotAvailableWhenCheckingOutANonExistingMovie() {
        doThrow(MovieNotFound.class).when(movieShelvesMock).checkOutMovie(anyString());
        presenter.checkOutMovie("The Cube");
        verify(fakeOutput).println("That movie is not available");
    }

    @Test
    public void sayTheMovieIsNotAvailableWhenCheckingOutACheckOutMovie() {
        doThrow(MovieNotAvailable.class).when(movieShelvesMock).checkOutMovie(anyString());
        presenter.checkOutMovie("The Cube");
        verify(fakeOutput).println("That movie is not available");
    }

    @Test
    public void askForMovieCheckOut() {
        presenter.askForMovieCheckOut();
        verify(fakeOutput).println("What movie do you want to checkout?");
    }
}
