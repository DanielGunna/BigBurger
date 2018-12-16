package com.gunna.bigburger.androidapp.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.gunna.bigburger.androidapp.app.BigBurgerApplication;
import com.gunna.bigburger.androidapp.data.remote.RxJavaUtils;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    protected CompositeDisposable mSubscriptions = new CompositeDisposable();

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BigBurgerApplication.getInstance().getAppComponent().inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxJavaUtils.checkUnsubscribe(mSubscriptions);
    }
}
