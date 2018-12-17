package com.gunna.bigburger.androidapp.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.gunna.bigburger.androidapp.R;
import com.gunna.bigburger.androidapp.app.BigBurgerApplication;
import com.gunna.bigburger.androidapp.app.RxJavaUtils;
import io.reactivex.disposables.CompositeDisposable;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    protected CompositeDisposable mSubscriptions = new CompositeDisposable();

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BigBurgerApplication.getInstance().getAppComponent().inject(this);
    }

    protected void setupToolbar(Toolbar toolbar) {
        setupToolbar(toolbar, false);
    }

    protected void setupToolbar(Toolbar toolbar, int title) {
        setupToolbar(toolbar, title, false);
    }

    protected void setupToolbar(Toolbar toolbar, int title, boolean isBack) {
        mToolbar = toolbar;
        if (mToolbar != null) {
            mToolbar.setTitle(title);
            setupToolbar(mToolbar, isBack);
        }
    }

    protected void setupToolbar(Toolbar toolbar, boolean isBack) {
        mToolbar = toolbar;
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.ic_back_18);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxJavaUtils.checkUnsubscribe(mSubscriptions);
    }
}
