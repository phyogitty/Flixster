package com.pk.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pk.flixster.models.Movie;

import org.parceler.Parcels;

public class MovieDetailActivity extends AppCompatActivity {


    TextView tvTitleM;
    TextView tvOverView1;
    RatingBar rbVoteAverage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvTitleM = findViewById(R.id.tvTitleM);
        tvOverView1 = findViewById(R.id.overview);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);

        // unwrap the movie passed in via intent, using its simple name as a key
        Movie movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailActivity", String.format("Showing details for '%s'", movie.getTitle()));

        tvTitleM.setText(movie.getTitle());
        tvOverView1.setText(movie.getOverview());

        float voteAverage = (float) movie.getVoteAvg();
        rbVoteAverage.setRating(voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
    }
}
