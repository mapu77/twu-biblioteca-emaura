package com.twu.biblioteca.movies.application;

import com.twu.biblioteca.movies.core.Movie;
import com.twu.biblioteca.movies.core.MovieBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MovieShelvesInteractor implements MovieShelves {

    private final HashMap<String, Movie> movies;

    public MovieShelvesInteractor() {
        this.movies = new HashMap<>();
    }

    @Override
    public Collection<Movie> listMovies() {
        return new ArrayList<>(movies.values());
    }

    @Override
    public void checkOutMovie(String movieName) {
        if (movies.containsKey(movieName)) movies.get(movieName).checkOut();
        else throw new MovieNotFound();
    }

    public void preload() {
        Movie movie1 = new MovieBuilder("West Side Story").fromDirector("Jerome Robbins").releasedInYear(1961).ratedWithA(7.6).build();
        Movie movie2 = new MovieBuilder("Psycho").fromDirector("Alfred Hitchcock").releasedInYear(1960).ratedWithA(8.5).build();
        Movie movie3 = new MovieBuilder("Skyscraper").fromDirector("Rawson Marshall").releasedInYear(2018).build();
        this.movies.put(movie1.getName(), movie1);
        this.movies.put(movie2.getName(), movie2);
        this.movies.put(movie3.getName(), movie3);
    }
}
