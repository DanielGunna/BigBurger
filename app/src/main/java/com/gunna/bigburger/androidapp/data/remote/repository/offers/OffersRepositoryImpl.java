package com.gunna.bigburger.androidapp.data.remote.repository.offers;

import android.accounts.NetworkErrorException;
import com.gunna.bigburger.androidapp.data.remote.model.OfferResponse;
import com.gunna.bigburger.androidapp.data.remote.network.NetworkStatus;
import com.gunna.bigburger.androidapp.data.remote.service.OffersService;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;

import javax.inject.Inject;
import java.util.List;

public class OffersRepositoryImpl implements OffersRepository {

    private final OffersService mService;
    private final Scheduler mMainThreadScheduler;
    private final Scheduler mIoScheduler;
    private final NetworkStatus mNetworkStatus;

    @Inject
    public OffersRepositoryImpl(OffersService service,
                                NetworkStatus networkStatus,
                                Scheduler ioScheduler,
                                Scheduler mainScheduler) {
        this.mService = service;
        this.mNetworkStatus = networkStatus;
        this.mMainThreadScheduler = mainScheduler;
        this.mIoScheduler = ioScheduler;
    }


    @Override
    public Flowable<List<OfferResponse>> getOffers() {
        return mNetworkStatus.isOnline()
                .observeOn(mMainThreadScheduler)
                .flatMap(isOnline -> {
                            if (isOnline) {
                                return mService.getOffers()
                                        .subscribeOn(mIoScheduler)
                                        .observeOn(mMainThreadScheduler);
                            } else {
                                return Flowable.error(new NetworkErrorException());
                            }
                        }
                );
    }
}
