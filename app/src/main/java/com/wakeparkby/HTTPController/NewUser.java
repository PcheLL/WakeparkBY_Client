package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class NewUser {
    @Expose
    private String phone;
    @Expose
    private String userName;
    @Expose
    private String password;

    public NewUser(String phone, String userName, String password) {
        this.phone = phone;
        this.userName = userName;
        this.password = password;
    }
}
