package com.gunna.bigburger.androidapp.di;

import com.gunna.bigburger.androidapp.data.remote.factory.ServiceFactory;
import com.gunna.bigburger.androidapp.data.remote.factory.ServiceFactoryImpl;
import com.gunna.bigburger.androidapp.data.remote.network.NetworkStatus;
import com.gunna.bigburger.androidapp.data.remote.network.NetworkStatusImpl;
import com.gunna.bigburger.androidapp.data.remote.repository.MenuListRepositoryImpl;
import com.gunna.bigburger.androidapp.data.remote.repository.MenuListRepository;
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
    NetworkStatus providesNetworkStatus() {
        return new NetworkStatusImpl();
    }


}
