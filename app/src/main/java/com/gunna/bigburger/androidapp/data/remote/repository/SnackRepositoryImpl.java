package com.gunna.bigburger.androidapp.data.remote.repository;

import com.gunna.bigburger.androidapp.data.remote.model.SnackResponse;
import com.gunna.bigburger.androidapp.data.remote.service.SnacksService;
import io.reactivex.Flowable;

import javax.inject.Inject;
import java.util.List;

public class SnackRepositoryImpl implements SnacksRepository {
    private final SnacksService mService;

    @Inject
    public SnackRepositoryImpl(SnacksService service) {
        this.mService = service;
    }

    @Override
    public Flowable<List<SnackResponse>> getSnacksList() {
        return null;
    }
}
