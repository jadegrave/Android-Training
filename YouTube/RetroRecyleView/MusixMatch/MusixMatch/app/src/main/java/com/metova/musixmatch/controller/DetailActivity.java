package com.metova.musixmatch.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.metova.musixmatch.R;

/**
 * Created by jodi on 8/2/17.
 */

public class DetailActivity extends AppCompatActivity {
    TextView Link, ArtistName;
    Toolbar mActionBarToolbar;
    ImageView imageView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.music_icon);
        ArtistName = (TextView) findViewById(R.id.artist_name_full);

        Link = (TextView) findViewById(R.id.link);

        String artistName = getIntent().getExtras().getString("artist_name");
        //String avatarUrl = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("artist_twitter_url");

        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        ArtistName.setText(artistName);
//        Glide.with(this)
//                .load(avatarUrl)
//                .placeholder(R.drawable.load)
//                .into(imageView);

        getSupportActionBar().setTitle("Details Activity");
    }

    private Intent createShareForcastIntent(){
        String artistName = getIntent().getExtras().getString("artist_name");
        String link = getIntent().getExtras().getString("artist_twitter_url");
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
        menuItem.setIntent(createShareForcastIntent());
        return true;
    }
}
