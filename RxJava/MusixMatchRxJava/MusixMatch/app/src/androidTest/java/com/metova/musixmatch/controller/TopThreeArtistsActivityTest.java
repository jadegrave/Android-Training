package com.metova.musixmatch.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import com.metova.musixmatch.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by jodi on 8/14/17.
 */
public class TopThreeArtistsActivityTest {
    private SharedPreferences sharedPrefs = Mockito.mock(SharedPreferences.class);

    // This declares an instance of TopThreeArtistsActivity
    private TopThreeArtistsActivity mTopThreeArtistsActivity;

    @Rule
    public ActivityTestRule<TopThreeArtistsActivity> mActivityRule = new ActivityTestRule<>(TopThreeArtistsActivity.class);


    @Before
    public void setUp() throws Exception {
        // Note: When activity launches, it using the true shared preferences object; we will have to set
        // it to mocked shared preferences manually

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        // Initialize the instance of the real TopThreeArtists Activity. This allows us to call the
        // SetArtists method
        mTopThreeArtistsActivity = mActivityRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testArtistViewWithDefault() throws Throwable {
        Mockito.when(sharedPrefs.getString(anyString(), anyString())).thenReturn(TopThreeArtistsActivity.DEFAULT);

        // Pass in mocked shared preferences
        mTopThreeArtistsActivity.setSharedPreferencesForTest(sharedPrefs);

        // Have to use runOnUiThread because we're explicitly calling a method inside of the activity under test
        // again
        mActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Uses default values for mocked shared preferences (clears out initial
                // real shared preferences values and uses default
                mTopThreeArtistsActivity.setArtistView();
            }
        });


        // Espresso tests
        onView(withId(R.id.number_one)).check(matches(isDisplayed()));
        onView(withId(R.id.number_two)).check(matches(isDisplayed()));
        onView(withId(R.id.number_three)).check(matches(isDisplayed()));

        //onView(withId(R.id.number_one)).check(matches(has()));
        onView(withId(R.id.number_two)).check(matches(isDisplayed()));
        onView(withId(R.id.number_three)).check(matches(isDisplayed()));


        // Create textView for junit style test
        TextView testOneTextView = (TextView) mActivityRule.getActivity().findViewById(R.id.number_one);
        // junit assertion
        assertEquals(TopThreeArtistsActivity.DEFAULT, testOneTextView.getText());

        // espresso test (same as junit above)
        onView(allOf(withText(TopThreeArtistsActivity.DEFAULT), withId(R.id.number_one))).check(matches(isDisplayed()));


    }

}