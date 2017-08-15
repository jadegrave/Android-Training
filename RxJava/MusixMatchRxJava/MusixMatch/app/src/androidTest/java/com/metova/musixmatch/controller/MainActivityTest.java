package com.metova.musixmatch.controller;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.ActionBar;

import com.metova.musixmatch.R;
import com.metova.musixmatch.model.Artist;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by jodi on 8/15/17.
 */
public class MainActivityTest {

    // This declares an instance of TopThreeArtistsActivity
    private MainActivity mMainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        mMainActivity = mActivityRule.getActivity();

        ArrayList<Artist> fakeArtistArray = createFakeArtistList();

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void checkActionBarEnabledToDisplayAsTrue() throws Exception {

    }

    @Test
    public void testInitializeViews() throws Exception {

        //onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));

    }

    @Test
    public void testArtistListInAscendingOrder () {

    }

    @Test
    public void onCreateOptionsMenu() throws Exception {

    }

    @Test
    public void onOptionsItemSelected() throws Exception {

    }

    @Test
    public void onDestroy() throws Exception {

    }

    @Test
    public void handleError() throws Exception {

    }

    @Test
    public void setTopThreeArtists() throws Exception {

    }

    private ArrayList<Artist> createFakeArtistList() {
        ArrayList<Artist> fakeArtistArray = new ArrayList<>();
        for (int i = 1; i >= 8; i++) {
            Artist fakeArtist = new Artist("Artist" + i, i, "http://www.google.com");
            fakeArtistArray.add(fakeArtist);
        }

        return fakeArtistArray;
    }




    }


}