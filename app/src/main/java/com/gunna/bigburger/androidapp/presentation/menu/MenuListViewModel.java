package com.gunna.bigburger.androidapp.presentation.menu;


import com.gunna.bigburger.androidapp.domain.interactor.menu.MenuListInteractor;
import com.gunna.bigburger.androidapp.domain.model.Snack;
import com.gunna.bigburger.androidapp.presentation.BaseViewModel;
import com.gunna.bigburger.androidapp.presentation.ViewState;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

import javax.inject.Inject;
import java.util.List;


public class MenuListViewModel extends BaseViewModel {

    private final PublishSubject<ViewState<List<Snack>>> mSnackListState = PublishSubject.create();

    private final MenuListInteractor mInteractor;
    private Disposable mGetSnackSubscription;


    public Observable<ViewState<List<Snack>>> observeSnackListState() {
        return mSnackListState.hide();
    }

    @Inject
    public MenuListViewModel(MenuListInteractor interactor) {
        this.mInteractor = interactor;
    }


    public void getSnacksList() {
        mSnackListState.onNext(ViewState.getLoading());
        mGetSnackSubscription = mInteractor
                .getSnacks()
                .subscribe(
                        result -> mSnackListState.onNext(ViewState.getSuccess(result.data)),
                        err -> mSnackListState.onNext(ViewState.getError(err)),
                        () -> mSnackListState.onNext(ViewState.getLoading())
                );
    }




}
