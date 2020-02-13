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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox stay_conected;
    private EditText loginEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stay_conected=(CheckBox)findViewById(R.id.stay_conected);

        loginEdit = (EditText) findViewById(R.id.loginEditText);
        passwordEdit = (EditText) findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(this);

        myPreferences = getPreferences(MODE_PRIVATE);
        editor=myPreferences.edit();

        checkPreferences();
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
        preferencesUtils.setLogin(login);
        startActivity(getHomeIntent(login));
    }

    private void checkPreferences(){
        EditText usernameEditText = findViewById(R.id.loginEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        String checkbox=myPreferences.getString(getString(R.string.checkboxPref),"remember me");
        String username=myPreferences.getString(getString(R.string.usernamePref),"");
        String password=myPreferences.getString(getString(R.string.passwordPref),"");

        usernameEditText.setText(username);
        passwordEditText.setText(password);
        if(checkbox.equals("True")){
            stay_conected.setText("True");
        }
        else{
            stay_conected.setText("remember me");
        }
    }

    private Intent getHomeIntent(String userName){
        final Intent homeIntent = new Intent(this, MainActivity.class);
        final Bundle extras = new Bundle();
        extras.putString(SyncStateContract.Constants.Login.EXTRA_LOGIN, userName);
        homeIntent.putExtras(extras);
        return homeIntent;
    }

}

