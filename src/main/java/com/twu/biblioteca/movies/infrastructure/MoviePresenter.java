package com.twu.biblioteca.movies.infrastructure;

import com.twu.biblioteca.movies.application.MovieNotFound;
import com.twu.biblioteca.movies.application.MovieShelves;
import com.twu.biblioteca.movies.core.Movie;
import com.twu.biblioteca.movies.core.MovieNotAvailable;

import java.io.PrintStream;
import java.util.Collection;

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

    public void askForMovieCheckOut() {
        output.println("What movie do you want to checkout?");
    }

    public void listAvailableMovies() {
        Collection<Movie> availableMovies = movieShelves.listAvailableMovies();
        if (availableMovies.isEmpty()) output.println("There are no movies in the shelves");
        else {
            output.println(HEADERS);
            for (Movie movie : availableMovies) {
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
