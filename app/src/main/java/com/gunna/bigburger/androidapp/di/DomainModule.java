package com.gunna.bigburger.androidapp.di;

import com.gunna.bigburger.androidapp.data.remote.repository.menu.MenuListRepository;
import com.gunna.bigburger.androidapp.data.remote.repository.offers.OffersRepository;
import com.gunna.bigburger.androidapp.domain.interactor.menu.MenuListInteractor;
import com.gunna.bigburger.androidapp.domain.interactor.menu.MenuListInteractorImpl;
import com.gunna.bigburger.androidapp.domain.interactor.offers.OffersInteractor;
import com.gunna.bigburger.androidapp.domain.interactor.offers.OffersInteractorImpl;
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


    @Provides
    @Singleton
    OffersInteractor provideOffersInteractor(OffersRepository repository) {
        return new OffersInteractorImpl(repository);
    }

}
