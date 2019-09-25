package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class UserResponse {
    @Expose
    private String id;
    @Expose
    private String token;

}
