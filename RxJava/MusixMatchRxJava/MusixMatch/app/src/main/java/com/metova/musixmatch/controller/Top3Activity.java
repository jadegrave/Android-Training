package com.metova.musixmatch.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class Top3Activity extends AppCompatActivity{
    public static final String DEFAULT = "Name not available";
    private static final String TAG =  Top3Activity.class.getSimpleName();

    @BindView(R.id.number_one) TextView mFirst;
    @BindView(R.id.number_two) TextView mSecond;
    @BindView(R.id.number_three) TextView mThird;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top3);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
