package com.gunna.bigburger.androidapp.data.remote.repository.offers;

import com.gunna.bigburger.androidapp.data.remote.model.OfferResponse;
import io.reactivex.Flowable;

import java.util.List;

public interface OffersRepository {
    Flowable<List<OfferResponse>> getOffers();
}
