package com.gunna.bigburger.androidapp.data.remote.service;

import com.gunna.bigburger.androidapp.app.Constants;
import com.gunna.bigburger.androidapp.data.remote.model.OfferResponse;
import io.reactivex.Flowable;
import retrofit2.http.GET;

import java.util.List;

public interface OffersService {

    @GET(Constants.Services.GET_OFFERS)
    Flowable<List<OfferResponse>> getOffers();

}
