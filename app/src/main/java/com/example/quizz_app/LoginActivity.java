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
import com.example.quizz_app.utils.Constants;
import com.example.quizz_app.utils.PreferencesUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox stay_conected;
    private EditText loginEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stay_conected=(CheckBox)findViewById(R.id.stay_conected);

        loginEdit = (EditText) findViewById(R.id.loginEditText);
        passwordEdit = (EditText) findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(this);

        final String login = PreferencesUtils.getLogin();
        final String password=PreferencesUtils.getPassword();
        if(!TextUtils.isEmpty(login))
            loginEdit.setText(login);
            passwordEdit.setText(password);
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

        String login = loginEdit.getText().toString();
        String password=passwordEdit.getText().toString();

        if(stay_conected.isChecked()){

            PreferencesUtils.setLogin(login);
            PreferencesUtils.setPassword(password);

        }

        startActivity(getHomeIntent(login));
    }

    private Intent getHomeIntent(String userName){
        final Intent homeIntent = new Intent(this, MenuPrincipal.class);
        final Bundle extras = new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);
        homeIntent.putExtras(extras);
        return homeIntent;
    }

}

