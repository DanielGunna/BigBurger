package com.gunna.bigburger.androidapp.domain.interactor.offers;

import com.gunna.bigburger.androidapp.data.Result;
import com.gunna.bigburger.androidapp.domain.model.Offer;
import io.reactivex.Flowable;

import java.util.List;

public interface OffersInteractor {
    Flowable<Result<List<Offer>>> getOffers();

}
