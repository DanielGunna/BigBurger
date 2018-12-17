package com.gunna.bigburger.androidapp.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.gunna.bigburger.androidapp.R;
import com.gunna.bigburger.androidapp.app.BigBurgerApplication;
import com.gunna.bigburger.androidapp.app.RxJavaUtils;
import com.gunna.bigburger.androidapp.utils.DialogUtils;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class BaseFragment extends Fragment {
    protected CompositeDisposable mSubscriptions = new CompositeDisposable();

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BigBurgerApplication.getInstance().getAppComponent().inject(this);
    }

    protected void handleErrors(Throwable error) {
        error.printStackTrace();
        DialogUtils.getInfoDialogDismiss(
                getActivity(),
                R.string.error_title,
                R.string.unexpected_error
        ).show();
    }


    @Override
    public void onDestroy() {
        RxJavaUtils.checkUnsubscribe(mSubscriptions);
        super.onDestroy();
    }
}
