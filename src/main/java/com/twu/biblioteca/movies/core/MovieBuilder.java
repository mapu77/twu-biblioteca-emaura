package com.twu.biblioteca.movies.core;

public class MovieBuilder {
    private final String name;
    private String directorName;
    private int year;
    private Double rate;
    private boolean checkedOut;

    public MovieBuilder(String movieName) {
        this.name = movieName;
    }

    public MovieBuilder fromDirector(String directorName) {
        this.directorName = directorName;
        return this;
    }

    public MovieBuilder releasedInYear(int yearOfRelease) {
        this.year = yearOfRelease;
        return this;
    }

    public MovieBuilder ratedWithA(double rate) {
        this.rate = rate;
        return this;
    }

    public Movie build() {
        Movie movie = new Movie(name);
        movie.setDirectorName(directorName);
        movie.setYear(year);
        movie.setRating(rate);
        movie.setCheckOut(checkedOut);
        return movie;
    }

    public MovieBuilder checkedOut() {
        this.checkedOut = true;
        return this;
    }
}
