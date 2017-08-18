package com.metova.musixmatch.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jodi on 8/11/17.
 */
public class ArtistTest {

    private Artist mFakeTestArtist;
    private String artistName = "Bangles";
    private int artistRating = 23;
    private String artistShareUrl = "https://www.google.com";

    @Before
    public void setup() {
        mFakeTestArtist = new Artist(artistName, artistRating, artistShareUrl);
    }

    @After
    public void tearDown() {
        mFakeTestArtist = null;
    }

    @Test
    public void testGetArtistName() throws Exception {
        mFakeTestArtist.setArtistName(artistName);
        assertEquals(artistName, mFakeTestArtist.getArtistName());
    }

    @Test
    public void testGetArtistRating() throws Exception {
        assertEquals(artistRating, (int) mFakeTestArtist.getArtistRating());
    }

    @Test
    public void testGetArtistShareUrl() throws Exception {
        assertEquals(artistShareUrl, mFakeTestArtist.getArtistShareUrl());

    }
}