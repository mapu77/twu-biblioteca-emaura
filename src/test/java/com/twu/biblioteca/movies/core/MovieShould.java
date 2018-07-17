package com.twu.biblioteca.movies.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MovieShould {
    @Test
    public void beCreatedWithName() {
        Movie movie = new Movie("name");
        assertThat(movie.getName(), is("name"));
    }

    @Test
    public void beAvailableWhenCreated() {
        Movie movie = new Movie("name");
        assertThat(movie.isAvailable(), is(true));
    }

    @Test(expected = InvalidRatingValue.class)
    public void haveRatingBelow10() {
        Movie movie = new Movie("name");
        movie.setRating(11.0);
    }

    @Test(expected = InvalidRatingValue.class)
    public void haveRatingAbove0() {
        Movie movie = new Movie("name");
        movie.setRating(-1.0);
    }

    @Test
    public void haveRatingBetween0And10() {
        Movie movie = new Movie("name");
        movie.setRating(5.0);
        assertThat(movie.getRating(), is(5.0));
    }

    @Test
    public void beAbleToBeUnrated() {
        Movie movie = new Movie("name");
        movie.setRating(null);
        assertThat(movie.getRating(), is(nullValue()));
    }

    @Test
    public void beNotBeAvailableWhenCheckedOut() {
        Movie movie = new Movie("some movie");
        movie.checkOut();
        assertThat(movie.isAvailable(), is(false));
    }

    @Test(expected = MovieNotAvailable.class)
    public void throwMovieNotAvailableWhenCheckingOutAMovieThatIsCheckedOut() {
        Movie movie = new Movie("checked out movie");
        movie.checkOut();
        movie.checkOut();
    }
}
