package com.metova.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected static final String KEY = "urlText";
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
            public void onClick(View v) {
                String urlText = mUrlText.getText().toString();
                startFormatUrlActivity(urlText);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == URL_FORMAT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String url = data.getStringExtra(KEY);
                Log.v("Good string: ", url);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);

            } else {
                Toast.makeText(this, "Invalid URL. Try again.", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void startFormatUrlActivity(String urlText) {
        Intent intent = new Intent(this, FormatUrlActivity.class);
        intent.putExtra(KEY, urlText);
        startActivityForResult(intent, URL_FORMAT_REQUEST);

    }


}
