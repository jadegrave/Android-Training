package com.metova.musixmatch;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by jodi on 8/4/17.
 */

public class RxEventBus {

    private static RxEventBus instance;

    private final PublishSubject<Object> rxBus = PublishSubject.create();

    // Create single instance of event bus
    public static RxEventBus getInstance() {
        if (instance == null) {
            instance = new RxEventBus();
        }
        return instance;
    }

    // Observable calls this method when to emit event
    public void post (Object event) {
        rxBus.onNext(event);
    }

    //Subscriber/Consumer calls this
    public Observable<Object> toObservable() {
        return rxBus;
    }


}
