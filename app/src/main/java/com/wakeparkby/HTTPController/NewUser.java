package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class NewUser {
    @Expose
    String phone;
    @Expose
    String userName;
    @Expose
    String password;

    public NewUser(String phone, String userName, String password) {
        this.phone = phone;
        this.userName = userName;
        this.password = password;
    }
}
