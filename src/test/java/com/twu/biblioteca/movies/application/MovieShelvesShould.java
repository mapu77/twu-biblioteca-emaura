package com.twu.biblioteca.movies.application;

import com.twu.biblioteca.movies.core.Movie;
import com.twu.biblioteca.movies.core.MovieBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieShelvesShould {

    private MovieRepository movieRepoMock;
    private MovieShelves movieShelves;

    @Before
    public void setUp() {
        movieRepoMock = mock(MovieRepository.class);
        movieShelves = new MovieShelves(movieRepoMock);
    }

    @Test
    public void onlyListAvailableMovies() {
        when(movieRepoMock.list()).thenReturn(Arrays.asList(
                new MovieBuilder("movie 1").build(),
                new MovieBuilder("movie 2").checkedOut().build()
        ));

        Collection<Movie> availableMovies = movieShelves.listAvailableMovies();
        assertThat(availableMovies.size(), is(1));
    }

    @Test(expected = MovieNotFound.class)
    public void throwMovieNotFoundWhenCheckingOutAMovieNotInTheShelves() {
        when(movieRepoMock.find("A non existing movie")).thenReturn(Optional.empty());
        movieShelves.checkOutMovie("A non existing movie");
    }
}
