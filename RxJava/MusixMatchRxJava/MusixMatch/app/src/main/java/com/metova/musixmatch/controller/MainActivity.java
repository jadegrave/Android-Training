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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    TextView mDisconnected;
    ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeContainer;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
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
        mDisconnected = (TextView) findViewById(R.id.disconnected);
        try{

            Client Client = new Client();
            final Service apiService = Client.getClient().create(Service.class);
//            mCompositeDisposable.add((Disposable) apiService.getMessage(getData()))
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(this::handleResponse, this:: handleError));handleError

            Observable<ArtistsResults> artistResults = Observable.just(apiService.getMessage(getData())
                            .observeOn(Schedulers.io())
                            .subscribeOn(Schedulers.io())



                    })

                    ArrayList<ArtistList> artistArrayList = (ArrayList<ArtistList>)response.body().getMessage().getBody().getArtistList();


                    // Get rid of ArtistList wrapper object around Artist objects
                    // And sort the artists based on artist rating
                    List<Artist> artistArray = new ArrayList<>();
                    artistArray = cleanArtistListArray(artistArrayList);

                    //Create and setup the adapter
                    mRecyclerView.setAdapter(new ArtistAdapter(getApplicationContext(), artistArray));
                    mRecyclerView.smoothScrollToPosition(0);
                    mSwipeContainer.setRefreshing(false);
                    mProgressDialog.hide();
                }

                @Override
                public void onFailure(Call<ArtistsResults> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    mDisconnected.setVisibility(View.VISIBLE);
                    mProgressDialog.hide();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }




}
