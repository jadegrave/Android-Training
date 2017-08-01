package com.metova.phase2practice;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ArtistInfo mArtistInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "d4925412c66d7dcd84a652cf40daad98";
        String musixMatchUrl = "https://api.musixmatch.com/ws/1.1/chart.artists.get?format=json&callback=callback&page=1&page_size=100&country=us&apikey=" + apiKey;

        if (isNetworkAvailable()) {


            OkHttpClient client = new OkHttpClient();

            //Build request
            Request request = new Request.Builder()
                            .url(musixMatchUrl)
                            .build();

            // Put request in call object
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mArtistInfo = getArtistDetails(jsonData);
                        } else {
                            Log.e(TAG, getString(R.string.log_error_network));
                            alertUserAboutHttpError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, getString(R.string.log_error_http), e);

                    } catch (JSONException e) {
                        Log.e(TAG, getString(R.string.error_json_exception), e);
                    }
                }
            });
        } else {
            alertUserAboutNetworkError();
        }
        Log.d(TAG, getString(R.string.log_info_main_thread));

    }

    private ArtistInfo getArtistDetails(String jsonData) throws JSONException {
        JSONObject allArtists = new JSONObject(jsonData);
        JSONObject message = allArtists.getJSONObject("message");
        JSONObject body = message.getJSONObject("body");
        JSONArray artistList = body.getJSONArray("artist_list");
        Log.i(TAG, "From JSON artist_list array: " + artistList);

        ArtistInfo artistInfo = new ArtistInfo();

       return artistInfo;

    }


    private void alertUserAboutHttpError() {
        AlertDialogHHTPRequestFragment dialog = new AlertDialogHHTPRequestFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutNetworkError() {
        AlertDialogNetworkStatusFragment dialog = new AlertDialogNetworkStatusFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }




}
