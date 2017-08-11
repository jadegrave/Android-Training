package com.metova.musixmatch.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by jodi on 8/11/17.
 */

public class ArtistResultsTest {

    @Test
    public void getMessageFromMusixMatchAPI () {

        ArtistsResults fakeArtistResults = new ArtistsResults();
        Message fakeMessage = new Message();

        fakeArtistResults.setMessage(fakeMessage);

        assertEquals(fakeMessage, fakeArtistResults.getMessage());

    }



}
