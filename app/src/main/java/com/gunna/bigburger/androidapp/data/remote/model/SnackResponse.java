package com.gunna.bigburger.androidapp.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SnackResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<Integer> ingredients;
    @SerializedName("image")
    @Expose
    private String image;

    public SnackResponse(Integer id, String name, List<Integer> ingredients, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.image = image;
    }

    public SnackResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
