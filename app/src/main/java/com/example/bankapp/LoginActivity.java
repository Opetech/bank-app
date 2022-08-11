package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankapp.model.User;
import com.example.bankapp.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LoginActivity.this;
    EditText email, password;
    Button loginSubmitBtn;
    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViewVariables();
        initObjects();
        handleLoginAction();
    }

    private void initViewVariables() {
        email = (EditText) findViewById(R.id.loginEmailAddress);
        password = (EditText) findViewById(R.id.loginPassword);
        loginSubmitBtn = (Button) findViewById(R.id.loginSubmitBtn);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    private void handleLoginAction() {

        loginSubmitBtn.setOnClickListener(view -> {
            if (!isEmail(email)) {
                email.setError("Enter valid email!");
            } else if (isEmpty(password)) {
                password.setError("Password field cannot be empty");
            } else {
                try {
                    Object user = databaseHelper.checkUser(email.getText().toString(), password.getText().toString());
                    if (user instanceof User) {
                        User userDetails = (User) user;
                        Intent intent = new Intent(this, DashboardActivity.class);
                        intent.putExtra("firstname", userDetails.getFirstname());
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Login Details", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
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

