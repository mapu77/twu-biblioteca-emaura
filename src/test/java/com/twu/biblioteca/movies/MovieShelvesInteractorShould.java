package com.twu.biblioteca.movies;

import com.twu.biblioteca.movies.application.MovieNotFound;
import com.twu.biblioteca.movies.application.MovieShelvesInteractor;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class MovieShelvesInteractorShould {

    private MovieShelvesInteractor movieShelvesInteractor;

    @Test
    public void returnAnEmptyCollectionOfMoviesWhenThereAreNoMoviesInIt() {
        movieShelvesInteractor = new MovieShelvesInteractor();
        MatcherAssert.assertThat(movieShelvesInteractor.listMovies().isEmpty(), is(true));
    }

    @Test
    public void haveSomeMoviesPreloaded() {
        movieShelvesInteractor = new MovieShelvesInteractor();
        movieShelvesInteractor.preload();
        MatcherAssert.assertThat(movieShelvesInteractor.listMovies().isEmpty(), is(false));
    }

    @Test(expected = MovieNotFound.class)
    public void throwMovieNotFoundWhenCheckingOutAMovieNotInTheShelves() {
        movieShelvesInteractor = new MovieShelvesInteractor();
        movieShelvesInteractor.checkOutMovie("A non existing movie");
    }
}
