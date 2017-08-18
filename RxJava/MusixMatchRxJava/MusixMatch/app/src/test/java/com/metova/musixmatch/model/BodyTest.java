package com.metova.musixmatch.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by jodi on 8/14/17.
 */
public class BodyTest {

    private Body mFakeBody;
    private List<ArtistList> mFakeListOfArtistList = new ArrayList<>();
    private ArtistList mArtistListOne;
    private ArtistList mArtistListTwo;
    private Artist mFakeArtistOne;
    private Artist mFakeArtistTwo;

    private String artistName1 = "Stan Getz";
    private int artistRating1 = 99;
    private String artistShareUrl1 = "https://www.stangetz.com";

    private String artistName2 = "Esperanza Spalding";
    private int artistRating2 = 1;
    private String artistShareUrl2 =  "https://www.jazz.com";

    @Before
    public void setUp() throws Exception {
        mFakeBody = new Body();


        mFakeArtistOne = new Artist(artistName1, artistRating1, artistShareUrl1);
        mFakeArtistTwo = new Artist(artistName2, artistRating2, artistShareUrl2);

        mArtistListOne = new ArtistList();
        mArtistListTwo = new ArtistList();

        mArtistListOne.setArtist(mFakeArtistOne);
        mArtistListTwo.setArtist(mFakeArtistTwo);

        mFakeListOfArtistList.add(mArtistListOne);
        mFakeListOfArtistList.add(mArtistListTwo);

        mFakeBody.setArtistList(mFakeListOfArtistList);
    }

    @After
    public void tearDown() throws Exception {
        mFakeListOfArtistList = null;
        mArtistListOne = null;
        mArtistListTwo = null;
        mFakeArtistOne = null;
        mFakeArtistTwo = null;
    }

    @Test
    public void testGetArtistList() throws Exception {

        assertNotNull(mFakeListOfArtistList);

        assertTrue(mFakeListOfArtistList.equals(mFakeBody.getArtistList()));

    }
}