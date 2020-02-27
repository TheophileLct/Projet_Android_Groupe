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


import com.example.quizz_app.QuestionActivity;
import com.example.quizz_app.R;
import com.example.quizz_app.object.Quiz;
import com.example.quizz_app.utils.QuestionCreate;

import java.util.ArrayList;

public class ListQuizAdapter extends RecyclerView.Adapter<ListQuizAdapter.MyViewHolder> {


    QuestionCreate factory = new QuestionCreate();
    ArrayList<Quiz> questions =factory.createquestion();
    Context context;

    public ListQuizAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quiz_list_item, parent, false);
        return new MyViewHolder(view,this.context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.display(questions.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView nbquestion;
        private final TextView difficulte;
        private final ImageView symbole;
        private final Context context;

        private Quiz currentQuiz;

        public MyViewHolder(final View itemView,Context context) {
            super(itemView);
            this.context=context;

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
            extras.putString("Quizname",currentQuiz.getName());
            homeIntent.putExtras(extras);
            context.startActivity(homeIntent);
        }

        public void display(Quiz quiz) {
            currentQuiz=quiz;
            name.setText(quiz.getName());
            nbquestion.setText(Integer.toString(quiz.getNumberOfQuestions()));
            difficulte.setText(Integer.toString(quiz.getDifficulte()));
            Drawable place = symbole.getContext().getResources().getDrawable(R.drawable.quiz);
            symbole.setImageDrawable(place);
        }


    }
}
