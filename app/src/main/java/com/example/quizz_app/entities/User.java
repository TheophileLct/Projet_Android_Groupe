package com.example.quizz_app.entities;


import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true) @NonNull
    private  int id;

    @ColumnInfo() @NonNull
    private String Username;

    @ColumnInfo() @NonNull
    private String Password;

    @ColumnInfo() @NonNull
    private int Score;

    @ColumnInfo() @NonNull
    private int nbquiz;

    //@ColumnInfo
    // private Image profilpicture;


    public User(@NonNull int id, @NonNull String username, @NonNull String password) {
        this.id = id;
        Username = username;
        Password = password;
        Score = 0;
        this.nbquiz =0;
    }

    @NonNull
    public int getNbquiz() {
        return nbquiz;
    }

    public void setNbquiz(@NonNull int nbquiz) {
        this.nbquiz = nbquiz;
    }

    @NonNull
    public int getScore() {
        return Score;
    }

    public void setScore(@NonNull int score) {
        Score = score;
    }

    @NonNull
    public String getPassword() {
        return Password;
    }

    public void setPassword(@NonNull String password) {
        Password = password;
    }

    @NonNull
    public String getUsername() {
        return Username;
    }

    public void setUsername(@NonNull String username) {
        Username = username;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
}
