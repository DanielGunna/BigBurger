package com.gunna.bigburger.androidapp.presentation.offers;

import android.databinding.ObservableBoolean;
import com.gunna.bigburger.androidapp.data.Result;
import com.gunna.bigburger.androidapp.domain.interactor.offers.OffersInteractor;
import com.gunna.bigburger.androidapp.domain.model.Offer;
import com.gunna.bigburger.androidapp.presentation.BaseViewModel;
import com.gunna.bigburger.androidapp.presentation.ViewState;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

import javax.inject.Inject;
import java.util.List;

public class OffersViewModel extends BaseViewModel {

    private final PublishSubject<ViewState<List<Offer>>> mOfferListState = PublishSubject.create();
    private final OffersInteractor mInteractor;
    public final ObservableBoolean isEmpty = new ObservableBoolean(false);
    private Disposable mGetOffersSubscription;


    @Inject
    public OffersViewModel(OffersInteractor interactor) {
        mInteractor = interactor;
    }

    public void getOffersList() {
        mOfferListState.onNext(ViewState.getLoading());
        mGetOffersSubscription = mInteractor
                .getOffers()
                .subscribe(
                        this::onGetOffersListResult,
                        err -> mOfferListState.onNext(ViewState.getError(err)),
                        () -> mOfferListState.onNext(ViewState.getLoading())
                );
    }

    private void onGetOffersListResult(Result<List<Offer>> listResult) {
        if (listResult.data.isEmpty()) {
            isEmpty.set(true);
        } else {
            isEmpty.set(false);
            mOfferListState.onNext(ViewState.getSuccess(listResult.data));
        }
    }

    public Observable<ViewState<List<Offer>>> observeOffersListState() {
        return mOfferListState.hide();
    }
}
