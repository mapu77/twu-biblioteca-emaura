package com.twu.biblioteca.movies;

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
}
