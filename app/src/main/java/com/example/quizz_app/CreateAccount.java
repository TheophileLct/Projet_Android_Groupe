package com.example.quizz_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.quizz_app.Dao.UserDao;
import com.example.quizz_app.appDatabse.AppDatabase;
import com.example.quizz_app.entities.User;

import java.util.List;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {
    private EditText loginEdit;
    private EditText passwordEdit;
    private AppDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = Room.databaseBuilder(this, AppDatabase.class, "app_user")
                .allowMainThreadQueries()
                .build();

        setContentView(R.layout.activity_create);

        loginEdit = (EditText) findViewById(R.id.profilName);
        passwordEdit = (EditText) findViewById(R.id.passwordSet);

        findViewById(R.id.ButtonContinue).setOnClickListener(this);

    }

    @Override
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


        if(database.userDao().getUserByUsername(login).size()!=0){
            Toast.makeText(this, "Username already use", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        else {
            User newUser = new User(login,password);

            database.userDao().insertAll(newUser);
            startActivity(new Intent( CreateAccount.this, LoginActivity.class));
        }
    }

}

