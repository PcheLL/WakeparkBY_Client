package com.wakeparkby.Activity.CreateAccount;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.wakeparkby.R;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private Button buttonSave;

    @Override
    /**
     * базовый android метод для старта объекта интерфейса
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth = FirebaseAuth.getInstance();
        editTextName = findViewById(R.id.editTextNameCA);
        editTextPassword = findViewById(R.id.editTextPasswordCA);
        editTextPhone = findViewById(R.id.editTextNumberPhoneCA);
        buttonSave = findViewById(R.id.buttonSaveCA);
        buttonSave.setOnClickListener(this);
    }

    /**
     * метод для обработки нажатия
     *
     * @param view состояние нажатия
     */
    @Override
    public void onClick(View view) {

        if (editTextPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Введите Password !!!", Toast.LENGTH_SHORT).show();
        } else if (editTextPhone.getText().toString().equals("")) {
            Toast.makeText(this, "Введите номер !!!", Toast.LENGTH_SHORT).show();
        } else if (editTextName.getText().toString().equals("")) {
            Toast.makeText(this, "Введите Password !!!", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.buttonSaveCA) {
            String password = editTextPassword.getText().toString();
            String phoneNumber = editTextPhone.getText().toString();


                if(phoneNumber.isEmpty() || phoneNumber.length() < 10){
                    editTextPhone.setError("Неправильный номер телефона");
                    editTextPhone.requestFocus();
                    return;
                }

                Intent intent = new Intent(this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", phoneNumber);
                startActivity(intent);
            }


    }

    /**
     * егистрация в базе данных нового пользователя
     * @param email почта
     * @param password парроль
     */

    /*public void registration(final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //-----Отправка сообщения на Gmail о регистрации
                            mAuth.getCurrentUser().sendEmailVerification();
                            String userKey = mAuth.getCurrentUser().getUid();
                            ActivityCreateProfile activityCreateProfile = new ActivityCreateProfile();
                            activityCreateProfile.setUserKey(userKey);
                            startActivityCreateProfile();
                        } else {
                            Toast.makeText(ActivityCreateAccount.this, "Регистрация провалена", Toast.LENGTH_SHORT).show();
                        }


                        // ...

                    }

                });
    }*/

    /**
     * метод для старта создания профиля
     */
    private void startActivityCreateProfile() {
        //     AdapterCreateAccount.startActivityCreateProfile(this);
    }
}
