package com.metova.musixmatch;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by jodi on 8/4/17.
 */

public class RxBusUtil {

    private final PublishSubject<Object> RxBus = PublishSubject.create();

    public void send(Object o) {
        RxBus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return RxBus;
    }

    public boolean hasObservers() {
        return RxBus.hasObservers();
    }
}
