package com.metova.musixmatch.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;

import com.metova.musixmatch.model.Artist;
import com.metova.musixmatch.model.ArtistList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.metova.musixmatch.R.id.disconnected;
import static org.hamcrest.Matchers.not;

/**
 * Created by jodi on 8/18/17.
 */
public class MainActivityTest {

    private MainActivity mMainActivity;
    private ArrayList<ArtistList> mFakeArtistArray = new ArrayList<ArtistList>();
    private List<Artist> mCleanedAndStortedArtistArray;
    private final String SHARED_PREFS_DEFAULT = "No data available";


    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();
        mMainActivityTestRule.launchActivity(intent);
        mMainActivity = mMainActivityTestRule.getActivity();

        // create test data
        mFakeArtistArray = createFakeArtistList(mFakeArtistArray);

    }


    @Test
    public void testHandleResponseProgressDialogIsInvisible() throws Exception {
        onView(withId(disconnected)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testArtistArrayIsSortedAndWrapperObjectIsGone() {
        mCleanedAndStortedArtistArray = mMainActivity.cleanArtistListArray(mFakeArtistArray);
        Assert.assertEquals("1", String.valueOf(mCleanedAndStortedArtistArray.get(0).getArtistRating()));
        Assert.assertEquals("2", String.valueOf(mCleanedAndStortedArtistArray.get(1).getArtistRating()));
        Assert.assertEquals("3", String.valueOf(mCleanedAndStortedArtistArray.get(2).getArtistRating()));
        Assert.assertEquals("4", String.valueOf(mCleanedAndStortedArtistArray.get(3).getArtistRating()));
        Assert.assertEquals("5", String.valueOf(mCleanedAndStortedArtistArray.get(4).getArtistRating()));
        Assert.assertEquals("6", String.valueOf(mCleanedAndStortedArtistArray.get(5).getArtistRating()));
        Assert.assertEquals("7", String.valueOf(mCleanedAndStortedArtistArray.get(6).getArtistRating()));
        Assert.assertEquals("8", String.valueOf(mCleanedAndStortedArtistArray.get(7).getArtistRating()));
    }

    @Test
    public void testSetTopThreeArtistsInSharedPreferences() throws Exception {
        SharedPreferences sharedPrefs = null;

        mCleanedAndStortedArtistArray = mMainActivity.cleanArtistListArray(mFakeArtistArray);
        mMainActivity.setTopThreeArtists((ArrayList<Artist>) mCleanedAndStortedArtistArray);

        sharedPrefs = mMainActivity.getSharedPreferences(mMainActivity.PREFS_NAME, MODE_PRIVATE);
        String artistOne = sharedPrefs.getString(mMainActivity.FIRST_ARTIST_NAME, SHARED_PREFS_DEFAULT);
        String artistTwo = sharedPrefs.getString(mMainActivity.SECOND_ARTIST_NAME, SHARED_PREFS_DEFAULT);
        String artistThree = sharedPrefs.getString(mMainActivity.THIRD_ARTIST_NAME, SHARED_PREFS_DEFAULT);

        Assert.assertEquals("Artist1", artistOne);
        Assert.assertEquals("Artist2", artistTwo);
        Assert.assertEquals("Artist3", artistThree);

    }


    private ArrayList<ArtistList> createFakeArtistList(ArrayList<ArtistList> mFakeArtistArray) {
        ArrayList<ArtistList> fakeArtistArray = new ArrayList<>();
        for (int i = 8; i >= 1; i--) {
            Artist fakeArtist = new Artist("Artist" + i, i, "http://www.google.com");
            ArtistList fakeArtistList = new ArtistList();
            fakeArtistList.setArtist(fakeArtist);
            fakeArtistArray.add(fakeArtistList);
        }

        return fakeArtistArray;
    }

}