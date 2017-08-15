package com.metova.musixmatch.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;

import com.metova.musixmatch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.metova.musixmatch.controller.MainActivity.PREFS_NAME;


/**
 * Created by jodi on 8/3/17.
 */

public class TopThreeArtistsActivity extends AppCompatActivity{
    public static final String DEFAULT = "Name not available";
    private static final String TAG =  TopThreeArtistsActivity.class.getSimpleName();

    SharedPreferences mSharedPreferences;

    @BindView(R.id.number_one) TextView mFirst;
    @BindView(R.id.number_two) TextView mSecond;
    @BindView(R.id.number_three) TextView mThird;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top3);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        setArtistView();
    }

    public void setArtistView() {
        String artistOne = mSharedPreferences.getString(MainActivity.FIRST_ARTIST_NAME, DEFAULT);
        String artistTwo = mSharedPreferences.getString(MainActivity.SECOND_ARTIST_NAME, DEFAULT);
        String artistThree = mSharedPreferences.getString(MainActivity.THIRD_ARTIST_NAME, DEFAULT);
        Log.d(TAG, artistOne);

        mFirst.setText(artistOne);
        mSecond.setText(artistTwo);
        mThird.setText(artistThree);
    }

    @VisibleForTesting
    // Passes in the mock sharedPreferences for testing only
    // This is set to package-private (no scope modifier) so it's available to test
    public void setSharedPreferencesForTest (SharedPreferences sharedPrefs){
        mSharedPreferences = sharedPrefs;
    }
}
