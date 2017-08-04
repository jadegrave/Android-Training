package com.metova.musixmatch;

import android.view.View;

/**
 * Created by jodi on 8/4/17.
 */

public class EventCardClick {

    private View view;

    public EventCardClick(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

}
