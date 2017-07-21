package com.metova.assignment1;

import android.content.Intent;
//import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    protected static final String key = "urlText";
    protected static final int URL_FORMAT_REQUEST = 1;   // The request code
    private EditText mUrlText;
    private Button mCheckUrlButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUrlText = (EditText) findViewById(R.id.urlEditText);
        mCheckUrlButton = (Button) findViewById(R.id.checkUrlButton);

        mCheckUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String urlText = mUrlText.getText().toString();
                checkUrl(urlText);

            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }


    private void checkUrl (String urlText) {
        Intent intent = new Intent(this,  FormatUrlActivity.class);
        intent.putExtra(key, urlText);
        startActivity(intent);
    }




}
