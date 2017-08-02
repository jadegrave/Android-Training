package com.metova.musixmatch.api;

import com.metova.musixmatch.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.metova.musixmatch.api.Service.API_KEY;

/**
 * Created by jodi on 8/2/17.
 */

public interface Service {

    public static final String API_KEY = "d4925412c66d7dcd84a652cf40daad98";

    @GET("chart.artists.get?format=json&callback=callback&page=1&page_size=100&country=us&apikey=" + API_KEY);

    Call<ItemResponse> getItems();
}
