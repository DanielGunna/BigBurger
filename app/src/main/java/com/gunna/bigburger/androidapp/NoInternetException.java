package com.gunna.bigburger.androidapp;

class NoInternetException extends Throwable {
    public NoInternetException() {
        super("No internet connection!");
    }
}
