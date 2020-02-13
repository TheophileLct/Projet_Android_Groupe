package com.example.quizz_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEdit = (EditText) findViewById(R.id.loginEditText);
        passwordEdit = (EditText) findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (TextUtils.isEmpty(loginEdit.getText()) && !(TextUtils.isEmpty(passwordEdit.getText())))
        {
            Toast.makeText(this, "EmptyLogin", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(passwordEdit.getText()) && !(TextUtils.isEmpty(loginEdit.getText())))
            {
                Toast.makeText(this, "EmptyPassword", Toast.LENGTH_SHORT)
                        .show();
                return;
            }

        if (TextUtils.isEmpty(passwordEdit.getText()) && (TextUtils.isEmpty(loginEdit.getText())))
        {
            Toast.makeText(this, "EmptyLogin and EmptyPassword", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        PreferenceUtils.setLogin(login);
        startActivity(getHomeIntent(login));
    }

}

