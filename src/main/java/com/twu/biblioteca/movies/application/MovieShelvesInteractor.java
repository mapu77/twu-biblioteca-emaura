package com.twu.biblioteca.movies.application;

import com.twu.biblioteca.movies.core.Movie;
import com.twu.biblioteca.movies.infrastructure.MovieShelves;

import java.util.Collection;
import java.util.Optional;

public class MovieShelvesInteractor implements MovieShelves {

    private final MovieRepository movieRepository;

    public MovieShelvesInteractor(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Collection<Movie> listMovies() {
        return movieRepository.list();
    }

    @Override
    public void checkOutMovie(String movieName) {
        Optional<Movie> movie = movieRepository.find(movieName);
        if (movie.isPresent()) movie.get().checkOut();
        else throw new MovieNotFound();
    }

}
