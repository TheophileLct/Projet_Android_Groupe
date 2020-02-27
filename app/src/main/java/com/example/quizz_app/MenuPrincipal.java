package com.example.quizz_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizz_app.adapter.ListQuizAdapter;
import com.example.quizz_app.utils.QuestionsService;

public class MenuPrincipal extends AppCompatActivity {

    private static Context sContext;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_du_quiz);
        TextView namefield = findViewById(R.id.choix_du_quiz_username);

        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                final String  name = extras.getString("username");
                this.username=name;
                namefield.setText(name);

            }
        }

        if(!QuestionsService.isLoaded())
        {
            QuestionsService.loadListQuestions(this);
            if(!QuestionsService.isLoaded()) {
                Log.e(QuestionsService.class.getName(), "The JSON is a fucking disaster!!!!!!!!!!!!!");
                return; }
        }

        final RecyclerView rv = (RecyclerView) findViewById(R.id.quizList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new ListQuizAdapter(this));
    }



    //méthodes pour le menu déroulant

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuprincipal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_deroulant_return_home){
            finish();
            return true;
        }

        if (id== R.id.menu_deroulant_profil){
            final Intent homeIntent = new Intent(this, ProfilActivity.class);
            final Bundle extras = new Bundle();
            extras.putString("username",this.username);
            homeIntent.putExtras(extras);
            startActivity(homeIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    public static Context getContext() {
        return sContext;
    }
}
