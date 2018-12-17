package com.gunna.bigburger.androidapp.data.remote.repository.menu;

import io.reactivex.Flowable;
import com.gunna.bigburger.androidapp.data.remote.model.SnackResponse;

import java.util.List;

public interface MenuListRepository {
    Flowable<List<SnackResponse>> getSnacksList();
}
