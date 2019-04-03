package com.wakeparkby.Activity.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.R;

public class SignInActivity extends AppCompatActivity {
    private EditText editTextMobile;
    private FirebaseUser firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        editTextMobile = findViewById(R.id.editTextMobile);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent_MainMenu = new Intent(SignInActivity.this, MainMenuActivity.class);
            startActivity(intent_MainMenu);
        }

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = editTextMobile.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }

                Intent intent = new Intent(SignInActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }
        });
    }
}
