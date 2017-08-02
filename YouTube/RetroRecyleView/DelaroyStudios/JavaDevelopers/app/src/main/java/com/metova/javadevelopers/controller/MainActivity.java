package com.metova.javadevelopers.controller;

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

import com.metova.javadevelopers.ItemAdapter;
import com.metova.javadevelopers.R;
import com.metova.javadevelopers.api.Client;
import com.metova.javadevelopers.api.Service;
import com.metova.javadevelopers.model.Item;
import com.metova.javadevelopers.model.ItemResponse;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.disconnected) TextView mDisconnected;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout mSwipeContainer;
    private Item item;
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();  // initialize views

        // initialize swipe container
        mSwipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(MainActivity.this, "Github Users Refreshed", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private void initViews() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Fetching Github Users...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.smoothScrollToPosition(0);
        loadJSON();

    }

    private void loadJSON() {
        try {
            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<ItemResponse> call = apiService.getItems();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItems();
                    mRecyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    mRecyclerView.smoothScrollToPosition(0);
                    mSwipeContainer.setRefreshing(false);
                    mProgressDialog.hide();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    mDisconnected.setVisibility(View.VISIBLE);
                    mProgressDialog.hide();
                }
            });
        } catch (Exception e) {
            Log.d("Error ", e.getMessage());
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}