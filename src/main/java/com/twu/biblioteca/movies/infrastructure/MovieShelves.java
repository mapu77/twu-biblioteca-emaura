package com.twu.biblioteca.movies.infrastructure;

import com.twu.biblioteca.movies.application.MovieNotFound;
import com.twu.biblioteca.movies.core.Movie;

import java.util.Collection;

public interface MovieShelves {
    Collection<Movie> listMovies();

    void checkOutMovie(String movieName) throws MovieNotFound;
}
