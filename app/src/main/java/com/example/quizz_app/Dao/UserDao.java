package com.example.quizz_app.Dao;

import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.quizz_app.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM AppUser")
    List<User> getAll();

    @Query("SELECT * FROM AppUser WHERE username=(:username)")
    List<User> getUserByUsername(String username);

    @Update
    public void updateUsers(User... users);

    @Delete
    public void deleteUsers(User... users);

    @Insert
    void insertAll(User... users);


    @Query("SELECT * FROM AppUser WHERE id=(:id)")
    List<User> getUserById(String id);

    // @Query("INSERT INTO person(id,Username,Password,Score,nbquiz) VALUES(:id,:Username,:Password,:Score,:nbquiz) ")
    // fun User (:id,:Username,:Password,:Score,:nbquiz)

}

