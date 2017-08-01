package com.treehouse.android.retrofitworkshop.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jodi on 8/1/17.
 */

// This returns back an instance of our API class we just created
// retrofit's addConverterFactory uses GsonConvertoryFactory to take the binary data and convert it into an object type we can use

public class Service {

    public static Imgur.Anon getAnonApi() {

        // Use OkHttp client to create header with access token (per Imgur docs, it has to be in the header)
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request authed = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", "Client-ID " + Imgur.IMGUR_CLIENT_ID)
                                .build();

                        return chain.proceed(authed);
                    }
                }).build();  // returns the actual client

        return new Retrofit.Builder()
                .baseUrl(Imgur.IMGUR_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Imgur.Anon.class);   // in the .create, put in what you
    }


    public static Imgur.Auth getAuthedApi() {

        // Use OkHttp client to create header with access token (per Imgur docs, it has to be in the header)
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request authed = chain.request()
                                        .newBuilder()
                                        .addHeader("Authorization", "Bearer " + OAuthUtil.get(OAuthUtil.ACCESS_TOKEN))
                                        .build();

                        return chain.proceed(authed);
                    }
                }).build();  // returns the actual client

        return new Retrofit.Builder()
                .baseUrl(Imgur.IMGUR_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Imgur.Auth.class);   // in the .create, put in what you
    }
}
