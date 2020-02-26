package com.example.quizz_app.object;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String name;
    private int difficulte = 10;
    private List<Question> questions;

    public Quiz(String name, List<Question> questions) {
        this.name = name;
        this.questions = new ArrayList<>();
        for(Question question : questions)
        {
            this.questions.add(new Question(question));
        }
    }

    public String getName() { return name; }
    public List<Question> getQuestions() { return questions; }
    public int getNumberOfQuestions() { return questions.size(); }
    public int getDifficulte() {
        return difficulte;
    }
}
