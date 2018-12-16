package com.gunna.bigburger.androidapp.domain.interactor;

import com.gunna.bigburger.androidapp.data.remote.repository.SnacksRepository;
import com.gunna.bigburger.androidapp.domain.model.Snack;
import io.reactivex.Flowable;

import javax.inject.Inject;
import java.util.List;

public class SnackListInteractorImpl implements SnackListInteractor {

    private final SnacksRepository mRepository;

    @Inject
    public SnackListInteractorImpl(SnacksRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public Flowable<List<Snack>> getSnacks() {
        return null;
    }
}
