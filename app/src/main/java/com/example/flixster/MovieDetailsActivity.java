package com.example.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flixster.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {
    //the movie to display
    Movie movie;

    //the view objects
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    TextView tvDate;

//    //config needed for image urls
//    Config config;
//    //context for rendering
//    Context context;
//
//    public Config getConfig() {
//        return config;
//    }
//
//    public void setConfig(Config config) {
//        this.config = config;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        //resolve the view objects
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        tvDate = (TextView) findViewById(R.id.tvDate);

        //unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        //set the title, overview, and date
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvDate.setText("Release date: " + movie.getTvDate());

        //vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);

//        //include image
//        ImageView detailImage = (ImageView) findViewById(R.id.detailImage);
//        String imageUrl = config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
//        //load image using glide
//        Glide.with(context)
//                .load(imageUrl)
//                .error(R.drawable.flicks_backdrop_placeholder)
//                .into(detailImage);
    }
}
