package com.example.quizz_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.quizz_app.MenuPrincipal;
import com.example.quizz_app.QuestionActivity;
import com.example.quizz_app.R;
import com.example.quizz_app.object.Quiz;
import com.example.quizz_app.utils.ImageService;
import com.example.quizz_app.utils.QuestionCreate;
import com.example.quizz_app.utils.QuestionsService;

import java.util.ArrayList;
import java.util.List;

public class ListQuizAdapter extends RecyclerView.Adapter<ListQuizAdapter.MyViewHolder> {


    //QuestionCreate factory = new QuestionCreate();
    //ArrayList<Quiz> questions =factory.createquestion();
    List<String> themeName = new ArrayList<>();
    Context context;
    private String username;

    public ListQuizAdapter(Context context,String username){
        this.context=context;
        this.username = username;
        themeName = QuestionsService.getNameOfQuestionLists();
    }

    @Override
    public int getItemCount() {
        return themeName.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quiz_list_item, parent, false);
        return new MyViewHolder(view,this.context,this.username);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.display(themeName.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView nbquestion;
        private final TextView difficulte;
        private final ImageView symbole;
        private String username;
        private final Context context;

        public MyViewHolder(final View itemView,Context context,String username) {
            super(itemView);
            this.context=context;
            this.username=username;

            name = ((TextView) itemView.findViewById(R.id.quiz_name));
            nbquestion = ((TextView) itemView.findViewById(R.id.nb_question));
            difficulte = ((TextView) itemView.findViewById(R.id.difficulte));
            symbole = (ImageView) itemView.findViewById(R.id.avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeView();
                }
            });
        }

        private void changeView(){
            final Intent homeIntent = new Intent(this.context, QuestionActivity.class);
            final Bundle extras = new Bundle();
            extras.putString("Quizname", name.getText().toString());
            extras.putString("username",this.username);
            homeIntent.putExtras(extras);
            context.startActivity(homeIntent);
        }

        public void display(String f_name) {
            name.setText(f_name);
            nbquestion.setText(Integer.toString(QuestionsService.getNumberOfQuestionsInList(f_name)));
            difficulte.setText(Integer.toString(QuestionsService.getListDifficulty(f_name)));
            ImageService.afficher_image(f_name,symbole);
        }
        /*
        public void display(Quiz quiz) {
            currentQuiz=quiz;
            name.setText(quiz.getName());
            nbquestion.setText(Integer.toString(quiz.getNumberOfQuestions()));
            difficulte.setText(Integer.toString(quiz.getDifficulte()));
            Drawable place = symbole.getContext().getResources().getDrawable(R.drawable.quiz);
            symbole.setImageDrawable(place);
        }*/


    }
}
