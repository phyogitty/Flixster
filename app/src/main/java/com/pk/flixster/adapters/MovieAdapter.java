package com.pk.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pk.flixster.MovieDetailActivity;
import com.pk.flixster.R;
import com.pk.flixster.models.Movie;

//import org.parceler.Parcels;

import org.parceler.Parcels;

import java.util.List;

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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        Movie movie = movies.get(position);
        holder.bind(movie);
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
        Button btnShowmore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverView = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            itemLayout = itemView.findViewById(R.id.itemLayout);
            btnShowmore = itemView.findViewById(R.id.btnShowmore);
//            itemView.setOnClickListener(this);
        }




        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            // cut the long text
            if (movie.getOverview().length() > 250) {
                tvOverView.setText(movie.getOverview().substring(0, 250) + "...");
                btnShowmore.setEnabled(true);
                btnShowmore.setVisibility(View.VISIBLE);
            } else {
                tvOverView.setText(movie.getOverview());
                btnShowmore.setVisibility(View.GONE);

            }

            btnShowmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String overView = movie.getOverview();
                    if (tvOverView.getText().length() == 253) {
                        tvOverView.setText(overView);
                        btnShowmore.setText("Show Less");
                    } else {
                        tvOverView.setText(overView.substring(0, 250) + "...");
                        btnShowmore.setText("Show More");
                    }

                }
            });
            String imageUrl;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            } else {
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).placeholder(R.drawable.placeholder).into(ivPoster);

            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, MovieDetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });

        }

    }
}
