package com.twu.biblioteca.books.infrastructure;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BookInputShould {
    @Test
    public void readUserBookTitleInput() {
        System.setIn(new ByteArrayInputStream("some input".getBytes()));
        BookInput bookInput = new BookInput(System.in);
        assertThat(bookInput.readBookTitle(), is("some input"));
    }
}