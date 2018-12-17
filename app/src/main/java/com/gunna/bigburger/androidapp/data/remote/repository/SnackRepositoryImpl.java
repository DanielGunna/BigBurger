package com.gunna.bigburger.androidapp.data.remote.repository;

import android.accounts.NetworkErrorException;
import com.gunna.bigburger.androidapp.app.RxJavaUtils;
import com.gunna.bigburger.androidapp.data.remote.model.SnackResponse;
import com.gunna.bigburger.androidapp.data.remote.network.NetworkStatus;
import com.gunna.bigburger.androidapp.data.remote.service.SnacksService;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import javax.inject.Inject;
import java.util.List;

public class SnackRepositoryImpl implements MenuListRepository {
    private final SnacksService mService;
    private final NetworkStatus mNetworkStatus;

    @Inject
    public SnackRepositoryImpl(SnacksService service, NetworkStatus networkStatus) {
        this.mService = service;
        this.mNetworkStatus = networkStatus;
    }

    @Override
    public Flowable<List<SnackResponse>> getSnacksList() {
        return mNetworkStatus.isOnline()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(isOnline -> {
                            if (isOnline) {
                                return mService.getSnacks()
                                        .compose(RxJavaUtils.applySchedulers(false));
                            } else {
                                return Flowable.error(new NetworkErrorException());
                            }
                        }
                );
    }
}
