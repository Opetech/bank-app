package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "UserInformation";
    EditText email, password;
    Button loginSubmitBtn;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handleLoginAction();
    }

    private void handleLoginAction() {

        email = (EditText) findViewById(R.id.loginEmailAddress);
        password = (EditText) findViewById(R.id.loginPassword);
        loginSubmitBtn = (Button) findViewById(R.id.loginSubmitBtn);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        loginSubmitBtn.setOnClickListener(view -> {
            if (!isEmail(email)) {
                email.setError("Enter valid email!");
            } else if (isEmpty(password)) {
                password.setError("Password field cannot be empty");
            } else {
                String getEmail = sharedpreferences.getString("email", "");
                String getPassword = sharedpreferences.getString("password", "");
                if (getEmail.equals(email.getText().toString()) && getPassword.equals(password.getText().toString())) {
                    startActivity(new Intent(this, DashboardActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Login Details", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}

