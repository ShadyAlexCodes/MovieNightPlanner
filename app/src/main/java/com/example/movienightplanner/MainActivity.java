package com.example.movienightplanner;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // Create a private array list to store both the movie and snack names. Define the initial capacity as 50.
    private final List<String> movieName = new ArrayList<>(50);
    private final List<String> snackName = new ArrayList<>(50);

    // Define the EditText Variables
    EditText etMovieName, etMovieSearch, etSnackName;

    // Define the TextView Variables
    TextView tvMovieAdded, tvSearchOutput;

    // Define the Button Variables
    Button btnCreateMovie, btnMovieSearch;

    // Define the Movie Class
    MovieNightPlan movieNightPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Private function to generate all the items in the array
        generateViews();

    }


    public void onCreateMovieInteract(View view) {
        // Check if the movie input is empty. Pass the requirement
        if (checkEmpty(etMovieName, "movie name")) {
            // Check that the length is greater than 2
            if (etMovieName.getText().toString().length() > 2) {
                // Check if the array already contains the movie
                if (!movieName.contains(etMovieName.getText().toString())) {
                    // Add the movie to the array
                    movieName.add(etMovieName.getText().toString());
                    // Inform the user the movie was added
                    tvMovieAdded.setText("You have added the movie: " + etMovieName.getText().toString());
                    // Reset the movie's text input
                    etMovieName.setText(null);

                    // Print array for debugging purposes
                    System.out.println("MOVIES: " + Arrays.toString(movieName.toArray()));

                } else {
                    // Inform the user they already added the movie
                    tvMovieAdded.setText("You've already added the movie " + etMovieName.getText().toString() + "!");
                }
            } else {
                // Inform the user the movies name needs to be longer
                etMovieName.setError("Please enter a longer movie name");
            }
        }
    }

    public void onCreateSnackInteract(View view) {
        // Check the snack name is empty. Pass requirement
        if (checkEmpty(etSnackName, "snack name")) {
            // Check the length of the snack name
            if (etSnackName.getText().toString().length() > 2) {
                // check if the array already contains input
                if (!snackName.contains(etSnackName.getText().toString())) {
                    // Add Snack
                    snackName.add(etSnackName.getText().toString());
                    // Output to the user
                    tvMovieAdded.setText("You have added the snack: " + etSnackName.getText().toString());
                    // Set the text to null
                    etSnackName.setText(null);

                    System.out.println("SNACKS: " + Arrays.toString(snackName.toArray()));

                } else {
                    // Inform the user they've already added the snack
                    tvMovieAdded.setText("You've already added the snack " + etSnackName.getText().toString() + "!");
                }
            } else {
                // Tell them to enter a longer movie name
                etSnackName.setError("Please enter a longer movie name");
            }
        }
    }


    public void onSearchMovieInteract(View view) {
        // check that the size is greater than 0
        if (movieName.size() > 0 && snackName.size() > 0) {
            // check if the movie search is empty.. pass requirement
            if (checkEmpty(etMovieSearch, "movie name")) {
                // Check if the movie's search is in the movie name
                if (movieName.contains(etMovieSearch.getText().toString())) {
                    // create a new instance
                    movieNightPlan = new MovieNightPlan(etMovieSearch.getText().toString(), snackName.get(generateRandomNumber(0, snackName.size() - 1)));
                    // Set the output
                    tvSearchOutput.setText(movieNightPlan.toString());
                } else {
                    // Inform the user they no big brain
                    tvSearchOutput.setText("The movie \"" + etMovieSearch.getText().toString() + "\" was not found!");
                }
            }
        } else {
            tvSearchOutput.setText("There was an error searching for a movie and snack!");
        }
    }

    private boolean checkEmpty(EditText input, String requirement) {
        // Check if the inputted action is null.
        if (TextUtils.isEmpty(input.getText().toString())) {
            // Define the error
            input.setError("Please enter a " + requirement);
            return false;
        }
        return true;
    }

    // Generate the views
    private void generateViews() {

        // Grab the Edit Text
        etMovieName = findViewById(R.id.etMovieName);
        etMovieSearch = findViewById(R.id.etMovieSearch);
        etSnackName = findViewById(R.id.etSnackName);

        // Grab the text view
        tvMovieAdded = findViewById(R.id.tvMovieAdded);
        tvSearchOutput = findViewById(R.id.tvSearchOutput);

        // Grab the buttons
        btnCreateMovie = findViewById(R.id.btnCreateMovie);
        btnMovieSearch = findViewById(R.id.btnMovieSearch);

    }

    private int generateRandomNumber(int min, int max) {
        // Generate a random number in the inclusivity range of the min and max
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}