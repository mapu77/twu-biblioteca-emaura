package com.twu.biblioteca.movies;

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

    public void preload() {
        Movie movie1 = new MovieBuilder("West Side Story").fromDirector("Jerome Robbins").releasedInYear(1961).ratedWithA(7.6).build();
        Movie movie2 = new MovieBuilder("Psicosi").fromDirector("Alfred Hitchcock").releasedInYear(1960).ratedWithA(8.5).build();
        Movie movie3 = new MovieBuilder("Skyscraper").fromDirector("Rawson Marshall").releasedInYear(2018).build();
        this.movies.put(movie1.getName(), movie1);
        this.movies.put(movie2.getName(), movie2);
        this.movies.put(movie3.getName(), movie3);
    }
}
