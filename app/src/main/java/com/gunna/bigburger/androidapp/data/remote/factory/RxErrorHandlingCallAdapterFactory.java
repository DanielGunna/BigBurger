package com.gunna.bigburger.androidapp.data.remote.factory;

import com.gunna.bigburger.androidapp.data.remote.RetrofitException;
import io.reactivex.Flowable;

import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;
import retrofit2.*;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter<Object, Flowable<?>> {
        private final Retrofit retrofit;
        private final CallAdapter<?, ?> wrapped;

        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?, ?> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @Override
        public Flowable<?> adapt(Call call) {
            Flowable observable = (Flowable) wrapped.adapt(call);
            return observable
                    .onErrorResumeNext(new Function<Throwable, Publisher>() {
                        @Override
                        public Publisher apply(Throwable throwable) throws Exception {
                            return Flowable.error(throwable);
                        }
                    });
        }

        private RetrofitException asRetrofitException(Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException
                        .httpError(response.raw().request().url().toString(), response, retrofit);
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RetrofitException.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error

            return RetrofitException.unexpectedError(throwable);
        }
    }
}
