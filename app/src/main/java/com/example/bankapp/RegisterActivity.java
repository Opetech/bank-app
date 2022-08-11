package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankapp.model.User;
import com.example.bankapp.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    private final AppCompatActivity activity = RegisterActivity.this;
    EditText email, firstname, lastname, phone, password;
    Button signupSubmitBtn;
    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViewVariables();
        initObjects();
        handleRegisterAction();
    }

    private void initViewVariables() {
        email = (EditText) findViewById(R.id.emailAddress);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.signupPassword);
        signupSubmitBtn = (Button) findViewById(R.id.signupSubmitBtn);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        databaseHelper.getWritableDatabase();
    }

    private void handleRegisterAction() {
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
                password.setError("Password is required");
            } else {
                if (databaseHelper.checkEmailExist(email.getText().toString())){
                    Toast.makeText(activity, "Email Already exist", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        user = new User(firstname.getText().toString(), lastname.getText().toString(), phone.getText().toString(), email.getText().toString(), password.getText().toString());
                        databaseHelper.addUser(user);
                        Toast.makeText(activity, "Information saved successfully", Toast.LENGTH_SHORT).show();
                        try {
                            Thread.sleep(1000);
                            startActivity(new Intent(this, LoginActivity.class));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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