package com.twu.biblioteca.movies.application;

import com.twu.biblioteca.movies.core.Movie;

import java.util.Collection;

public interface MovieShelves {
    Collection<Movie> listMovies();

    void checkOutMovie(String movieName) throws MovieNotFound;
}
