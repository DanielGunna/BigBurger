package com.gunna.bigburger.androidapp.data.remote.factory;

public interface ServiceFactory  {
    <T> T createService(Class<T> serviceClass);
}
