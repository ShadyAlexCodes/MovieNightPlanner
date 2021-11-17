package com.example.movienightplanner;

public class MovieNightPlan {

    private String movie;
    private String snack;

    public MovieNightPlan(String movieName, String snackName) {
        this.movie = movieName;
        this.snack = snackName;
    }

    public String toString() {
        return "Your movie is " + movie + " and your snack is " + snack;
    }
}
