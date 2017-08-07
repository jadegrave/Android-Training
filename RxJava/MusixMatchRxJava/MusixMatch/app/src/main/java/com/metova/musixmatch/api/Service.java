package com.metova.musixmatch.api;

import com.metova.musixmatch.model.ArtistsResults;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;


/**
 * Created by jodi on 8/2/17.
 */

public interface Service {

    @Headers("Content-Type: application/json")
    @GET("ws/1.1/chart.artists.get")
    Observable<ArtistsResults> getMessage (@QueryMap Map<String, String> options);
}
