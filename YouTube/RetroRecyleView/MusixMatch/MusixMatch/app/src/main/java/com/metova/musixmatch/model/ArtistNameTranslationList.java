
package com.metova.musixmatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistNameTranslationList {

    @SerializedName("artist_name_translation")
    @Expose
    private ArtistNameTranslation artistNameTranslation;

    public ArtistNameTranslation getArtistNameTranslation() {
        return artistNameTranslation;
    }

    public void setArtistNameTranslation(ArtistNameTranslation artistNameTranslation) {
        this.artistNameTranslation = artistNameTranslation;
    }

}
