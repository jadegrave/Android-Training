package com.metova.musixmatch.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jodi on 8/11/17.
 */
public class ArtistTest {

    //private Artist fakeTestArtist = new Artist("Bangles", 23, "http://www.google.com");
    private Artist fakeTestArtist = new Artist();


    @Test
    public void getArtistName() throws Exception {
        fakeTestArtist.setArtistName("Bangles");
        assertEquals("Bangles", fakeTestArtist.getArtistName());
    }

    @Test
    public void getArtistRating() throws Exception {
        fakeTestArtist.setArtistRating(45);
        assertEquals(45, fakeTestArtist.getArtistRating());
        assertEquals  ();
    }

    @Test
    public void getArtistShareUrl() throws Exception {

    }

}