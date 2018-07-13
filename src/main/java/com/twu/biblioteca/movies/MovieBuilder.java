package com.twu.biblioteca.movies;

class MovieBuilder {
    private final String name;
    private String directorName;
    private int year;
    private Double rate;

    MovieBuilder(String movieName) {
        this.name = movieName;
    }

    MovieBuilder fromDirector(String directorName) {
        this.directorName = directorName;
        return this;
    }

    MovieBuilder releasedInYear(int yearOfRelease) {
        this.year = yearOfRelease;
        return this;
    }

    MovieBuilder ratedWithA(double rate) {
        this.rate = rate;
        return this;
    }

    Movie build() {
        Movie movie = new Movie(name);
        movie.setDirectorName(directorName);
        movie.setYear(year);
        movie.setRating(rate);
        return movie;
    }
}
