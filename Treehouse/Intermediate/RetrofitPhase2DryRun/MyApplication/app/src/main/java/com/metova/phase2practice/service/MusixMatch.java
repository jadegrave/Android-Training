package com.metova.phase2practice.service;

/**
 * Created by jodi on 8/1/17.
 */

public interface MusixMatch {
    String MUSIXMATCH_BASE_URL = "https://api.musixmatch.com/ws/1.1";
    String API_KEY = "d4925412c66d7dcd84a652cf40daad98";

    interface ChartArtistInterface {
        @GET()
    }

}
