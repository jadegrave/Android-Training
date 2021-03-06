package com.metova.musixmatch.controller;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.metova.musixmatch.ArtistAdapter;
import com.metova.musixmatch.R;
import com.metova.musixmatch.api.Client;
import com.metova.musixmatch.api.Service;
import com.metova.musixmatch.model.Artist;
import com.metova.musixmatch.model.ArtistList;
import com.metova.musixmatch.model.ArtistsResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    TextView Disconnected;
    ProgressDialog mProgressDialog;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadJSON();
                Toast.makeText(MainActivity.this, "Artist List Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.fetch_musixmatch_artists_text));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mRecyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON(){
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try{

            Client Client = new Client();
            final Service apiService = Client.getClient().create(Service.class);
            Call<ArtistsResults> call = apiService.getMessage(getData());
            call.enqueue(new Callback<ArtistsResults>() {
                @Override
                public void onResponse(Call<ArtistsResults> call, Response<ArtistsResults> response) {
                    ArrayList<ArtistList> artistArrayList = (ArrayList<ArtistList>)response.body().getMessage().getBody().getArtistList();


                    // Get rid of ArtistList wrapper object around Artist objects
                    // And sort the artists based on artist rating
                    List<Artist> artistArray = new ArrayList<>();
                    artistArray = cleanArtistListArray(artistArrayList);

                    //Create and setup the adapter
                    mRecyclerView.setAdapter(new ArtistAdapter(getApplicationContext(), artistArray));
                    mRecyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    mProgressDialog.hide();
                }

                @Override
                public void onFailure(Call<ArtistsResults> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    mProgressDialog.hide();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private List<Artist> cleanArtistListArray(ArrayList<ArtistList> artistArrayList) {
        List<Artist> artistArray = new ArrayList<>();
        for (ArtistList a : artistArrayList ) {
            artistArray.add(a.getArtist());
        }

        //Sort Artists in ascending order of artist rating
        Collections.sort(artistArray, new Comparator<Artist>() {
            @Override
            public int compare(Artist a1, Artist a2) {
                return a1.getArtistRating() - a2.getArtistRating();
            }
        });


        return artistArray;

    }


    // provides Retrofit2 query parameters to api url
    private Map<String, String> getData() {

        Map<String, String> data = new HashMap<>();
        data.put("format", "json");
        data.put("page", String.valueOf(1));
        data.put("page_size", String.valueOf(100));
        data.put("country", "us");
        data.put("apikey", "d4925412c66d7dcd84a652cf40daad98");
        return data;
    }
}
