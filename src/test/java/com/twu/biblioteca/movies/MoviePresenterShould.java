package com.twu.biblioteca.movies;

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
        when(movieShelvesMock.listMovies()).thenReturn(Collections.EMPTY_LIST);
        presenter.listMovies();
        verify(fakeOutput).println("There are no movies in the shelves");
    }

    @Test
    public void printHeadersFirstWhenThereAreMoviesToList() {
        when(movieShelvesMock.listMovies()).thenReturn(Collections.singletonList(
                new MovieBuilder("Test film 1").fromDirector("fake Director").releasedInYear(1990).ratedWithA(8.0).build()));
        presenter.listMovies();
        verify(fakeOutput).println("Name\t\t\t\tDirector\t\t\t\tRelease year\t\t\t\tRating" +
                "\n-------------------------------------------------------------------------------");
    }

    @Test
    public void printAsManyMoviesAsThereAreInTheShelves() {
        when(movieShelvesMock.listMovies()).thenReturn(Arrays.asList(
                new MovieBuilder("West Side Story").fromDirector("Jerome Robbins").releasedInYear(1961).ratedWithA(7.6).build(),
                new MovieBuilder("Psicosi").fromDirector("Alfred Hitchcock").releasedInYear(1960).ratedWithA(8.5).build()
        ));
        presenter.listMovies();
        InOrder inOrder = inOrder(fakeOutput);
        inOrder.verify(fakeOutput).println("West Side Story\t\t\t\tJerome Robbins\t\t\t\t1961\t\t\t\t7.6");
        inOrder.verify(fakeOutput).println("Psicosi\t\t\t\tAlfred Hitchcock\t\t\t\t1960\t\t\t\t8.5");
    }

    @Test
    public void printUnratedMoviesWithUnratedTag() {
        when(movieShelvesMock.listMovies()).thenReturn(Collections.singletonList(
                new MovieBuilder("Not rated movie").fromDirector("Director").releasedInYear(1961).build()
        ));
        presenter.listMovies();
        verify(fakeOutput).println("Not rated movie\t\t\t\tDirector\t\t\t\t1961\t\t4\t\tUnrated");
    }
}
