package com.wakeparkby.Controller;

import com.wakeparkby.Client.RetrofitClient;
import com.wakeparkby.HTTPController.NewUser;

public class CreateAccountController {
    private RetrofitClient retrofitClient = RetrofitClient.getRetrofitClient();

    public CreateAccountController(String numberPhone, String userName, String password) {
        retrofitClient.postCreateAccountUser(new NewUser(numberPhone, userName, password));
    }
}
