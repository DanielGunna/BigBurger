package com.gunna.bigburger.androidapp.domain.interactor;

import com.gunna.bigburger.androidapp.domain.model.Snack;
import io.reactivex.Flowable;


import java.util.List;

public interface SnackListInteractor {
    Flowable<List<Snack>> getSnacks();
}
