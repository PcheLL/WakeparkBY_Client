package com.wakeparkby.Activity.SignIn;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wakeparkby.Activity.CreateAccount.CreateAccountActivity;
import com.wakeparkby.Activity.MainMenu.MainMenuActivity;
import com.wakeparkby.Controller.SignInController;
import com.wakeparkby.Database.App;
import com.wakeparkby.Database.DataModel;
import com.wakeparkby.Database.DatabaseHelper;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseUser firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
    private TextView textViewNewAccount;
    private TextView textViewNewPassword;
    private EditText editTextNumberPhoneSI;
    private EditText editTextPasswordSI;
    private MaterialButton buttonEnter;
    private DatabaseHelper databaseHelper;
    private Observer observer = new Observer("SignIn") {

        /**
         * override method of Observer class with new reaction for notify observers
         */

        @Override
        public void update() {
            int n = observer.getStatus();
            if (n == 10) {
                if (observer.getId() == 8) {
                    signInAnswerTrue();
                    observer.setId(0);
                } else if (observer.getId() == 9) {
                    signInAnswerFalse();
                    observer.setId(0);
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textViewNewAccount = findViewById(R.id.textViewNewAccount);
        textViewNewPassword = findViewById(R.id.textViewNewPassword);
        editTextNumberPhoneSI = findViewById(R.id.editTextNumberPhoneSI);
        editTextPasswordSI = findViewById(R.id.editTextPasswordSI);
        buttonEnter = findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);
        textViewNewAccount.setOnClickListener(this);
        databaseHelper = App.getInstance().getDatabaseInstance();

        if (databaseHelper.getDataDao().getByTitle("UserToken").size() != 0) {
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
                SignInController signInController = new SignInController(editTextNumberPhoneSI.getText().toString(), editTextPasswordSI.getText().toString());
                break;
        }
        ;
    }

    public void signInAnswerTrue() {
        databaseHelper = App.getInstance().getDatabaseInstance();
        String token = databaseHelper.getDataDao().getByTitle("UserToken").get(0).getDescription().toString();
        Intent intent_MainMenu = new Intent(this, MainMenuActivity.class);
        startActivity(intent_MainMenu);
        editTextNumberPhoneSI.setText("");
        editTextPasswordSI.setText("");
    }

    private void signInAnswerFalse() {
        Toast.makeText(this, "Неправильный логин/пароль", Toast.LENGTH_SHORT).show();
    }
}
