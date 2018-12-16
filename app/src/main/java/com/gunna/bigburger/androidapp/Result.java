package com.gunna.bigburger.androidapp;


public class Result<T> {
    public Throwable error;
    public T data;
    public boolean isSuccess;

    private Result(Throwable throwable) {
        this.isSuccess = false;
        this.error = throwable;
    }

    private Result(T data) {
        this.isSuccess = true;
        this.data = data;
    }

    public static Result getError(Throwable throwable) {
        throwable.printStackTrace();
        return new Result(throwable);
    }

    public static <R> Result<R> getData(R data) {
        return new Result<>(data);
    }


    public static Result getNetworkError() {
        return new Result(new NoInternetException());
    }

    public boolean isNetworkError() {
        return error instanceof NoInternetException;
    }
}