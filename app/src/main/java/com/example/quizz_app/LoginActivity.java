package com.example.quizz_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.quizz_app.Dao.UserDao;
import com.example.quizz_app.appDatabse.AppDatabase;
import com.example.quizz_app.entities.User;
import com.example.quizz_app.utils.Constants;
import com.example.quizz_app.utils.PreferencesUtils;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox stay_conected;
    private EditText loginEdit;
    private EditText passwordEdit;
    private AppDatabase database;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Room.databaseBuilder(this, AppDatabase.class, "app_user")
                .allowMainThreadQueries()
                .build();

        stay_conected=(CheckBox)findViewById(R.id.stay_conected);
        loginEdit = (EditText) findViewById(R.id.loginEditText);
        passwordEdit = (EditText) findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent( LoginActivity.this,CreateAccount.class));
            }
        });


        final String login = PreferencesUtils.getLogin();
        final String password=PreferencesUtils.getPassword();
        if(!TextUtils.isEmpty(login))
            loginEdit.setText(login);
        passwordEdit.setText(password);
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


        if((database.userDao().getUserByUsername(login).size()==0)){
            Toast.makeText(this, "Username or password false ", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        else {
            User compareUser=database.userDao().getUserByUsername(login).get(0);
            if(!(compareUser.getPassword().equals(password))){
                Toast.makeText(this, "Username or password false ", Toast.LENGTH_SHORT)
                        .show();
                return;
            }

        }

        if(stay_conected.isChecked()){

            PreferencesUtils.setLogin(login);
            PreferencesUtils.setPassword(password);

        }

        if(!(stay_conected.isChecked())){

            PreferencesUtils.setLogin("");
            PreferencesUtils.setPassword("");

        }

        startActivity(new Intent( LoginActivity.this,MainActivity.class));
    }

    private Intent getHomeIntent(String userName){
        final Intent homeIntent = new Intent(this, MainActivity.class);
        final Bundle extras = new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);
        homeIntent.putExtras(extras);
        return homeIntent;
    }
}




