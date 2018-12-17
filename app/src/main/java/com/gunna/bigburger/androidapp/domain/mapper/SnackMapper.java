package com.gunna.bigburger.androidapp.domain.mapper;

import com.gunna.bigburger.androidapp.data.remote.model.SnackResponse;
import com.gunna.bigburger.androidapp.domain.model.Snack;

import java.util.ArrayList;
import java.util.List;


public class SnackMapper {

    public static Snack getSnackFormatted(SnackResponse snackResponse) {
        Snack snack = new Snack();
        if (snackResponse != null) {
            snack.setName(snackResponse.getName());
            snack.setImageUrl(snackResponse.getImage());
            snack.setDescription(snackResponse.getDescription());
        }
        return snack;
    }

    public static List<Snack> getListSnackFormatted(List<SnackResponse> snacks) {
        List<Snack> list = new ArrayList<>();
        for (SnackResponse s : snacks)
            list.add(getSnackFormatted(s));
        return list;
    }
}
