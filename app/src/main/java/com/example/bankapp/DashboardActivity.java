package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class DashboardActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "UserInformation";
    private TextView username, balance, accountNumber;
    private Random random = new Random();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initializeViewVariables();
        updateUserInfo();
    }

    private void initializeViewVariables() {
        username = (TextView) findViewById(R.id.username);
        balance = (TextView) findViewById(R.id.balance);
        accountNumber = (TextView) findViewById(R.id.accountNumber);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    private void updateUserInfo() {
        String firstname = getIntent().getStringExtra("firstname");
        username.setText("Hi, "+ firstname);

    }
}