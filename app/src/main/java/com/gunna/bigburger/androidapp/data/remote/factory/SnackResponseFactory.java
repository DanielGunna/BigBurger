package com.gunna.bigburger.androidapp.data.remote.factory;

import com.gunna.bigburger.androidapp.data.remote.model.SnackResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnackResponseFactory {

    public static List<SnackResponse> getSnacks(){
        List<SnackResponse> list = new ArrayList<>();
        list.add(getSnack());
        list.add(getSnack());
        list.add(getSnack());
        return list;
    }

    private static SnackResponse getSnack() {
        return new SnackResponse(
                1,
                "Snack",
                Arrays.asList(new  Integer[]{1,2,3}),
                "www.foo.com"
        );
    }
}
