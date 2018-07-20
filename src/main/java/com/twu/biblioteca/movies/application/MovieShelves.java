package com.twu.biblioteca.movies.application;

import com.twu.biblioteca.movies.core.Movie;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieShelves {

    private final MovieRepository movieRepository;

    public MovieShelves(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> listAvailableMovies() {
        Collection<Movie> movies = movieRepository.list();
        return movies.stream().filter(Movie::isAvailable).collect(Collectors.toList());
    }

    public void checkOutMovie(String movieName) {
        Optional<Movie> movie = movieRepository.find(movieName);
        if (movie.isPresent()) movie.get().checkOut();
        else throw new MovieNotFound();
    }

}
