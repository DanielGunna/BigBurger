package com.gunna.bigburger.androidapp.data.remote.network;

import io.reactivex.Flowable;
import rx.functions.Action1;

public interface NetworkStatus {
   Flowable<Boolean> isOnline( );
}
