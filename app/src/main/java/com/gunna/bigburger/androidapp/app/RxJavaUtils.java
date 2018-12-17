package com.gunna.bigburger.androidapp.app;


import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;


public class RxJavaUtils {
    private static final int COUNTER_START = 1;
    private static final int ATTEMPTS = 5;

    public static void checkUnsubscribe(Disposable subscription) {
        if (subscription != null && !subscription.isDisposed())
            subscription.dispose();
    }

    public static void checkUnsubscribe(CompositeDisposable subscription) {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
            subscription.clear();
        }
    }

    public static boolean hasSubscriptions(Disposable subscription) {
        return subscription == null || subscription.isDisposed();
    }

    public static boolean hasSubscriptions(CompositeDisposable subscription) {
        return subscription.size() > 0;
    }

    public static <T> FlowableTransformer<T, T> applySchedulers(boolean retry) {
        return !retry ? observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) :
                observable -> observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .retryWhen(errorObservable -> {
                            return errorObservable.zipWith(Flowable.range(COUNTER_START, ATTEMPTS),
                                    (BiFunction<Throwable, Integer, Integer>) (throwable, attempt) -> {
                                        throwable.printStackTrace();
                                        Log.v("RxJavaUtils",
                                                String.format("Retrying request  with error, attempt (%d/%d)", attempt, ATTEMPTS));
                                        if (attempt == ATTEMPTS)
                                            throw new RuntimeException(throwable);
                                        return attempt;
                                    });
                        });
    }

}