package com.example.quizz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.quizz_app.Dao.UserDao;
import com.example.quizz_app.appDatabse.AppDatabase;
import com.example.quizz_app.entities.User;
import com.example.quizz_app.utils.Constants;

public class ProfilActivity extends AppCompatActivity {

    private String username;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        database = Room.databaseBuilder(this, AppDatabase.class, "app_user")
                .allowMainThreadQueries()
                .build();
        TextView namefield = findViewById(R.id.profil_name);
        TextView scorefield = findViewById(R.id.profil_score);
        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                final String  name = extras.getString("username");
                this.username=name;
                namefield.setText(name);
                User user = database.userDao().getUserByUsername(username).get(0);
                scorefield.setText(String.valueOf(user.getScore()));
            }
        }

    }

    //méthodes pour le menu déroulant

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprofil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_deroulant_profil_return_home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
