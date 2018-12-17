package com.gunna.bigburger.androidapp.domain.interactor;

import com.gunna.bigburger.androidapp.data.Result;
import com.gunna.bigburger.androidapp.domain.model.Snack;
import io.reactivex.Flowable;


import java.util.List;

public interface MenuListInteractor {
    Flowable<Result<List<Snack>>> getSnacks();
}
