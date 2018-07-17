package com.twu.biblioteca.books.infrastructure.persistance;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InMemoryBookRepositoryShould {

    @Test
    public void returnAnEmptyCollectionWhenThereAreNoBooksInIt() {
        InMemoryBookRepository bookRepo = new InMemoryBookRepository(Collections.emptyList());
        assertThat(bookRepo.listBooks().isEmpty(), is(true));
    }

    @Test
    public void haveSomeBooksWhenPreloaded() {
        InMemoryBookRepository bookRepo = new InMemoryBookRepository(Arrays.asList(new String[][]{
                new String[]{"Some title", "Some author", "234"}
        }));
        assertThat(bookRepo.listBooks().isEmpty(), is(false));
    }

    @Test
    public void returnEmptyBookWhenTryingToFindANonExistingBook() {
        InMemoryBookRepository bookRepo = new InMemoryBookRepository(Collections.emptyList());
        assertThat(bookRepo.find("A non existing book"), is(Optional.empty()));
    }

    @Test
    public void returnTheBookWhenTryingToFindAnExistingBook() {
        InMemoryBookRepository bookRepo = new InMemoryBookRepository(Arrays.asList(new String[][]{
                new String[]{"Some title", "Some author", "234"}
        }));
        assertThat(bookRepo.find("Some title").get().getTitle(), is("Some title"));

    }
}
