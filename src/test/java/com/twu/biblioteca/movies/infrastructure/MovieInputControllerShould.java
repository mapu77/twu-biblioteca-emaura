package com.twu.biblioteca.movies.infrastructure;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MovieInputControllerShould {

    @Test
    public void readMovieNameFromUserInput() {
        System.setIn(new ByteArrayInputStream("some input".getBytes()));
        MovieInputController inputController = new MovieInputController(System.in);
        assertThat(inputController.readMovieName(), is("some input"));
    }
}