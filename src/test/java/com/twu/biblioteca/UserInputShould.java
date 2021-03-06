package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserInputShould {

    private UserInput inputController;

    @Test
    public void returnTrueWhenUserWantsToExit() {
        System.setIn(new ByteArrayInputStream("0".getBytes()));
        inputController = new UserInput(System.in);
        assertThat(inputController.wantsToExit(), is(true));
    }

    @Test
    public void returnFalseWhenUserDoesNotWantToExit() {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        inputController = new UserInput(System.in);
        assertThat(inputController.wantsToExit(), is(false));
    }

    @Test
    public void keepSelectedOptionWhenAskingIfUserWantsToExit() {
        System.setIn(new ByteArrayInputStream("34".getBytes()));
        inputController = new UserInput(System.in);
        inputController.wantsToExit();
        assertThat(inputController.getSelectedOption(), is(not(nullValue())));
    }
}