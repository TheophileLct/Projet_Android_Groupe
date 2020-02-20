package com.example.quizz_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizz_app.Dao.UserDao;
import com.example.quizz_app.entities.User;

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

        if (TextUtils.isEmpty(passwordEdit.getText()) && (TextUtils.isEmpty(loginEdit.getText())))
        {
            Toast.makeText(this, "EmptyLogin and EmptyPassword", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        else {

            if (TextUtils.isEmpty(loginEdit.getText())) {
                Toast.makeText(this, "EmptyLogin", Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            if (TextUtils.isEmpty(passwordEdit.getText())) {
                Toast.makeText(this, "EmptyPassword", Toast.LENGTH_SHORT)
                        .show();
                return;
            }

        }
        String login = loginEdit.getText().toString();
        String password=passwordEdit.getText().toString();

        User compareUser=dao.getUserByUsername(login).get(0);
        if(compareUser!=null){
            Toast.makeText(this, "Username already use", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        User newUser = new User(0,login,password);
        dao.insertItem(newUser);
    }




}

