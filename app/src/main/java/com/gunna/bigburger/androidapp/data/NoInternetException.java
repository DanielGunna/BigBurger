package com.gunna.bigburger.androidapp.data;

class NoInternetException extends Throwable {
    public NoInternetException() {
        super("No internet connection!");
    }
}
