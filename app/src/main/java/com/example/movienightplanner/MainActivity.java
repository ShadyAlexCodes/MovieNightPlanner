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

    private final List<String> movieName = new ArrayList<>(50);
    private final List<String> snackName = new ArrayList<>(50);
    EditText etMovieName, etMovieSearch, etSnackName;
    TextView tvMovieAdded, tvSearchOutput;
    Button btnCreateMovie, btnMovieSearch;
    MovieNightPlan movieNightPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateViews();

    }


    public void onCreateMovieInteract(View view) {
        if (checkEmpty(etMovieName, "movie name")) {
            if (etMovieName.getText().toString().length() > 2) {
                if (!movieName.contains(etMovieName.getText().toString())) {
                    movieName.add(etMovieName.getText().toString());
                    tvMovieAdded.setText("You have added the movie: " + etMovieName.getText().toString());
                    etMovieName.setText(null);

                    System.out.println("MOVIES: " + Arrays.toString(movieName.toArray()));

                } else {
                    tvMovieAdded.setText("You've already added the movie " + etMovieName.getText().toString() + "!");
                }
            } else {
                etMovieName.setError("Please enter a longer movie name");
            }
        }
    }

    public void onCreateSnackInteract(View view) {
        if (checkEmpty(etSnackName, "snack name")) {
            if (etSnackName.getText().toString().length() > 2) {
                if (!snackName.contains(etSnackName.getText().toString())) {
                    snackName.add(etSnackName.getText().toString());
                    tvMovieAdded.setText("You have added the snack: " + etSnackName.getText().toString());
                    etSnackName.setText(null);

                    System.out.println("SNACKS: " + Arrays.toString(snackName.toArray()));

                } else {
                    tvMovieAdded.setText("You've already added the snack " + etSnackName.getText().toString() + "!");
                }
            } else {
                etSnackName.setError("Please enter a longer movie name");
            }
        }
    }


    public void onSearchMovieInteract(View view) {
        if (movieName.size() > 0 && snackName.size() > 0) {
            if (checkEmpty(etMovieSearch, "movie name")) {
                if (movieName.contains(etMovieSearch.getText().toString())) {
                    movieNightPlan = new MovieNightPlan(etMovieSearch.getText().toString(), snackName.get(generateRandomNumber(0, snackName.size() - 1)));
                    tvSearchOutput.setText(movieNightPlan.toString());
                } else {
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