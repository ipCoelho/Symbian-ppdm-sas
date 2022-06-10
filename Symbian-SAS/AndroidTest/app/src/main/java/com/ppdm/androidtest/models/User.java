package com.ppdm.androidtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name") @Expose
    private String name;

    @SerializedName("lastname") @Expose
    private String lastname;

    @SerializedName("email") @Expose
    private String email;

    @SerializedName("cel") @Expose
    private String cel;

    public User() {}

    public User(String name, String lastname, String email, String cel) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.cel = cel;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getCel() {
        return cel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }
}