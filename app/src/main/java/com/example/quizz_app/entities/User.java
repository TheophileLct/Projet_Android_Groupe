package com.example.quizz_app.entities;


import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AppUser")
public class User {

    public User(  String username,  String password) {
        this.username = username;
        this.password = password;
        this.score = 0;
        this.nbquiz =0;
    }

    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name="username")
    private String username;

    @ColumnInfo(name="password")
    private String password;

    @ColumnInfo(name="score")
    private int score;

    @ColumnInfo(name="nbquizz")
    private int nbquiz;

    //@ColumnInfo
    // private Image profilpicture;





    public int getNbquiz() {
        return nbquiz;
    }

    public void setNbquiz( int nbquiz) {
        this.nbquiz = nbquiz;
    }


    public int getScore() {
        return score;
    }

    public void setScore( int score) {
        score = score;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword( String password) {
        password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername( String username) {
        username = username;
    }


    public int getId() {
        return id;
    }

    public void setId( int id) {
        id = id;
    }


}
