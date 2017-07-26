package com.metova.musicmachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mDownloadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDownloadButton = (Button) findViewById(R.id.downloadButton);

        mDownloadButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Downloading", Toast.LENGTH_SHORT).show();
                downloadSong();
            }
        });

    }

    private void downloadSong() {
        long endtime = System.currentTimeMillis() + 10 * 1000;  // time from Jan 1 1970 (dawn of Unix epoch) in milliseconds
        while (System.currentTimeMillis() < endtime) {
            try {
                Thread.sleep(1000);   //waits
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "Song downloaded!");
    }
}
