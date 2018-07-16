package com.twu.biblioteca.movies.core;

public class Movie {
    private static final double MINIMUM_RATING = 1.0;
    private static final double MAXIMUM_RATING = 10.0;
    private final String name;
    private String directorName;
    private Integer year;
    private Double rating;
    private boolean checkOut;

    public Movie(String name) {
        this.name = name;
        this.checkOut = false;
    }

    public String getName() {
        return this.name;
    }

    public String getDirectorName() {
        return directorName;
    }

    void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Integer getYear() {
        return year;
    }

    void setYear(Integer year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        verifyValidRating(rating);
        this.rating = rating;
    }

    private void verifyValidRating(Double rating) {
        if (isBelowTheThreshold(rating) || isAboveTheThreshold(rating)) throw new InvalidRatingValue();
    }

    private boolean isAboveTheThreshold(Double rating) {
        if (rating != null) return new Double(MAXIMUM_RATING).compareTo(rating) < 0;
        return false;
    }

    private boolean isBelowTheThreshold(Double rating) {
        if (rating != null) return new Double(MINIMUM_RATING).compareTo(rating) > 0;
        return false;
    }

    void setCheckOut(boolean checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isAvailable() {
        return !this.checkOut;
    }

    public void checkOut() {
        if (this.isAvailable()) setCheckOut(true);
        else throw new MovieNotAvailable();
    }
}
