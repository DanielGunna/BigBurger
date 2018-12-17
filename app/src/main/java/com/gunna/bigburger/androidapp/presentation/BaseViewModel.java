package com.gunna.bigburger.androidapp.presentation;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;


public class BaseViewModel extends ViewModel {
    public final ObservableBoolean isLoading = new ObservableBoolean(false);

}
