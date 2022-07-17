package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText email, firstname, lastname, phone, password;
    Button signupSubmitBtn;
    public static final String MyPREFERENCES = "UserInformation";
    public static final String firstnameKey = "firstname";
    public static final String lastnameKey = "lastname";
    public static final String passwordKey = "password";
    public static final String phoneKey = "phone";
    public static final String emailKey = "email";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        handleLoginAction();
    }

    private void handleLoginAction() {

        email = (EditText) findViewById(R.id.emailAddress);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.signupPassword);
        signupSubmitBtn = (Button) findViewById(R.id.signupSubmitBtn);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        signupSubmitBtn.setOnClickListener(view -> {
            if (!isEmail(email)) {
                email.setError("Enter valid email!");
            } else if (isEmpty(firstname)) {
                firstname.setError("Firstname is required");
            } else if (isEmpty(lastname)) {
                lastname.setError("Lastname is required");
            } else if (isEmpty(phone)) {
                phone.setError("Phone number is required");
            } else if (isEmpty(password)) {
                password.setError("Password  is required");
            } else {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(firstnameKey, firstname.getText().toString());
                editor.putString(lastnameKey, lastname.getText().toString());
                editor.putString(emailKey, email.getText().toString());
                editor.putString(firstnameKey, firstname.getText().toString());
                editor.putString(phoneKey, phone.getText().toString());
                editor.putString(passwordKey, password.getText().toString());
                editor.apply();
                Toast.makeText(RegisterActivity.this, "Information saved successfully", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                    startActivity(new Intent(this, LoginActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
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