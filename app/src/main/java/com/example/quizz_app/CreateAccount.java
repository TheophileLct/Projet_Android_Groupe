package com.example.quizz_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizz_app.Dao.UserDao;

public class CreateAccount extends AppCompatActivity {
    private EditText loginEdit;
    private EditText passwordEdit;
    private UserDao dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginEdit = (EditText) findViewById(R.id.profilName);
        passwordEdit = (EditText) findViewById(R.id.passwordSet);
    }


    public void onClick(View v) {




    }




}

