package com.twu.biblioteca.movies.infrastructure.persistance;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InMemoryMovieRepositoryShould {
    @Test
    public void returnAnEmptyCollectionOfMoviesWhenThereAreNoMoviesInIt() {
        InMemoryMovieRepository movieRepository = new InMemoryMovieRepository(Collections.emptyList());
        assertThat(movieRepository.list().isEmpty(), is(true));
    }

    @Test
    public void returnACollectionOfMoviesWhenThereAreMoviesInIt() {
        InMemoryMovieRepository movieRepository = new InMemoryMovieRepository(
                Arrays.asList(new String[][]{new String[]{"Some title", "some director", "1234", "3.4"}}));
        assertThat(movieRepository.list().isEmpty(), is(false));
    }

    @Test
    public void returnAnEmptyMovieWhenNotFindingIt() {
        InMemoryMovieRepository movieRepository = new InMemoryMovieRepository(Collections.emptyList());
        assertThat(movieRepository.find("some movie"), is(Optional.empty()));
    }

    @Test
    public void returnAMovieWhenFindingIt() {
        InMemoryMovieRepository movieRepository = new InMemoryMovieRepository(
                Arrays.asList(new String[][]{new String[]{"Some title", "some director", "1234", "3.4"}}));
        assertThat(movieRepository.find("Some title").get().getName(), is("Some title"));
    }
}