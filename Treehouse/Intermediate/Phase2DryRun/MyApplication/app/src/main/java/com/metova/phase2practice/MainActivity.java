package com.metova.phase2practice;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "d4925412c66d7dcd84a652cf40daad98";
        String musixMatchUrl = "https://api.musixmatch.com/ws/1.1/chart.artists.get?format=json&callback=callback&page=1&page_size=2&country=us&apikey=" + apiKey;

        //Starting the task. Pass an url as the parameter.
        new PostTask().execute(musixMatchUrl);
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

    // The definition of our task class
    private class PostTask extends AsyncTask<String, String, String> {
        private String resp;
        ProgressDialog lyricsProgressBar;


        @Override
        protected String doInBackground(String... params) {
            publishProgress("Fetching lyric lists...");   // Calls progress update
            String url = params[0];
            if (isNetworkAvailable()) {


                OkHttpClient client = new OkHttpClient();

                //Build request
                Request request = new Request.Builder().url(url).build();

                // Put request in call object
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            Log.v(TAG, response.body().string());
                            if (response.isSuccessful()) {
                                resp = response.body().string();

                            } else {
                                Log.e(TAG, getString(R.string.log_error_network));
                                alertUserAboutHttpError();
                            }
                        } catch (IOException e) {
                            Log.e(TAG, getString(R.string.log_error_http), e);

                        }
                    }
                });
            } else {
                alertUserAboutNetworkError();
            }
            Log.d(TAG, getString(R.string.log_info_main_thread));

            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            lyricsProgressBar.dismiss();
            Log.v(TAG, result);
            Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            lyricsProgressBar = ProgressDialog.show(MainActivity.this, "Lyrics Progress", "Downloading lyrics...");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            Log.v(TAG, text[0]);
        }

    }
}
