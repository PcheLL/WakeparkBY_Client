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
import com.wakeparkby.Controller.SignInController;
import com.wakeparkby.Database.App;
import com.wakeparkby.Database.DataModel;
import com.wakeparkby.Database.DatabaseHelper;
import com.wakeparkby.Observer.Observer;
import com.wakeparkby.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseUser firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
    TextView textViewNewAccount;
    TextView textViewNewPassword;
    EditText editTextNumberPhoneSI;
    EditText editTextPasswordSI;
    MaterialButton buttonEnter;
    private DatabaseHelper databaseHelper;
    Observer observer = new Observer("SignIn") {

        /**
         * override method of Observer class with new reaction for notify observers
         */

        @Override
        public void update() {
            int n = observer.getStatus();
            if (n == 10) {
                if (observer.getId() == 8) {
                    signInAnswer();
                    observer.setId(0);
                } else {
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


        /*if (!databaseHelper.getDataDao().getByTitle("UserToken").get(0).getDescription().toString().equals("-")) {
            Intent intent_MainMenu = new Intent(SignInActivity.this, MainMenuActivity.class);
            startActivity(intent_MainMenu);
        }*/
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
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
                //  Intent intent_MainMenu = new Intent(this,MainMenuActivity.class);
                //  startActivity(intent_MainMenu);
                SignInController signInController = new SignInController(editTextNumberPhoneSI.getText().toString(), editTextPasswordSI.getText().toString());
                break;
        }
        ;
    }

    public void signInAnswer() {
        databaseHelper = App.getInstance().getDatabaseInstance();
        String token = databaseHelper.getDataDao().getByTitle("UserToken").get(0).getDescription().toString();
        System.out.print(token);

    }
}
