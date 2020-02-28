package com.example.quizz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizz_app.R;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener{

    private String username;
    private String quizname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        findViewById(R.id.result_quitter_button).setOnClickListener(this);
        findViewById(R.id.result_rejouer_button).setOnClickListener(this);
        TextView resultfield = findViewById(R.id.resultat);
        TextView userfield = findViewById(R.id.result_username);
        TextView scorefield = findViewById(R.id.score_final);
        TextView phraseresultfield = findViewById(R.id.result_result);
        TextView quiznamefield = findViewById(R.id.result_quizname);

        final Intent intent = getIntent();
        if (null != intent) {
            final Bundle extras = intent.getExtras();
            if (null != extras) {
                this.username = extras.getString("username");
                userfield.setText(this.username);
                this.quizname = extras.getString("quizname");
                quiznamefield.setText(this.quizname);
                final boolean result = extras.getBoolean("result");
                if(result==true){
                    resultfield.setText("Félicitations ");
                    phraseresultfield.setText("Tu as atteint le bout du quiz : ");
                    scorefield.setText("20");
                }
                else{
                    resultfield.setText("Dommage... ");
                    phraseresultfield.setText("Tu n'as pas réussi à finir le quiz : ");
                    scorefield.setText("0");
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
