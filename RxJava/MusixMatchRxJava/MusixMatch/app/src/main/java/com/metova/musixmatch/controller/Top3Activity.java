package com.metova.musixmatch.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.metova.musixmatch.R;

import static com.metova.musixmatch.controller.MainActivity.PREFS_NAME;


/**
 * Created by jodi on 8/3/17.
 */

public class Top3Activity extends AppCompatActivity{
    public static final String DEFAULT = "Name not available";
    private static final String TAG =  Top3Activity.class.getSimpleName();

    private TextView mFirst;
    private TextView mSecond;
    private TextView mThird;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top3);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirst = (TextView) findViewById(R.id.number_one);
        mSecond = (TextView) findViewById(R.id.number_two);
        mThird = (TextView) findViewById(R.id.number_three);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String artistOne = sharedPreferences.getString("firstArtistName", DEFAULT);
        String artistTwo = sharedPreferences.getString("secondArtistName", DEFAULT);
        String artistThree = sharedPreferences.getString("thirdArtistName", DEFAULT);
        Log.d(TAG, artistOne);

        mFirst.setText(artistOne);
        mSecond.setText(artistTwo);
        mThird.setText(artistThree);

    }







}
