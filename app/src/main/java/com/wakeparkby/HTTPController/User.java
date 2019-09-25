package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class User {

    @Expose
    private String phone;
    @Expose
    private String password;

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
