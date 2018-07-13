package com.twu.biblioteca.movies;

import com.twu.biblioteca.movies.application.MovieShelves;
import com.twu.biblioteca.movies.application.MovieShelvesInteractor;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class MovieShelvesInteractorShould {

    @Test
    public void returnAnEmptyCollectionOfMoviesWhenThereAreNoMoviesInIt() {
        MovieShelves movieShelvesInteractor = new MovieShelvesInteractor();
        MatcherAssert.assertThat(movieShelvesInteractor.listMovies().isEmpty(), is(true));
    }

    @Test
    public void haveSomeMoviesPreloaded() {
        MovieShelvesInteractor movieShelvesInteractor = new MovieShelvesInteractor();
        movieShelvesInteractor.preload();
        MatcherAssert.assertThat(movieShelvesInteractor.listMovies().isEmpty(), is(false));

    }
}
