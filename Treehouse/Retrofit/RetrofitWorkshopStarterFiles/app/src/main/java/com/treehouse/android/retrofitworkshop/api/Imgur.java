package com.treehouse.android.retrofitworkshop.api;

import com.treehouse.android.retrofitworkshop.model.Basic;
import com.treehouse.android.retrofitworkshop.model.Image;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by jodi on 8/1/17.
 */

public interface Imgur {
    String IMGUR_BASE_URL = "https://api.imgur.com";
    String IMGUR_CLIENT_ID = "3c6467f227ca866";
    String AUTHORIZATION_URL = "https://api.imgur.com/oauth2/authorize?client_id=" + IMGUR_CLIENT_ID + "&response_type=token";
    String REDIRECT_URL = "https://treehouseworkshop:88";

    interface Auth {
        @GET("3/account/{username}/images{page}")
        Call<Basic<ArrayList<Image>>> images(@Path("username") String username,
                                             @Path("page") int page);


        // this uploads images and return the url of where images were uploaded to. It is Multipart because it uses the same format as a form posting data
        @Multipart
        @POST("3/upload")
        Call<Basic<Image>> uploadImage(@Part("image") RequestBody image);

    }

    interface Anon {
        @Multipart
        @POST("3/upload")
        Call<Basic<Image>> uploadImage(@Part("image") RequestBody image);
    }


}
