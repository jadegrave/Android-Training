package com.metova.musixmatch.controller;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import com.metova.musixmatch.R;

import org.hamcrest.Matcher;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;


import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by jodi on 8/15/17.
 */
public class DetailActivityTest {


    public static final String ARTIST_NAME = "artist name";
    public static final String ARTIST_RATING = "artist rating";
    public static final String ARTIST_SHARE_URL = "artist share url";



    @Rule
    public ActivityTestRule<DetailActivity> mActivityRule =
            new ActivityTestRule<DetailActivity>(DetailActivity.class) {

                // Overriding getActivityIntent so that all tests can use it
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent intent = new Intent(targetContext, DetailActivity.class);
                    intent.putExtra(ARTIST_NAME, "Ed Sheeran");
                    intent.putExtra(ARTIST_RATING, 100);
                    intent.putExtra(ARTIST_SHARE_URL, "https://www.google.com");
                    return intent;
                }
            };

    @Test
    public void checkIntentPassedFromMainActivityArtistName () throws Exception {
        onView(withId(R.id.artist_name_full)).check(matches(withText("Ed Sheeran")));

    }

    @Test
    public void checkIntentPassedFromMainActivityArtistRating () throws Exception {
       onView(withId(R.id.rating_text)).check(matches(withText("100")));

    }

    @Test
    public void checkIntentPassedFromMainActivityShareURL () throws Exception {
        onView(withId(R.id.share_link)).check(matches(withText("https://www.google.com")));
    }

    @Test
    public void displayArtistWebSiteWhenUrlIsClicked () throws Exception {
        Intents.init();
        Matcher<Intent> expectedIntent = hasData("https://www.google.com");
        intending(expectedIntent).respondWith(new Instrumentation.ActivityResult(0, null));
        onView(withId(R.id.share_link)).perform(click());
        intended(expectedIntent);
        Intents.release();

    }

    @Test
    public void ShareOptionsMenuDisplaysOnDetailActivity () {
        // ensures share option is displayed
        onView(withId(R.id.action_share)).check(matches(isDisplayed()));

    }
}