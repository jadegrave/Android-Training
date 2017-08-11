package com.metova.musixmatch.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.widget.TextView;
import android.widget.Toast;

import com.metova.musixmatch.ArtistAdapter;
import com.metova.musixmatch.Events;
import com.metova.musixmatch.R;
import com.metova.musixmatch.RxEventBus;
import com.metova.musixmatch.api.Client;
import com.metova.musixmatch.api.Service;
import com.metova.musixmatch.model.Artist;
import com.metova.musixmatch.model.ArtistList;
import com.metova.musixmatch.model.ArtistsResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String ARTIST_NAME = "artist name";
    public static final String ARTIST_RATING = "artist rating";
    public static final String ARTIST_SHARE_URL = "artist share url";
    public static final String FIRST_ARTIST_NAME = "firstArtistName";
    public static final String SECOND_ARTIST_NAME = "secondArtistName";
    public static final String THIRD_ARTIST_NAME = "thirdArtistName";

    @BindView(R.id.disconnected) TextView mDisconnected;
    private RecyclerView mRecyclerView;
    ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeContainer;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActionBar mActionBarMain = getSupportActionBar();
        mActionBarMain.setDisplayShowHomeEnabled(true);

        initViews();

        mSwipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_dark);
        mSwipeContainer.setOnRefreshListener(() -> {
            loadJSON();
            Toast.makeText(MainActivity.this, "Artist List Refreshed", Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.top3artists) {
            Intent intent = new Intent(this, TopThreeArtistsActivity.class);
            this.startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();  // clears all disposables, but can accept new disposable
    }



    private void initViews() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.getContext();
        mProgressDialog.setMessage(getString(R.string.fetch_musixmatch_artists_text));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mRecyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON (){
        try {
            Service apiService = Client.getClient().create(Service.class);
            mCompositeDisposable.add(apiService.getMessage(getData())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(response -> handleResponse (response), error -> handleError(error)));
        } catch(Exception e){
            Log.d("Error",e.getMessage());
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    private void handleResponse (ArtistsResults artistsResults) {
        mDisconnected.setVisibility(View.INVISIBLE);
        ArrayList<ArtistList> artistArrayList = (ArrayList<ArtistList>)artistsResults.getMessage().getBody().getArtistList();

        // Get rid of ArtistList wrapper object around Artist objects and sort the artists based on artist rating
        List<Artist> artistArray = new ArrayList<>();
        artistArray = cleanArtistListArray(artistArrayList);

        // save top 3 artists to SharedPreferences and put top 3 artist data in map object
        setTopThreeArtists((ArrayList<Artist>) artistArray);

        //Create and setup the adapter
        mRecyclerView.setAdapter(new ArtistAdapter(artistArray));
        mRecyclerView.smoothScrollToPosition(0);
        mSwipeContainer.setRefreshing(false);
        mProgressDialog.dismiss();

        List<Artist> finalArtistArray = artistArray;

        // Use singleton of RxEventbus, subscribe to the event (not the bus)
        RxEventBus.getInstance().toObservable()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    if (event instanceof Events) {
                        int pos = ((Events) event).getPosition();
                        Artist clickedDataItem = finalArtistArray.get(pos);
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra(ARTIST_NAME, clickedDataItem.getArtistName());
                        intent.putExtra(ARTIST_RATING, clickedDataItem.getArtistRating());
                        intent.putExtra(ARTIST_SHARE_URL, clickedDataItem.getArtistShareUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
    }

    // Handle errors in fetching data from MusixMatch API
    public void handleError (Throwable error) {
        Log.d("Error", error.getMessage());
        Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_LONG).show();
        mDisconnected.setVisibility(View.VISIBLE);
        mProgressDialog.hide();
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

    private List<Artist> cleanArtistListArray(ArrayList<ArtistList> artistArrayList) {

        // Remove empty unnecessary object for easier reference in code
        List<Artist> artistArray = new ArrayList<>();
        for (ArtistList a : artistArrayList ) {
            artistArray.add(a.getArtist());
        }
        // Sort Artists in ascending order of artist rating
        Collections.sort(artistArray, (a1, a2) -> a1.getArtistRating() - a2.getArtistRating());
        return artistArray;
    }

    // Save top 3 artists to Shared Preferences
    public void setTopThreeArtists(ArrayList<Artist> allArtists) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(FIRST_ARTIST_NAME, String.valueOf(allArtists.get(0).getArtistName()));
        editor.putString(SECOND_ARTIST_NAME, String.valueOf(allArtists.get(1).getArtistName()));
        editor.putString(THIRD_ARTIST_NAME, String.valueOf(allArtists.get(2).getArtistName()));
        editor.apply();
    }
}
