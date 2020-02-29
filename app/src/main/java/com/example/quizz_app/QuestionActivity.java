package com.example.quizz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizz_app.object.Question;
import com.example.quizz_app.utils.ImageService;
import com.example.quizz_app.utils.QuestionsService;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private String quizname;
    private String username;
    private int difficulte;
    private ImageView logo;
    private int count;
    private TextView SubjectText;
    private TextView QuestionWidget;
    private List<Button> AnswerButtons = new ArrayList<>();
    private Button AnswerBButton;
    private Button AnswerCButton;
    private Button AnswerDButton;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        logo=findViewById(R.id.logoquiz);
        this.count=0;
        final Intent intent = getIntent();
        if(null!= intent){
            final Bundle extras = intent.getExtras();
            if(null!= extras )
            {
                this.quizname = extras.getString("Quizname");
                this.username = extras.getString("username");
                getSupportActionBar().setSubtitle(this.quizname);
                ImageService.afficher_image(quizname,logo);
                if(quizname == null)
                    Log.e(QuestionActivity.class.getName(), "No theme name was given in the bundle when QuestionActivity is created");
                QuestionsService.resetQuiz();
                QuestionsService.chooseQuestionList(quizname);
                this.difficulte=QuestionsService.getListDifficulty(quizname);

            }
        }



        //getFragmentManager().beginTransaction().add(R.id.frameTest, new QuestionFragment()).commit();
    }




    @Override
    protected void onStart() {
        super.onStart();
        SubjectText = (TextView) findViewById(R.id.theme_text);
        SubjectText.setText(quizname);

        QuestionWidget = (TextView) findViewById(R.id.question_textview);
        AnswerButtons.add((Button) findViewById(R.id.answer_a_button));
        AnswerButtons.add((Button) findViewById(R.id.answer_b_button));
        AnswerButtons.add((Button) findViewById(R.id.answer_c_button));
        AnswerButtons.add((Button) findViewById(R.id.answer_d_button));

        for(int i = 0; i < AnswerButtons.size(); i++)
            AnswerButtons.get(i).setOnClickListener(this);

        Question q = QuestionsService.getCurrentQuestion();
        putGUIInformationRelatedToQuestion(q);
    }
    private void putGUIInformationRelatedToQuestion(Question q)
    {
        QuestionWidget.setText(q.getTitle());
        for(int i = 0; i < AnswerButtons.size(); i++)
            AnswerButtons.get(i).setText(q.getAnswers().get(i));
    }
    private void onAnswerGiven(int index)
    {
        Question q = QuestionsService.getCurrentQuestion();
        if(q.isGoodAnswer(AnswerButtons.get(index).getText().toString()))
        {
            this.count++;
            //Increase score
            if (QuestionsService.isAnotherQuestion()){
                QuestionsService.nextQuestion();
                putGUIInformationRelatedToQuestion(QuestionsService.getCurrentQuestion());
            }
            else {
                //END OF QUIZ WITH WIN SCREEN!!!!
                Intent intent= new Intent ( QuestionActivity.this, ResultsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("username",this.username);
                bundle.putBoolean("result",true);
                bundle.putString("quizname",this.quizname);
                bundle.putInt("difficulte",this.difficulte);
                bundle.putInt("count",this.count);
                intent.putExtras(bundle);
                startActivity(intent);
                QuestionsService.resetQuiz();
            }
        }
        else
        {
            //Bad answer!!! Shame delivery and END OF SCREEN WITH LOOSE SCREEN!!!!
            Intent intent= new Intent ( QuestionActivity.this,ResultsActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("username",this.username);
            bundle.putBoolean("result",false);
            bundle.putString("quizname",this.quizname);
            bundle.putInt("difficulte",this.difficulte);
            bundle.putInt("count",this.count);
            intent.putExtras(bundle);
            startActivity(intent);
            QuestionsService.resetQuiz();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.answer_a_button:
                onAnswerGiven(0);
                break;
            case R.id.answer_b_button:
                onAnswerGiven(1);
                break;
            case R.id.answer_c_button:
                onAnswerGiven(2);
                break;
            case R.id.answer_d_button:
                onAnswerGiven(3);
                break;
            default:
                break;
        }
    }




    //méthodes pour le menu déroulant

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuquiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_deroulant_quiz_return_home){
            Intent intent= new Intent ( QuestionActivity.this,MenuPrincipal.class);
            Bundle bundle=new Bundle();
            bundle.putString("username",this.username);
            intent.putExtras(bundle);
            startActivity(intent);
            QuestionsService.resetQuiz();
        }

        return super.onOptionsItemSelected(item);
    }
}
