package com.gunna.bigburger.androidapp.data.remote;


import android.util.Log;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;


import static com.gunna.bigburger.androidapp.data.remote.RetrofitException.Kind.HTTP;
import static com.gunna.bigburger.androidapp.data.remote.RetrofitException.Kind.NETWORK;
import static com.gunna.bigburger.androidapp.data.remote.RetrofitException.Kind.UNEXPECTED;

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

    public static boolean isNullOrUnsubscribed(Disposable subscription) {
        return subscription == null || subscription.isDisposed();
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

    private static boolean checkErrorRetrofit(Throwable error) {
        if (error instanceof RetrofitException) {
            RetrofitException exception = ((RetrofitException) error);
            return (exception.getKind() == HTTP && exception.getResponse().code() >= 500) ||
                    exception.getKind() == NETWORK || exception.getKind() == UNEXPECTED;
        }
        return false;

    }
}