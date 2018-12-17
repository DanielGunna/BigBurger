package com.gunna.bigburger.androidapp.domain.interactor.offers;

import com.gunna.bigburger.androidapp.data.Result;

import com.gunna.bigburger.androidapp.data.remote.repository.offers.OffersRepository;

import com.gunna.bigburger.androidapp.domain.mapper.OfferMapper;

import com.gunna.bigburger.androidapp.domain.model.Offer;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;
import java.util.List;

public class OffersInteractorImpl implements OffersInteractor {

    private OffersRepository mRepository;
    private Disposable mGetOffersSubscription;

    @Inject
    public OffersInteractorImpl(OffersRepository repository) {
        mRepository = repository;
    }

    @Override
    public Flowable<Result<List<Offer>>> getOffers() {
        return Flowable.create(this::getOffersFromRepository,
                BackpressureStrategy.BUFFER);
    }

    private void getOffersFromRepository(FlowableEmitter<Result<List<Offer>>> emitter) {
        mGetOffersSubscription = mRepository.getOffers()
                .flatMap(Flowable::fromIterable)
                .map(OfferMapper::geOfferFormatted)
                .toList()
                .subscribe(snacks -> emitter.onNext(Result.getData(snacks)), emitter::onError);
    }
}
