package com.twu.biblioteca.movies.infrastructure.persistance;

import com.twu.biblioteca.movies.application.MovieRepository;
import com.twu.biblioteca.movies.core.Movie;
import com.twu.biblioteca.movies.core.MovieBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryMovieRepository implements MovieRepository {
    private final Map<String, Movie> movies;

    public InMemoryMovieRepository(Collection<String[]> movies) {
        this.movies = movies.stream().collect(Collectors.toMap(mapMovieKey(), mapMovie()));
    }

    @Override
    public Collection<Movie> list() {
        return this.movies.values();
    }

    @Override
    public Optional<Movie> find(String movieName) {
        return movies.values().stream().filter(movie -> movie.getName().equals(movieName)).findFirst();
    }

    private Function<String[], String> mapMovieKey() {
        return values -> values[0];
    }

    private Function<String[], Movie> mapMovie() {
        return values -> {
            MovieBuilder movieBuilder = new MovieBuilder(values[0])
                    .fromDirector(values[1])
                    .releasedInYear(Integer.valueOf(values[2]));
            if (values[3] != null) movieBuilder.ratedWithA(new Double(values[3]));
            return movieBuilder.build();
        };
    }
}
