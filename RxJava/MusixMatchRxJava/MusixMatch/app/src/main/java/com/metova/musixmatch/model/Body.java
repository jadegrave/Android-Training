
package com.metova.musixmatch.model;

import android.support.annotation.VisibleForTesting;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body {

    @SerializedName("artist_list")
    @Expose
    private List<ArtistList> artistList = null;

    public List<ArtistList> getArtistList() {
        return artistList;
    }

    @VisibleForTesting
    public void setArtistList(List<ArtistList> artistList) {
        this.artistList = artistList;
    }
}
