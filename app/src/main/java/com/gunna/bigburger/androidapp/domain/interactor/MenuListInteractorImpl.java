package com.gunna.bigburger.androidapp.domain.interactor;

import com.gunna.bigburger.androidapp.data.Result;
import com.gunna.bigburger.androidapp.data.remote.repository.MenuListRepository;

import com.gunna.bigburger.androidapp.domain.mapper.SnackMapper;
import com.gunna.bigburger.androidapp.domain.model.Snack;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.disposables.Disposable;


import javax.inject.Inject;
import java.util.List;

public class MenuListInteractorImpl implements MenuListInteractor {

    private final MenuListRepository mRepository;
    private Disposable mGetSnacksSubscription;

    @Inject
    public MenuListInteractorImpl(MenuListRepository repository) {
        this.mRepository = repository;
    }


    @Override
    public Flowable<Result<List<Snack>>> getSnacks() {
        return Flowable.create(this::getSnacksFromRepository,
                BackpressureStrategy.BUFFER);

    }

    private void getSnacksFromRepository(FlowableEmitter<Result<List<Snack>>> emitter) {
        mGetSnacksSubscription = mRepository.getSnacksList()
                .flatMap(Flowable::fromIterable)
                .map(SnackMapper::getSnackFormatted)
                .toList()
                .subscribe(snacks -> emitter.onNext(Result.getData(snacks)), emitter::onError);
    }
}
