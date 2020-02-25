package com.example.quizz_app.appDatabse;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quizz_app.Dao.UserDao;
import com.example.quizz_app.entities.User;

@Database(entities= {User.class},version = 1 )
public abstract class AppDatabase  extends RoomDatabase {
    private static final String DB_Name="app_user";
    private  static  AppDatabase instance;

    public  static synchronized AppDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,
                    DB_Name)
                                .fallbackToDestructiveMigration()
                                .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}
