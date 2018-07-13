package com.twu.biblioteca.books.infrastructure;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookInputControllerShould {
    @Test
    public void readUserBookTitleInput() {
        System.setIn(new ByteArrayInputStream("some input".getBytes()));
        BookInputController bookInputController = new BookInputController(System.in);
        assertThat(bookInputController.readBookTitle(), is("some input"));
    }
}