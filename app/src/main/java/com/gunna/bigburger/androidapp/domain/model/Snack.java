package com.gunna.bigburger.androidapp.domain.model;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class Snack {
    private String name;
    private String imageUrl;
    private String description;

    public Snack() {

    }

    public Snack(String name, String imageUrl, String desc) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = desc;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
