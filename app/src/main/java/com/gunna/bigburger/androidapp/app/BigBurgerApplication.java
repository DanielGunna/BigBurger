package com.gunna.bigburger.androidapp.app;

import android.app.Application;
import com.gunna.bigburger.androidapp.R;
import com.gunna.bigburger.androidapp.di.AppComponent;
import com.gunna.bigburger.androidapp.di.DaggerAppComponent;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BigBurgerApplication extends Application {
    private final AppComponent mAppComponent = DaggerAppComponent.create();
    private static BigBurgerApplication sInstance;


    @Override
    public void onCreate() {
        mAppComponent.inject(this);
        sInstance = this;
        initCaligraphy();
        super.onCreate();
    }

    private void initCaligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Product_Sans_Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static BigBurgerApplication getInstance() {
        return sInstance;
    }
}
