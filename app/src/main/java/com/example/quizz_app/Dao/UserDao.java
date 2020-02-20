package com.example.quizz_app.Dao;

import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.quizz_app.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE username=(:username)")
    List<User> getUserByUsername(String username);

    @Update
    public void updateUsers(User... users);

    @Delete
    public void deleteUsers(User... users);

    @Insert
    long insertItem(User user);

    @Query("SELECT * FROM User WHERE id=(:id)")
    List<User> getUserById(String id);

    // @Query("INSERT INTO person(id,Username,Password,Score,nbquiz) VALUES(:id,:Username,:Password,:Score,:nbquiz) ")
    // fun User (:id,:Username,:Password,:Score,:nbquiz)

}

