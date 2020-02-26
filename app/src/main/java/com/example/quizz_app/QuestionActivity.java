package com.example.quizz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;

public class QuestionActivity extends AppCompatActivity {

    protected CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //getFragmentManager().beginTransaction().add(R.id.frameTest, new QuestionFragment()).commit();
    }
    //méthodes pour le menu déroulant

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuquiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //action de logout
        if (id == R.id.menu_deroulant_return_home){
            finish();
            return true;
        }

        if (id== R.id.menu_deroulant_profil){
            final Intent homeIntent = new Intent(this, MainActivity.class);
            //final Bundle extras = new Bundle();
            //extras.putString("KeyQuiz",currentQuiz.getName());
            //homeIntent.putExtras(extras);
            startActivity(homeIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
