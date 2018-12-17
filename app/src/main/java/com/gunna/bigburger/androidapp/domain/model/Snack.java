package com.gunna.bigburger.androidapp.domain.model;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class Snack {
    private String name;
    private String imageUrl;

    public Snack() {

    }

    public Snack(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
