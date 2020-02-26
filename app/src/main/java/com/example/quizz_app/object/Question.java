package com.example.quizz_app.object;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int number;
    private String title;
    private List<Pair<String, Boolean>> answers;
    private double timer;

    public Question(int number, String title, List<Pair<String, Boolean>> answers, double timer)
    {
        this.number = number;
        this.title = title;
        this.timer = timer;
        this.answers = new ArrayList<Pair<String, Boolean>>();
        for(Pair<String, Boolean> answer : answers)
        {
            this.answers.add(new Pair<String, Boolean>(answer.first, answer.second));
        }
    }

    public Question(Question otherQuestion)
    {
        this.number = otherQuestion.number;
        this.title = otherQuestion.title;
        this.timer = otherQuestion.timer;
        this.answers = new ArrayList<Pair<String, Boolean>>();
        for(Pair<String, Boolean> answer : otherQuestion.answers)
        {
            this.answers.add(new Pair<String, Boolean>(answer.first, answer.second));
        }
    }
    public int getNumber() { return number; }
    public String getTitle() { return title; }
    public List<String> getAnswers()
    {
        List<String> returnListAnswers = new ArrayList<>();
        for(Pair<String, Boolean> answer : answers)
        {
            returnListAnswers.add(answer.first);
        }
        return returnListAnswers;
    }
    public boolean isGoodAnswer(String answerToTest)
    {
        for(Pair<String, Boolean> answer : answers)
        {
            if(answer.first == answerToTest)
            {
                if (answer.second == Boolean.TRUE)
                    return true;
                else
                    return false;
            }
        }
        //If the program go here, the answerToTest was not in answers list.
        return false;
    }
    public double getTimer() { return timer; }
}
