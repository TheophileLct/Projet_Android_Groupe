package com.example.quizz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;

public class QuestionActivity extends AppCompatActivity {

    protected CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //getFragmentManager().beginTransaction().add(R.id.frameTest, new QuestionFragment()).commit();
    }
}
