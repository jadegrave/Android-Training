package com.metova.musixmatch.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.TextView;

import com.metova.musixmatch.R;

/**
 * Created by jodi on 8/2/17.
 */

public class DetailActivity extends AppCompatActivity {
    TextView shareLink;
    TextView artistFullName;
    TextView artistMmRating;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        artistMmRating = (TextView) findViewById(R.id.rating_text);
        artistFullName = (TextView) findViewById(R.id.artist_name_full);
        shareLink = (TextView) findViewById(R.id.share_link);

        String artistName = getIntent().getExtras().getString("artist_name");
        int rating = getIntent().getExtras().getInt("artist_rating");
        String link = getIntent().getExtras().getString("artist_share_url");

        shareLink.setText(link);
        Linkify.addLinks(shareLink, Linkify.WEB_URLS);

        artistFullName.setText(artistName);
        artistMmRating.setText(String.valueOf(rating));
        getSupportActionBar().setTitle("Details Activity");
    }



    private Intent createShareForecastIntent(){
        String artistName = getIntent().getExtras().getString("artist_name");
        String link = getIntent().getExtras().getString("artist_share_url");

        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this artist @" + artistName + ", " + link)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }


}
