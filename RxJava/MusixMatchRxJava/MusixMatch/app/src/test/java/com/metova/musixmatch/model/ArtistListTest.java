package com.metova.musixmatch.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jodi on 8/11/17.
 */
public class ArtistListTest {

    private ArtistList mFakeArtistListTest;
    private Artist mFakeArtist;

    @Before
    public void setup() {
        mFakeArtistListTest = new ArtistList();
        mFakeArtist = new Artist("Prince", 35, "http.facebook.com");
        mFakeArtistListTest.setArtist(mFakeArtist);
    }

    @After
    public void tearDown() {
        mFakeArtistListTest = null;
        mFakeArtist = null;
    }

    @Test
    public void getArtist() throws Exception {
        assertEquals(mFakeArtist, mFakeArtistListTest.getArtist());
    }

}