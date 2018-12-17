package com.gunna.bigburger.androidapp.presentation.orders;

import android.databinding.ObservableBoolean;
import com.gunna.bigburger.androidapp.presentation.BaseViewModel;

public class OrdersViewModel extends BaseViewModel {
    public final ObservableBoolean isEmpty = new ObservableBoolean(false);
}
