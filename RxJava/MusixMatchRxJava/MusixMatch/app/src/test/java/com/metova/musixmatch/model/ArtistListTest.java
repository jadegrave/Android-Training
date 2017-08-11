package com.metova.musixmatch.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jodi on 8/11/17.
 */
public class ArtistListTest {


    private ArtistList fakeArtistListTest = new ArtistList();
    private Artist fakeArtist = new Artist();

    @Test
    public void getArtist() throws Exception {

        assertEquals(fakeArtist, fakeArtistListTest.getArtist());

    }

}