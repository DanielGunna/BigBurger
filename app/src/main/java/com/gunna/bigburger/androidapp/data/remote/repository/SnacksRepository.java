package com.gunna.bigburger.androidapp.data.remote.repository;

import io.reactivex.Flowable;
import com.gunna.bigburger.androidapp.data.remote.model.SnackResponse;

import java.util.List;

public interface SnacksRepository {
    Flowable<List<SnackResponse>> getSnacksList();
}
