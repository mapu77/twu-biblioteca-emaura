package com.twu.biblioteca.movies.infrastructure;

import com.twu.biblioteca.movies.application.MovieNotFound;
import com.twu.biblioteca.movies.application.MovieShelves;
import com.twu.biblioteca.movies.core.Movie;
import com.twu.biblioteca.movies.core.MovieNotAvailable;

import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MoviePresenter {
    private static final String SPACE_BETWEEN_COLUMNS = "\t\t\t\t";
    private static final String FANCY_LINE = "-------------------------------------------------------------------------------";
    private static final String HEADERS = "Name" + SPACE_BETWEEN_COLUMNS + "Director" + SPACE_BETWEEN_COLUMNS + "Release year" + SPACE_BETWEEN_COLUMNS + "Rating\n" + FANCY_LINE;
    private static final String UNRATED_TAG = "Unrated";
    private static final String THANK_YOU_FOR_CHECKING_OUT = "Thank you! Enjoy the movie";
    private static final String MOVIE_NOT_AVAILABLE = "That movie is not available";

    private final MovieShelves movieShelves;
    private final PrintStream output;

    public MoviePresenter(MovieShelves movieShelves, PrintStream output) {
        this.movieShelves = movieShelves;
        this.output = output;
    }

    public void listMovies() {
        Collection<Movie> movies = movieShelves.listMovies();
        List<Movie> filteredMovies = movies.stream().filter(Movie::isAvailable).collect(Collectors.toList());
        if (filteredMovies.isEmpty()) output.println("There are no movies in the shelves");
        else {
            output.println(HEADERS);
            for (Movie movie : filteredMovies) {
                String rating = movie.getRating() == null ? UNRATED_TAG : movie.getRating().toString();
                output.println(movie.getName() + SPACE_BETWEEN_COLUMNS + movie.getDirectorName() +
                        SPACE_BETWEEN_COLUMNS + movie.getYear() + SPACE_BETWEEN_COLUMNS + rating);
            }
        }
    }

    public void checkOutMovie(String movieName) {
        try {
            movieShelves.checkOutMovie(movieName);
            output.println(THANK_YOU_FOR_CHECKING_OUT);
        } catch (MovieNotFound | MovieNotAvailable e) {
            output.println(MOVIE_NOT_AVAILABLE);
        }
    }
}
