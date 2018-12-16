package com.gunna.bigburger.androidapp.di;

import com.gunna.bigburger.androidapp.app.BigBurgerApplication;
import com.gunna.bigburger.androidapp.ui.BaseActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        DataModule.class,
        DomainModule.class,
        PresentationModule.class
})
public interface AppComponent {
    void inject(BaseActivity activity);

    void inject(BigBurgerApplication application);
}
