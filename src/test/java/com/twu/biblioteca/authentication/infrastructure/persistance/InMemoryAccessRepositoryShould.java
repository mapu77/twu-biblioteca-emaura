package com.twu.biblioteca.authentication.infrastructure.persistance;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class InMemoryAccessRepositoryShould {

    @Test
    public void returnEmptyOptionalWhenNotFindingALibraryNumber() {
        InMemoryAccessRepository accessRepository = new InMemoryAccessRepository(Collections.emptyList());
        Optional<String> expectedLibraryNumber = accessRepository.find("not in repo number");
        assertThat(expectedLibraryNumber, is(Optional.empty()));
    }

    @Test
    public void returnOptionalWithTheLibraryNumberWhenFindingIt() {
        InMemoryAccessRepository accessRepository = new InMemoryAccessRepository(Arrays.asList(new String[][]{new String[]{"inRepoNumber", "not important password"}}));
        Optional<String> expectedLibraryNumber = accessRepository.find("inRepoNumber");
        assertThat(expectedLibraryNumber, is(Optional.of("inRepoNumber")));
    }

    @Test
    public void returnTrueWhenLibraryNumberAndPasswordMatch() {
        InMemoryAccessRepository accessRepository = new InMemoryAccessRepository(Arrays.asList(new String[][]{new String[]{"inRepoNumber", "somePassword"}}));
        assertThat(accessRepository.matches("inRepoNumber", "somePassword"), is(true));
    }

    @Test
    public void returnFalseWhenLibraryNumberAndPasswordDoNotMatch() {
        InMemoryAccessRepository accessRepository = new InMemoryAccessRepository(Arrays.asList(new String[][]{new String[]{"inRepoNumber", "somePassword"}}));
        assertThat(accessRepository.matches("inRepoNumber", "not this password"), is(false));
    }
}