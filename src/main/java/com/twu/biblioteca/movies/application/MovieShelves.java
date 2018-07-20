package com.twu.biblioteca.movies.application;

import com.twu.biblioteca.movies.core.Movie;

import java.util.Collection;
import java.util.Optional;

public class MovieShelves {

    private final MovieRepository movieRepository;

    public MovieShelves(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> listMovies() {
        return movieRepository.list();
    }

    public void checkOutMovie(String movieName) {
        Optional<Movie> movie = movieRepository.find(movieName);
        if (movie.isPresent()) movie.get().checkOut();
        else throw new MovieNotFound();
    }

}
