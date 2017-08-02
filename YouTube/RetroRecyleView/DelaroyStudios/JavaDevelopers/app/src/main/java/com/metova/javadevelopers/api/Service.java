package com.metova.javadevelopers.api;

import com.metova.javadevelopers.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jdegr on 8/1/2017.
 */

public interface Service {
    @GET("/search/users?q=language:java+location:newyork")
    Call<ItemResponse> getItems();
}
