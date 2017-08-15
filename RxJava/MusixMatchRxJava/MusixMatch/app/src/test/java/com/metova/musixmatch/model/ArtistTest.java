package com.metova.musixmatch.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;

/**
 * Created by jodi on 8/11/17.
 */
public class ArtistTest {

    private Artist mFakeTestArtist;

    @Before
    public void setup(){
        mFakeTestArtist = new Artist("Bangles", 23, "http://www.google.com");
    }


    @After
    public void tearDown(){
        mFakeTestArtist = null;
    }


    @Test
    public void getArtistName() throws Exception {
        mFakeTestArtist.setArtistName("Bangles");
        assertEquals("Bangles", mFakeTestArtist.getArtistName());
    }

    @Test
    public void getArtistRating() throws Exception {
        assertEquals(23, (int) mFakeTestArtist.getArtistRating());
    }

    @Test
    public void getArtistShareUrl() throws Exception {
        assertEquals("http://www.google.com", mFakeTestArtist.getArtistShareUrl());

    }

}