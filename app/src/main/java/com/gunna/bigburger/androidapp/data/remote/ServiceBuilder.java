package com.gunna.bigburger.androidapp.data.remote;


import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gunna.bigburger.androidapp.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gunna on 17/04/2018.
 */
public class ServiceBuilder {
    private String mBaseUrl;
    private static ServiceBuilder sBuilderInstance;
    private List<CallAdapter.Factory> mCallAdapterFactories;
    private List<Converter.Factory> mConverterFactories;
    private Gson mGson;
    private int mConnectTimeout = 120;
    private int mReadTimeout = 120;
    private int mWriteTimeout = 120;
    private Interceptor mHeadersInterceptor;
    private boolean isUnsafeHttps;
    private boolean isEmptyUrl;

    public <I> I build(Class<I> serviceType) {
        if (TextUtils.isEmpty(mBaseUrl) && !isEmptyUrl)
            throw new RuntimeException("Api url cant be empty ! User .withUrl(baseUrl)");
        return createInstance(serviceType);
    }

    private ServiceBuilder() {
        init();
    }

    private void init() {
        mCallAdapterFactories = new ArrayList<>();
        mConverterFactories = new ArrayList<>();
        mHeadersInterceptor = null;
        mBaseUrl = "http://dummy.com";
        isUnsafeHttps = false;
    }

    public static ServiceBuilder getInstance() {
        if (sBuilderInstance == null)
            sBuilderInstance = new ServiceBuilder();
        sBuilderInstance.clearPreviousData();
        return sBuilderInstance;
    }

    private void clearPreviousData() {
        init();
    }


    private <I> I createInstance(Class<I> service) {
        final OkHttpClient client = getOkHttpClient();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(mGson == null ? gson : mGson))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create());
        return addConverters(addFactories(builder)).build().create(service);
    }

    private Retrofit.Builder addConverters(Retrofit.Builder builder) {
        for (Converter.Factory f : mConverterFactories) {
            if (!(f instanceof GsonConverterFactory))
                builder.addConverterFactory(f);
        }
        return builder;
    }

    private Retrofit.Builder addFactories(Retrofit.Builder builder) {
        for (CallAdapter.Factory t : mCallAdapterFactories) {
            if (!((t instanceof RxErrorHandlingCallAdapterFactory) ||
                    (t instanceof RxJava2CallAdapterFactory)))
                builder.addCallAdapterFactory(t);
        }
        return builder;
    }

    private void addHeaders(final OkHttpClient.Builder builder) {
        if (mHeadersInterceptor != null)
            builder.addInterceptor(mHeadersInterceptor);
        else
            Log.e("ServiceBuilder", "No headers interceptor attached!!");

    }

    private OkHttpClient getOkHttpClient() {
        final OkHttpClient.Builder builder =
                isUnsafeHttps ? UnsafeOkHttpClient.getUnsafeOkHttpClientBuilder()
                        : new OkHttpClient.Builder();

        builder.connectTimeout(mConnectTimeout, TimeUnit.SECONDS)
                .writeTimeout(mWriteTimeout, TimeUnit.SECONDS)
                .readTimeout(mReadTimeout, TimeUnit.SECONDS);

        addHeaders(builder);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logInterceptor);
        }
        return builder.build();
    }

    public ServiceBuilder withGsonInstance(Gson gson) {
        mGson = gson;
        return this;
    }

    public ServiceBuilder withFactory(CallAdapter.Factory factory) {
        mCallAdapterFactories.add(factory);
        return this;
    }

    public ServiceBuilder withConverter(Converter.Factory factory) {
        mConverterFactories.add(factory);
        return this;
    }

    public ServiceBuilder withConnectTimeout(int timeout) {
        this.mConnectTimeout = timeout;
        return this;
    }

    public ServiceBuilder withReadTimeout(int timeout) {
        this.mReadTimeout = timeout;
        return this;
    }

    public ServiceBuilder withWriteTimeout(int timeout) {
        this.mWriteTimeout = timeout;
        return this;
    }

    public ServiceBuilder withUrl(String url) {
        this.mBaseUrl = url;
        return this;
    }

    public ServiceBuilder withHeadersInterceptor(Interceptor interceptor) {
        mHeadersInterceptor = interceptor;
        return this;
    }

    public ServiceBuilder isUnsafeHttps(boolean bool) {
        isUnsafeHttps = bool;
        return this;
    }

    public ServiceBuilder isEmptyUrl(boolean bool) {
        isEmptyUrl = bool;
        return this;
    }
}