package com.example.quizz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.quizz_app.R;
import com.example.quizz_app.appDatabse.AppDatabase;
import com.example.quizz_app.entities.User;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener{

    private String username;
    private String quizname;
    private int difficulte;
    private int nbgoodanswer;
    private AppDatabase database;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = Room.databaseBuilder(this, AppDatabase.class, "app_user")
                .allowMainThreadQueries()
                .build();

        setContentView(R.layout.result);
        findViewById(R.id.result_quitter_button).setOnClickListener(this);
        findViewById(R.id.result_rejouer_button).setOnClickListener(this);
        TextView resultfield = findViewById(R.id.resultat);
        TextView userfield = findViewById(R.id.result_username);
        TextView scorefield = findViewById(R.id.score_final);
        TextView phraseresultfield = findViewById(R.id.result_result);
        TextView quiznamefield = findViewById(R.id.result_quizname);
        ImageView image = findViewById(R.id.result_image);

        final Intent intent = getIntent();
        if (null != intent) {
            final Bundle extras = intent.getExtras();
            if (null != extras) {
                this.username = extras.getString("username");
                userfield.setText(this.username);
                this.user = database.userDao().getUserByUsername(username).get(0);
                this.quizname = extras.getString("quizname");
                quiznamefield.setText(this.quizname);
                final boolean result = extras.getBoolean("result");
                this.difficulte=extras.getInt("difficulte");
                this.nbgoodanswer=extras.getInt("count");
                if(result==true){
                    resultfield.setText("Félicitations ");
                    phraseresultfield.setText("Tu as atteint le bout du quiz : ");
                    double score=Math.ceil((this.difficulte/2)*this.nbgoodanswer);
                    scorefield.setText(String.valueOf((int)score));
                    image.setImageDrawable(image.getContext().getResources().getDrawable(R.drawable.felicitation));

                    //partie BDD
                    int scorebdd=this.user.getScore()+(int)score;
                    this.user.setScore(scorebdd);
                    this.user.setNbquiz(this.user.getNbquiz()+1);
                    database.userDao().updateUsers(this.user);
                }
                else{
                    resultfield.setText("Dommage... ");
                    phraseresultfield.setText("Tu n'as pas réussi à finir le quiz : ");
                    double score=Math.ceil((this.difficulte/4)*this.nbgoodanswer);
                    scorefield.setText(String.valueOf((int)score));
                    image.setImageDrawable(image.getContext().getResources().getDrawable(R.drawable.perdu));

                    //partie BDD
                    int scorebdd=this.user.getScore()+(int)score;
                    this.user.setScore(scorebdd);
                    this.user.setNbquiz(this.user.getNbquiz()+1);
                    database.userDao().updateUsers(this.user);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v==findViewById(R.id.result_rejouer_button)){
            final Intent homeIntent = new Intent(this, QuestionActivity.class);
            final Bundle extras = new Bundle();
            extras.putString("username",this.username);
            extras.putString("Quizname",this.quizname);
            homeIntent.putExtras(extras);
            startActivity(homeIntent);
        }
        if(v==findViewById(R.id.result_quitter_button)){
            final Intent homeIntent = new Intent(this, MenuPrincipal.class);
            final Bundle extras = new Bundle();
            extras.putString("username",this.username);
            homeIntent.putExtras(extras);
            startActivity(homeIntent);
        }
    }
}
