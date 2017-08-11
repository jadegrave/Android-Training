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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jodi on 8/2/17.
 */

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.share_link) TextView shareLink;
    @BindView(R.id.artist_name_full) TextView artistFullName;
    @BindView(R.id.rating_text) TextView artistMmRating;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String artistName = getIntent().getExtras().getString(MainActivity.ARTIST_NAME);
        int rating = getIntent().getExtras().getInt(MainActivity.ARTIST_RATING);
        String link = getIntent().getExtras().getString(MainActivity.ARTIST_SHARE_URL);

        shareLink.setText(link);
        Linkify.addLinks(shareLink, Linkify.WEB_URLS);

        artistFullName.setText(artistName);
        artistMmRating.setText(String.valueOf(rating));
        getSupportActionBar().setTitle(R.string.action_bar_title_details_activity);
    }



    private Intent createShareArtistIntent(){
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
        menuItem.setIntent(createShareArtistIntent());
        return true;
    }


}
