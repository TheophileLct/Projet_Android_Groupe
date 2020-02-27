package com.example.quizz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;

public class QuestionActivity extends AppCompatActivity {

    protected CountDownTimer timer;
    private String quizname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                this.quizname = extras.getString("Quizname");
                getSupportActionBar().setSubtitle(this.quizname);

            }
        }

        //getFragmentManager().beginTransaction().add(R.id.frameTest, new QuestionFragment()).commit();
    }



    //méthodes pour le menu déroulant

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuquiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_deroulant_quiz_return_home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
