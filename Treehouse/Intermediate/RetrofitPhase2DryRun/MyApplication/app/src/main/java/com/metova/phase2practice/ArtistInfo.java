package com.metova.phase2practice;

import java.util.ArrayList;

/**
 * Created by jodi on 7/31/17.
 */

public class ArtistInfo {
    private String mMessage;
    private String mBody;
    private ArrayList mArtist_List;
    private String mArtist;
    private String mArtistId;
    private String mArtistName;
    private String mArtistCountry;
    private int mArtistRating;
    private String mPrimaryGenres;
    private ArrayList<String> mMusicGenreList;
    private String mMusicGenreName;
    private String mMusicGenreNameExtended;

    private String mArtistShareUrl;
    private String mArtistTwitterUrl;


    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public ArrayList getArtist_List() {
        return mArtist_List;
    }

    public void setArtist_List(ArrayList artist_List) {
        mArtist_List = artist_List;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public int getArtistRating() {
        return mArtistRating;
    }

    public void setArtistRating(int artistRating) {
        mArtistRating = artistRating;
    }

    public String getPrimaryGenres() {
        return mPrimaryGenres;
    }

    public void setPrimaryGenres(String primaryGenres) {
        mPrimaryGenres = primaryGenres;
    }

    public ArrayList<String> getMusicGenreList() {
        return mMusicGenreList;
    }

    public void setMusicGenreList(ArrayList<String> musicGenreList) {
        mMusicGenreList = musicGenreList;
    }

    public String getArtistShareUrl() {
        return mArtistShareUrl;
    }

    public void setArtistShareUrl(String artistShareUrl) {
        mArtistShareUrl = artistShareUrl;
    }

    public String getArtistTwitterUrl() {
        return mArtistTwitterUrl;
    }

    public void setArtistTwitterUrl(String artistTwitterUrl) {
        mArtistTwitterUrl = artistTwitterUrl;
    }

    public String getMusicGenreName() {
        return mMusicGenreName;
    }

    public void setMusicGenreName(String musicGenreName) {
        mMusicGenreName = musicGenreName;
    }

    public String getMusicGenreNameExtended() {
        return mMusicGenreNameExtended;
    }

    public void setMusicGenreNameExtended(String musicGenreNameExtended) {
        mMusicGenreNameExtended = musicGenreNameExtended;
    }

    public String getArtistId() {
        return mArtistId;
    }

    public void setArtistId(String artistId) {
        mArtistId = artistId;
    }
}
