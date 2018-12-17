package com.gunna.bigburger.androidapp.domain.model;

public class Offer {
    private String name;
    private String description;

    public Offer(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public Offer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
