package com.metova.musixmatch.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jodi on 8/2/17.
 */

public class Client {

    public static final String BASE_URL ="https://api.musixmatch.com/ws/1.1/";
    public static Retrofit retrofit = null;

    public static Retrofit getclient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
