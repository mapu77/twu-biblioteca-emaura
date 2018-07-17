package com.twu.biblioteca.movies.application;

import com.twu.biblioteca.movies.core.Movie;

import java.util.Collection;
import java.util.Optional;

public interface MovieRepository {
    Collection<Movie> list();

    Optional<Movie> find(String movieName);
}
