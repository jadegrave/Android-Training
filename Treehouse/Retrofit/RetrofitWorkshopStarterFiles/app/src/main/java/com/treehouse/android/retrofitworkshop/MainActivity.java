package com.treehouse.android.retrofitworkshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.treehouse.android.retrofitworkshop.api.Imgur;
import com.treehouse.android.retrofitworkshop.api.OAuthUtil;
import com.treehouse.android.retrofitworkshop.api.Service;
import com.treehouse.android.retrofitworkshop.model.Basic;
import com.treehouse.android.retrofitworkshop.model.Image;
import com.treehouse.android.retrofitworkshop.view.ImageAdapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_sign_in)
    View signInBtn;
    @BindView(R.id.btn_upload_anon)
    View uploadAnon;

    @BindView(R.id.account_images_container)
    View accountImagesContainer;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.btn_upload)
    View upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        signInBtn.setOnClickListener(this);
        uploadAnon.setOnClickListener(this);
        upload.setOnClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new ImageAdapter(this));

        if (OAuthUtil.isAuthorized()) {
            // TODO set title
            showAccountImages();
        } else {
            toolbar.setTitle("Login");
            showLoginOrAnon();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO
    }

    private void showLoginOrAnon() {
        accountImagesContainer.setVisibility(View.GONE);
        signInBtn.setVisibility(View.VISIBLE);
        uploadAnon.setVisibility(View.VISIBLE);
    }

    private void showAccountImages() {
        fetchAccountImages();

        accountImagesContainer.setVisibility(View.VISIBLE);
        signInBtn.setVisibility(View.GONE);
        uploadAnon.setVisibility(View.GONE);
    }

    private void fetchAccountImages() {

        Snackbar.make(upload, "Getting images for Account", Snackbar.LENGTH_SHORT).show();
        Service.getAuthedApi().images(OAuthUtil.get(OAuthUtil.ACCOUNT_USERNAME), 0)
                .enqueue(new Callback<Basic<ArrayList<Image>>>() {
                    @Override
                    public void onResponse(Call<Basic<ArrayList<Image>>> call, Response<Basic<ArrayList<Image>>> response) {
                        if (response.code() == HttpURLConnection.HTTP_OK) {
                            ((ImageAdapter) recyclerView.getAdapter()).swap(response.body().data);
                        } else {
                            Snackbar.make(upload, "Failed :(", Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Basic<ArrayList<Image>>> call, Throwable t) {
                        Snackbar.make(upload, "Failed :(", Snackbar.LENGTH_SHORT).show();
                    }
                });
    }


    private void upload() {
        Snackbar.make(upload, "Uploading Image", Snackbar.LENGTH_SHORT).show();

        try {
            BufferedSource img = Okio.buffer(Okio.source(getAssets().open("sample_image.jpg")));

            byte[] image = img.readByteArray();

            Service.getAuthedApi().uploadImage(
                    RequestBody.create(
                            MediaType.parse("image/jpeg"),
                            image
                    )
            ).enqueue(new Callback<Basic<Image>>() {
                @Override
                public void onResponse(Call<Basic<Image>> call, Response<Basic<Image>> response) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        fetchAccountImages();
                    } else {
                        Snackbar.make(upload, "Failed :(", Snackbar.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Basic<Image>> call, Throwable t) {

                }
            });
        } catch (IOException e) {

        }
    }


    private void uploadAnon() {
        Snackbar.make(upload, "Uploading Anon Image", Snackbar.LENGTH_SHORT).show();

        try {
            BufferedSource img = Okio.buffer(Okio.source(getAssets().open("sample_image.jpg")));

            byte[] image = img.readByteArray();

            Service.getAnonApi().uploadImage(
                    RequestBody.create(
                            MediaType.parse("image/jpeg"),
                            image
                    )
            ).enqueue(new Callback<Basic<Image>>() {
                @Override
                public void onResponse(Call<Basic<Image>> call, Response<Basic<Image>> response) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        // get the URL
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().data.link)));
                    } else {
                        Snackbar.make(upload, "Failed :(", Snackbar.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Basic<Image>> call, Throwable t) {

                }
            });
        } catch (IOException e) {

        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_upload_anon:
                uploadAnon();
                break;
            case R.id.btn_upload:
                // TODO
                upload();
                break;
            case R.id.btn_sign_in:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Imgur.AUTHORIZATION_URL)));
                // TODO start login process
                break;
        }
    }


}

