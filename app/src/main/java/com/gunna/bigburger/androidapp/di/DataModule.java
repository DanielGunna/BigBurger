package com.gunna.bigburger.androidapp.di;

import com.gunna.bigburger.androidapp.data.remote.factory.ServiceFactory;
import com.gunna.bigburger.androidapp.data.remote.factory.ServiceFactoryImpl;
import com.gunna.bigburger.androidapp.data.remote.network.NetworkStatus;
import com.gunna.bigburger.androidapp.data.remote.network.NetworkStatusImpl;
import com.gunna.bigburger.androidapp.data.remote.repository.menu.MenuListRepositoryImpl;
import com.gunna.bigburger.androidapp.data.remote.repository.menu.MenuListRepository;
import com.gunna.bigburger.androidapp.data.remote.repository.offers.OffersRepository;
import com.gunna.bigburger.androidapp.data.remote.repository.offers.OffersRepositoryImpl;
import com.gunna.bigburger.androidapp.data.remote.service.OffersService;
import com.gunna.bigburger.androidapp.data.remote.service.SnacksService;
import dagger.Module;
import dagger.Provides;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Singleton;

@Module
public class DataModule {

    @Singleton
    @Provides
    ServiceFactory providesServiceFactory() {
        return new ServiceFactoryImpl();
    }

    @Singleton
    @Provides
    SnacksService providesSnackService(ServiceFactory factory) {
        return factory.createService(SnacksService.class);
    }


    @Singleton
    @Provides
    OffersService providesOffersService(ServiceFactory factory) {
        return factory.createService(OffersService.class);
    }


    @Singleton
    @Provides
    MenuListRepository providesSnackRepository(SnacksService service, NetworkStatus networkStatus) {
        return new MenuListRepositoryImpl(
                service,
                networkStatus,
                Schedulers.io(),
                AndroidSchedulers.mainThread()
        );

    }

    @Singleton
    @Provides
    OffersRepository providesOffersRepository(OffersService service, NetworkStatus networkStatus) {
        return new OffersRepositoryImpl(
                service,
                networkStatus,
                Schedulers.io(),
                AndroidSchedulers.mainThread()
        );

    }


    @Singleton
    @Provides
    NetworkStatus providesNetworkStatus() {
        return new NetworkStatusImpl();
    }


}
