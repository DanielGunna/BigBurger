package com.gunna.bigburger.androidapp.domain.mapper;

import com.gunna.bigburger.androidapp.data.remote.model.OfferResponse;
import com.gunna.bigburger.androidapp.domain.model.Offer;

public class OfferMapper {
    public static Offer geOfferFormatted(OfferResponse offerResponse) {
        Offer offer = new Offer();
        if (offerResponse != null) {
            offer.setDescription(offerResponse.getDescription());
            offer.setName(offerResponse.getName());
        }
        return offer;
    }
}
