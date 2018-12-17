package com.gunna.bigburger.androidapp.di;

import com.gunna.bigburger.androidapp.data.remote.repository.MenuListRepository;
import com.gunna.bigburger.androidapp.domain.interactor.MenuListInteractor;
import com.gunna.bigburger.androidapp.domain.interactor.MenuListInteractorImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DomainModule {


    @Provides
    @Singleton
    MenuListInteractor provideSnackListInteractor(MenuListRepository repository) {
        return new MenuListInteractorImpl(repository);
    }


}
