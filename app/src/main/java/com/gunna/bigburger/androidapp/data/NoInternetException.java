package com.gunna.bigburger.androidapp.data;

public class NoInternetException extends Throwable {
    public NoInternetException() {
        super("No internet connection!");
    }
}
