package com.gunna.bigburger.androidapp.app;

import android.app.Application;
import com.gunna.bigburger.androidapp.di.AppComponent;
import com.gunna.bigburger.androidapp.di.DaggerAppComponent;

public class BigBurgerApplication extends Application {
    private final AppComponent mAppComponent = DaggerAppComponent.create();
    private static BigBurgerApplication sInstance;


    @Override
    public void onCreate() {
        mAppComponent.inject(this);
        sInstance = this;
        super.onCreate();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static BigBurgerApplication getInstance() {
        return sInstance;
    }
}
