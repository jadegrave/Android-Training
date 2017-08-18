package com.metova.musixmatch.controller;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.metova.musixmatch.R;
import com.metova.musixmatch.model.Artist;
import com.metova.musixmatch.model.ArtistList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.metova.musixmatch.R.id.disconnected;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by jodi on 8/18/17.
 */
public class MainActivityTest {

    private MainActivity mMainActivity;


    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();
        mMainActivityTestRule.launchActivity(intent);
        mMainActivity = mMainActivityTestRule.getActivity();

        ArrayList<ArtistList> fakeArtistArray = createFakeArtistList();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHandleResponseProgressDialogIsInvisible() throws Exception {
        onView(withId(disconnected)).check(matches(not(isDisplayed())));


    }

    @Test
    public void setTopThreeArtists() throws Exception {

    }

    private ArrayList<ArtistList> createFakeArtistList() {
        ArrayList<ArtistList> fakeArtistArray = new ArrayList<>();
        for (int i = 8; i <= 1; i--) {
            Artist fakeArtist = new Artist("Artist" + i, i, "http://www.google.com");
            ArtistList fakeArtistList = new ArtistList();
            fakeArtistList.setArtist(fakeArtist);
            fakeArtistArray.add(fakeArtistList);
        }

        return fakeArtistArray;
    }

}