package com.gunna.bigburger.androidapp.data.remote.service;

import com.gunna.bigburger.androidapp.app.Constants;
import com.gunna.bigburger.androidapp.data.remote.model.SnackResponse;
import io.reactivex.Flowable;
import retrofit2.http.GET;

import java.util.List;

public interface SnacksService {

    @GET(Constants.Services.GET_SNACKS)
    Flowable<List<SnackResponse>> getSnacks();
}
