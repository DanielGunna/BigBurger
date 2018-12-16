package com.gunna.bigburger.androidapp.presentation.snacks;

import com.gunna.bigburger.androidapp.domain.interactor.SnackListInteractor;
import com.gunna.bigburger.androidapp.presentation.BaseViewModel;

import javax.inject.Inject;

public class SnackListViewModel extends BaseViewModel {

    private final SnackListInteractor mInteractor;

    @Inject
    public SnackListViewModel(SnackListInteractor interactor) {
        this.mInteractor = interactor;
    }


}
