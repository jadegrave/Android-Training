package com.metova.musixmatch.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by jodi on 8/11/17.
 */

public class ArtistResultsTest {

    private ArtistsResults mArtistsResults;
    private Message mFakeMessage;


    @Before
    public void setup() {
        mArtistsResults = new ArtistsResults();
        mFakeMessage = new Message();
    }

    @After
    public void tearDown(){
        mArtistsResults = null;
        mFakeMessage = null;
    }

    @Test
    public void testGetMessageFromMusixMatchAPI() throws Exception{

        mArtistsResults.setMessage(mFakeMessage);

        assertEquals(mFakeMessage, mArtistsResults.getMessage());

    }
}
