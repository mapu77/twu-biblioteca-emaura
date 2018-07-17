package com.twu.biblioteca.movies.application;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieShelvesInteractorShould {

    private MovieRepository movieRepoMock;

    @Before
    public void setUp() {
        movieRepoMock = mock(MovieRepository.class);
    }

    @Test(expected = MovieNotFound.class)
    public void throwMovieNotFoundWhenCheckingOutAMovieNotInTheShelves() {
        when(movieRepoMock.find("A non existing movie")).thenReturn(Optional.empty());
        MovieShelvesInteractor movieShelvesInteractor = new MovieShelvesInteractor(movieRepoMock);
        movieShelvesInteractor.checkOutMovie("A non existing movie");
    }
}
