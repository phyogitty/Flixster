package com.pk.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pk.flixster.R;
import com.pk.flixster.models.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        Movie movie = movies.get(position);
        holder.bind(movie, position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverView;
        ImageView ivPoster;
        RelativeLayout itemLayout;

        Map<String, Integer> itsColor = new HashMap<>();


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverView = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            itemLayout = itemView.findViewById(R.id.itemLayout);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Movie movie, int pos) {
            tvTitle.setText(movie.getTitle());

            // cut the long text
            if (movie.getOverview().length() > 270) {
                tvOverView.setText(movie.getOverview().substring(0, 270) + "...");
            } else {
                tvOverView.setText(movie.getOverview());
            }

            // assign color for each unique movie ; 1 = Light gray, 0 = White (default)
            String title = movie.getTitle();
            if (!itsColor.containsKey(title)) {
               if (pos % 2 == 0) {
                   itsColor.put(title, 1);
               } else {
                   itsColor.put(title, 0);
               }
            }

            // set the color based on 1 or 0
            if (itsColor.get(title) == 1) {
                itemLayout.setBackgroundColor(Color.LTGRAY);
            }

            String imageUrl;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            } else {
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).placeholder(R.drawable.placeholder).into(ivPoster);
        }

    }
}
