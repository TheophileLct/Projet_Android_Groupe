package com.example.quizz_app;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.quizz_app.appDatabse.AppDatabase;

public class QuizzApplication extends Application {

    private static Context sContext;

    public void onCreate(){
        super.onCreate();
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "app_user")
                .allowMainThreadQueries()
                .build();



        // Keep a reference to the application context
        sContext = getApplicationContext();
    }

    // Used to access Context anywhere within the app
    public static Context getContext() {
        return sContext;
    }
}

