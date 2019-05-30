package com.ahmedteleb.soleek_task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("name")
    @Expose
    private String name;

    public String getCountryName() {
        return name;
    }

    public void setCountryName(String name) {
        this.name = name;
    }

}