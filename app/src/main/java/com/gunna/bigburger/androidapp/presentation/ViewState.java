package com.gunna.bigburger.androidapp.presentation;

import static com.gunna.bigburger.androidapp.presentation.ViewStateEnum.ERROR;
import static com.gunna.bigburger.androidapp.presentation.ViewStateEnum.LOADING;
import static com.gunna.bigburger.androidapp.presentation.ViewStateEnum.SUCCESS;

public class ViewState<T> {

    public T data;
    public Throwable error;
    public ViewStateEnum status;


    public ViewState(T data, Throwable error, ViewStateEnum status) {
        this.data = data;
        this.error = error;
        this.status = status;
    }

    public static <R> ViewState<R> getSuccess(R data) {
        return new ViewState<>(data, null, SUCCESS);
    }

    public static <R> ViewState<R> getLoading(Class<R> clazz) {
        return new ViewState<>(null, null, LOADING);
    }

    public static <R> ViewState<R> getError(Throwable t, Class<R> clazz) {
        return new ViewState<>(null, t, ERROR);
    }

    public static <R> ViewState<R> getLoading() {
        return new ViewState<>(null, null, LOADING);
    }

    public static <R> ViewState<R> getError(Throwable t) {
        return new ViewState<>(null, t, ERROR);
    }


}
