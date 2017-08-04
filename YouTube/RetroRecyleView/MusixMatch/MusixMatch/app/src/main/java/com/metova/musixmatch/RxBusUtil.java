package com.metova.musixmatch;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by jodi on 8/4/17.
 */

public class RxBusUtil {

    private static RxBusUtil instance;
    private final PublishSubject<Object> RxBus = PublishSubject.create();

    public static RxBusUtil instanceOf() {
        if (instance == null) {
            instance = new RxBusUtil();
        }
        return instance;
    }


    public void post(Object o) {
        RxBus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return RxBus;
    }

    public boolean hasObservers() {
        return RxBus.hasObservers();
    }
}
