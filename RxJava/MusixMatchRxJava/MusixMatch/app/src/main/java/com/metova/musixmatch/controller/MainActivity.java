package com.metova.musixmatch.controller;

import android.app.ProgressDialog;
import android.content.Context;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    TextView mDisconnected;
    ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeContainer;
    private CompositeDisposable mCompositeDisposable;
    private static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar mActionBarMain = getSupportActionBar();
        mActionBarMain.setDisplayShowHomeEnabled(true);


        initViews();

        mSwipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_dark);
        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadJSON();
                Toast.makeText(MainActivity.this, "Artist List Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add("Top 3 Artists");
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.top3MenuItem) {
            Intent intent = new Intent(this, Top3Activity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();  // clears all disposables, but can't accept new disposable
    }

    private void initViews() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.fetch_musixmatch_artists_text));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mRecyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON (){
        mDisconnected=(TextView)findViewById(R.id.disconnected);
        try {

            Client client = new Client();
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

        ArrayList<ArtistList> artistArrayList = (ArrayList<ArtistList>)artistsResults.getMessage().getBody().getArtistList();


        // Get rid of ArtistList wrapper object around Artist objects and sort the artists based on artist rating
        List<Artist> artistArray = new ArrayList<>();
        artistArray = cleanArtistListArray(artistArrayList);

        // save top 3 artists to SharedPreferences
        setTop3((ArrayList<Artist>) artistArray);


        //Create and setup the adapter
        mRecyclerView.setAdapter(new ArtistAdapter(getApplicationContext(), artistArray));
        mRecyclerView.smoothScrollToPosition(0);
        mSwipeContainer.setRefreshing(false);
        mProgressDialog.hide();



    }


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
        Collections.sort(artistArray, new Comparator<Artist>() {
            @Override
            public int compare(Artist a1, Artist a2) {
                return a1.getArtistRating() - a2.getArtistRating();
            }
        });
        return artistArray;
    }


    // Define Shared Preferences file
    public SharedPreferences getSharedPreferences (Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);  // MODE_PRIVATE means no other app can access preferences file
    }

    // Save top 3 artists in Shared Preferences
    public void setTop3 (ArrayList<Artist> allArtists) {
        getSharedPreferences(getApplicationContext()).edit().putString("firstArtistName", String.valueOf(allArtists.get(0).getArtistName())).apply();
        getSharedPreferences(getApplicationContext()).edit().putString("secondArtistName", String.valueOf(allArtists.get(1).getArtistName())).apply();
        getSharedPreferences(getApplicationContext()).edit().putString("thirdArtistName", String.valueOf(allArtists.get(2).getArtistName())).apply();
        getSharedPreferences(getApplicationContext()).edit().putString("firstArtistRating", String.valueOf(allArtists.get(0).getArtistRating())).apply();
        getSharedPreferences(getApplicationContext()).edit().putString("secondArtistRating", String.valueOf(allArtists.get(1).getArtistRating())).apply();
        getSharedPreferences(getApplicationContext()).edit().putString("thirdArtistRating", String.valueOf(allArtists.get(2).getArtistRating())).apply();
    }

    public Map <String, String> getTop3 () {
        Map<String, String > top3Artists = new HashMap<>();

        top3Artists.put("firstArtistName", getSharedPreferences(getApplicationContext()).getString("firstArtistName", "First artist name unavailable"));
        Log.i(TAG, top3Artists.toString());
        top3Artists.put("secondArtistName", getSharedPreferences(getApplicationContext()).getString("secondArtistName", "Second artist name unavailable"));
        top3Artists.put("thirdArtistName", getSharedPreferences(getApplicationContext()).getString("thirdArtistName", "Third artist name unavailable"));
        top3Artists.put("firstArtistRating", getSharedPreferences(getApplicationContext()).getString("firstArtistRating", "First artist rating unavailable"));
        top3Artists.put("secondArtistRating", getSharedPreferences(getApplicationContext()).getString("secondArtistRating", "Second artist rating unavailable"));
        top3Artists.put("thirdArtistRating", getSharedPreferences(getApplicationContext()).getString("thirdArtistRating", "Third artist rating unavailable"));

        return top3Artists;


    }
}
