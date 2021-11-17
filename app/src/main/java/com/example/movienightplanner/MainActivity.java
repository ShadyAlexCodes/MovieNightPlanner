package com.example.movienightplanner;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etMovieName, etMovieSearch, etSnackName;
    TextView tvMovieAdded, tvSearchOutput;
    Button btnCreateMovie, btnMovieSearch;

    MovieNightPlan movieNightPlan;

    private final List<String> movieName = new ArrayList<>(50);
    private final List<String> snackName = new ArrayList<>(50);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateViews();

    }


    public void onCreateMovieInteract(View view) {
        if (checkEmpty(etMovieName, "movie name") && checkEmpty(etSnackName, "snack name")) {
            if(!movieName.contains(etMovieName.getText().toString()) && !snackName.contains(etSnackName.getText().toString())) {
                movieName.add(etMovieName.getText().toString());
                snackName.add(etSnackName.getText().toString());

                etMovieName.setText(null);
                etSnackName.setText(null);

                tvMovieAdded.setText("You have added the movie: " + etMovieName.getText().toString() + "\nYou have added the snack: " + etSnackName.getText().toString());
            } else {
                tvMovieAdded.setText("You've already added that movie or snack!");
            }
        }
    }


    public void onSearchMovieInteract(View view) {
        
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
}