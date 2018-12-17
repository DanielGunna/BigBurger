package com.gunna.bigburger.androidapp.domain.mapper;

import com.gunna.bigburger.androidapp.data.remote.model.SnackResponse;
import com.gunna.bigburger.androidapp.domain.model.Snack;


public class SnackMapper {

    public static Snack getSnackFormatted(SnackResponse snackResponse) {
        Snack snack = new Snack();
        if (snackResponse != null) {
            snack.setName(snackResponse.getName());
            snack.setImageUrl(snackResponse.getImage());
        }
        return snack;
    }
}
