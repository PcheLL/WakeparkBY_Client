package com.wakeparkby.Controller;

import com.wakeparkby.Client.RetrofitClient;
import com.wakeparkby.HTTPController.User;

public class SignInController {
    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();
    public SignInController(String phone, String password) {
        retrofitClient.postSignInUser(new User(phone,password));
    }
}
