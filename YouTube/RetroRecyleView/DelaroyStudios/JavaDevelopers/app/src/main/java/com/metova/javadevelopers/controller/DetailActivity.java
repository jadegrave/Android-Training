package com.metova.javadevelopers.controller;

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

import com.bumptech.glide.Glide;
import com.metova.javadevelopers.R;


import butterknife.BindView;

/**
 * Created by jdegr on 8/1/2017.
 */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.link) TextView Link;
    @BindView(R.id.username) TextView Username;
    Toolbar mActionBarToolbar;
    @BindView(R.id.user_image_header) ImageView imageView;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // up navigation
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        String username = getIntent().getExtras().getString("login");
        String avatarUrl = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        Username.setText(username);
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.load)
                .into(imageView);

        getSupportActionBar().setTitle(R.string.support_action_bar_text_details_activity);
    }

        //create an intent for Share menu
        private Intent createShareForecastIntent() {
            String username = getIntent().getExtras().getString("login");
            String link = getIntent().getExtras().getString("link");
            Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                    .setType("text/plain")
                    .setText("Check out this awesome developer @" + username +  ", "  + link)
                    .getIntent();
            return shareIntent;

        }


        //Inflate the Share menu option, pass shareForce
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.detail, menu);
            MenuItem menuItem = menu.findItem(R.id.action_share);
            menuItem.setIntent(createShareForecastIntent());
            return true;
        }

    }

