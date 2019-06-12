package com.wakeparkby.Activity.SignIn;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wakeparkby.Activity.CreateAccount.CreateAccountActivity;
import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextMobile;
    private FirebaseUser firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
    TextView textViewNewAccount;
    TextView textViewNewPassword;
    MaterialButton buttonEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textViewNewAccount = findViewById(R.id.textViewNewAccount);
        textViewNewPassword = findViewById(R.id.textViewNewPassword);
       // editTextMobile = findViewById(R.id.editTextMobile);
        buttonEnter = findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);
        textViewNewAccount.setOnClickListener(this);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent_MainMenu = new Intent(SignInActivity.this, MainMenuActivity.class);
            startActivity(intent_MainMenu);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewNewAccount:
                Intent intent_CreateAccount = new Intent(this, CreateAccountActivity.class);
                startActivity(intent_CreateAccount);
                break;
            case R.id.buttonEnter:
                Intent intent_MainMenu = new Intent(this,MainMenuActivity.class);
                startActivity(intent_MainMenu);
                break;
        }
        ;
    }
}
