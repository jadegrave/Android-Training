package com.metova.musixmatch.controller;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jodi on 8/15/17.
 */
public class DetailActivityTest {


    private DetailActivity mDetailActivity;

    @Rule
    public ActivityTestRule<DetailActivity> mActivityRule = new ActivityTestRule<>(DetailActivity.class);

    @Before
    public void setUp() throws Exception {
        //launch the test Detail Activity
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        //Initialize the instance of the real DetailActivity, so we access various methods for test
        mDetailActivity = mActivityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getIntentInfoArtistRatingLink () throws Exception {

    }

    @Test
    public void onCreateOptionsMenu() throws Exception {

    }

}