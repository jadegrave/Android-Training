package com.metova.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FormatUrlActivity extends AppCompatActivity {

    private String mRawUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_url);

        Intent intent = getIntent();
        mRawUrl = intent.getStringExtra("urlText");

        if (mRawUrl == "") {
            mRawUrl = "Enter a Url";
            
        }


    }


}
