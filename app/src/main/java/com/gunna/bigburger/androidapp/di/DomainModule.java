package com.gunna.bigburger.androidapp.di;

import com.gunna.bigburger.androidapp.data.remote.repository.SnacksRepository;
import com.gunna.bigburger.androidapp.domain.interactor.SnackListInteractor;
import com.gunna.bigburger.androidapp.domain.interactor.SnackListInteractorImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DomainModule {


    @Provides
    @Singleton
    SnackListInteractor provideSnackListInteractor(SnacksRepository repository) {
        return new SnackListInteractorImpl(repository);
    }


}
