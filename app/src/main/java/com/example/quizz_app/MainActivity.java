package com.example.quizz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.example.quizz_app.appDatabse.AppDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "app_user")
                .allowMainThreadQueries()
                .build();
        setContentView(R.layout.activity_profil);
    }
}
