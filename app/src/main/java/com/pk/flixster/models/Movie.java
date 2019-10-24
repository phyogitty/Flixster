package com.pk.flixster.models;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String title;
    String backdropPath;
    String posterPath;
    String overview;
    double voteAvg;



    public Movie() {}
    public Movie(JSONObject jsonObject, int index) throws JSONException {
        title = jsonObject.getString("title");
        backdropPath= jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        overview = jsonObject.getString("overview");
        voteAvg = jsonObject.getDouble("vote_average");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i), i));
        }

        return movies;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }


    public String getOverview() {
        return overview;
    }


    public double getVoteAvg() { return voteAvg; }
}
