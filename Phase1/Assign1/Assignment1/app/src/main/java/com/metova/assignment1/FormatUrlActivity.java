package com.metova.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



import static com.metova.assignment1.MainActivity.KEY;


public class FormatUrlActivity extends AppCompatActivity {

    private String mRawUrl;
    private String mFormattedUrl = "";

    private static final String TAG = "SetResult:...";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_url);

        Intent intent = getIntent();
        mRawUrl = intent.getStringExtra(KEY);
        Log.v("Key 2nd activity: ", KEY);
        Log.v("Value 2nd activity", mRawUrl);
        mFormattedUrl = formatUrl(mRawUrl);
        Log.v("After formatting: ", mFormattedUrl);

        intent.putExtra(KEY, mFormattedUrl);
        Log.v("checking URL...", mFormattedUrl);

        if (mFormattedUrl.equals("")  || mFormattedUrl.equals("http://") || mFormattedUrl.equals("https://")){
            setResult(Activity.RESULT_CANCELED);
            Log.v(TAG, "SetResult is set to cancelled");
        } else {
            setResult(Activity.RESULT_OK, intent);
            Log.v("All good", "sending string to Main");

        }
        finish();


    }

    private String formatUrl (String rawUrl){
        final String urlProtocolHTTP = "http://";
        final String urlProtocolHTTPS = "https://";
        String formattedUrl = "";

        if (rawUrl.equals("") || rawUrl.toLowerCase().startsWith(urlProtocolHTTP) || rawUrl.toLowerCase().startsWith(urlProtocolHTTPS)){
            formattedUrl = rawUrl;
        } else {
            formattedUrl = urlProtocolHTTPS + rawUrl;
        }


        return formattedUrl;

    }



}