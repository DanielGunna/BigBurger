package com.gunna.bigburger.androidapp.data.remote.repository.menu;

import android.accounts.NetworkErrorException;
import com.gunna.bigburger.androidapp.data.remote.model.SnackResponse;
import com.gunna.bigburger.androidapp.data.remote.network.NetworkStatus;
import com.gunna.bigburger.androidapp.data.remote.service.SnacksService;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;


import javax.inject.Inject;
import java.util.List;

public class MenuListRepositoryImpl implements MenuListRepository {
    private final SnacksService mService;
    private final NetworkStatus mNetworkStatus;
    private final Scheduler mMainThreadScheduler;
    private Scheduler mIoScheduler;

    @Inject
    public MenuListRepositoryImpl(SnacksService service,
                                  NetworkStatus networkStatus,
                                  Scheduler ioScheduler,
                                  Scheduler mainScheduler) {
        this.mService = service;
        this.mNetworkStatus = networkStatus;
        this.mMainThreadScheduler = mainScheduler;
        this.mIoScheduler = ioScheduler;
    }

    @Override
    public Flowable<List<SnackResponse>> getSnacksList() {
        return mNetworkStatus.isOnline()
                .observeOn(mMainThreadScheduler)
                .flatMap(isOnline -> {
                            if (isOnline) {
                                return mService.getSnacks()
                                        .subscribeOn(mIoScheduler)
                                        .observeOn(mMainThreadScheduler);
                            } else {
                                return Flowable.error(new NetworkErrorException());
                            }
                        }
                );
    }
}
