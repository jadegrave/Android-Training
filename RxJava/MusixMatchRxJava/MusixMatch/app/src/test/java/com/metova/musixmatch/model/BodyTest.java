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


    @Before
    public void setUp() throws Exception {
        mFakeBody = new Body();

        mFakeArtistOne = new Artist("Stan Getz", 99, "https://www.stangetz.com");
        mFakeArtistTwo = new Artist("Esperanza Spalding", 1, "https://www.jazz.com");

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
    public void getArtistList() throws Exception {

        assertNotNull(mFakeListOfArtistList);

        mFakeListOfArtistList.equals(mFakeBody.getArtistList());

    }
}