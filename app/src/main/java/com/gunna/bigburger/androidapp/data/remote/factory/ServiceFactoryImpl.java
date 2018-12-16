package com.gunna.bigburger.androidapp.data.remote.factory;


import com.google.gson.Gson;
import com.gunna.bigburger.androidapp.BuildConfig;
import com.gunna.bigburger.androidapp.data.remote.ServiceBuilder;


public class ServiceFactoryImpl implements ServiceFactory {

    @Override
    public <T> T createService(Class<T> clazz) {
        return ServiceBuilder.getInstance()
                .withUrl(BuildConfig.BASE_URL)
                .isUnsafeHttps(true)
                .withWriteTimeout(30)
                .withGsonInstance(new Gson())
                .withReadTimeout(30)
                .withConnectTimeout(30)
                .build(clazz);
    }


}
