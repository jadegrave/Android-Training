package com.metova.musixmatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jodi on 8/2/17.
 */

public class Item {
    @SerializedName("artist_name")
    @Expose
    private String artistName;

    @SerializedName("artist_country")
    @Expose
    private String artistCountry;

    @SerializedName("artist_rating")
    @Expose
    private int artistRating;


    @SerializedName("artist_twitter_url")
    @Expose
    private String artistTwitterUrl;

    @SerializedName("artist_share_url")
    @Expose
    private String artistShareUrl;


    public Item (String artistName, String artistCountry, int artistRating, String artistTwitterUrl, String artistShareUrl) {
        this.artistName = artistName;
        this.artistCountry = artistCountry;
        this.artistRating = artistRating;
        this.artistTwitterUrl = artistTwitterUrl;
        this.artistShareUrl = artistShareUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistCountry() {
        return artistCountry;
    }

    public void setArtistCountry(String artistCountry) {
        this.artistCountry = artistCountry;
    }

    public int getArtistRating() {
        return artistRating;
    }

    public void setArtistRating(int artistRating) {
        this.artistRating = artistRating;
    }

    public String getArtistTwitterUrl() {
        return artistTwitterUrl;
    }

    public void setArtistTwitterUrl(String artistTwitterUrl) {
        this.artistTwitterUrl = artistTwitterUrl;
    }

    public String getArtistShareUrl() {
        return artistShareUrl;
    }

    public void setArtistShareUrl(String artistShareUrl) {
        this.artistShareUrl = artistShareUrl;
    }
}
