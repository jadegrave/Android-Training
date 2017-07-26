package com.metova.interactivestory.ui;

import com.metova.interactivestory.R;
import com.metova.interactivestory.model.Page;
import com.metova.interactivestory.model.Story;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private String name;
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView)findViewById(R.id.storyTextView);
        choice1Button = (Button)findViewById(R.id.choice1Button);
        choice2Button = (Button)findViewById(R.id.choice2Button);

        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.key_name));
        if (name == null || name.isEmpty()) {
            name = "Friend";
        }
        Log.d(TAG, name);

        story = new Story();
        loadPage(0);


    }

    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);  // pushes pageNumber onto Stack

        final Page page = story.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());
        // Add name if placeholder is included. Won't add if it's not there
        pageText = String.format(pageText, name);
        storyTextView.setText(pageText);

        if (page.isFinalPage()) {
                choice1Button.setVisibility(View.INVISIBLE);
                choice2Button.setText(R.string.play_again_button_text);
                choice2Button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //finish();  Call this when your activity is done and should be closed.
                        // The ActivityResult is propagated back to whoever launched you via onActivityResult().
                        loadPage(0);
                    }
                });

        } else {
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    // Whenever the back button is pressed, it calls the onBackPressed() method. We will override this
    // method so that we can navigate back through the pages.

    @Override
    public void onBackPressed() {
        pageStack.pop();            //pop the current page number
        if (pageStack.isEmpty()) {
            super.onBackPressed();  // this is the normal onBackPressed method; if no pages to pop, we go back to the main activity
        } else {
            loadPage(pageStack.pop());      // This pops off the current page, and passes that value into loadPage
                                            // Example: start at page 0, then go to page 1:
                                            // Current stack:       page 1
                                            //                      page 0
                                            // First call to pageStack.pop()  <--- Line 113
                                            // Current stack:       page 0
                                            // Since pageStack isn't Emmpty, we go to the else block:
                                            // Now, we execute the pageStack.pop() method
                                            // Current Stack:      (blank - empty)
                                            // The result of the pop is 0; that result is now passed into Loadpage
                                            // LoadPage loads page 0, and we go back to that page!

        }

    }
}
