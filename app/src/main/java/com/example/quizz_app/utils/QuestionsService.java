package com.example.quizz_app.utils;

import android.app.Activity;
import android.util.Log;

import androidx.core.util.Pair;

import com.example.quizz_app.object.Question;
import com.example.quizz_app.R;
import com.example.quizz_app.object.Quiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class QuestionsService {
    private static final QuestionsService ourInstance = new QuestionsService();

    public static QuestionsService getInstance() {
        return ourInstance;
    }

    private static List<Quiz> AllQuestionsList = new ArrayList<>();
    private static int currentListQuestionIndex = 0;
    private static int currentQuestionIndex = 0;
    private static boolean loaded = false;

    private QuestionsService() {
    }


    private static int getIndexOfQuestionList(String nameOfList) throws NoSuchElementException
    {
        for (int i = 0; i < AllQuestionsList.size(); i++) {
            if (AllQuestionsList.get(i).getName().equals(nameOfList)) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public static void chooseQuestionList(String nameOfList)  {
        currentListQuestionIndex = getIndexOfQuestionList(nameOfList);
    }
    public static List<String> getNameOfQuestionLists()
    {
        List<String> names = new ArrayList<>();
        for(Quiz listQuestions : AllQuestionsList)
        {
            names.add(listQuestions.getName());
        }
        return names;
    }
    public static int getNumberOfQuestionsInList(String nameOfList) {
        return AllQuestionsList.get(getIndexOfQuestionList(nameOfList)).getNumberOfQuestions();
    }
    public static String getListPictureName(String nameOfList) {
        return AllQuestionsList.get(getIndexOfQuestionList(nameOfList)).getPictureName();
    }
    public static int getListDifficulty(String nameOfList) {
        return AllQuestionsList.get(getIndexOfQuestionList(nameOfList)).getDifficulty();
    }
    public static void resetQuiz()
    {
        currentListQuestionIndex = 0;
        currentQuestionIndex = 0;
    }
    public static boolean isAnotherQuestion()
    {
        if(AllQuestionsList.get(currentListQuestionIndex).getNumberOfQuestions() <= currentQuestionIndex + 1)
            return false;
        return true;
    }
    public static Question getCurrentQuestion()
    {
        return AllQuestionsList.get(currentListQuestionIndex).getQuestions().get(currentQuestionIndex);
    }
    public static void nextQuestion() { currentQuestionIndex++; }




















    public static boolean isLoaded() { return loaded; }

    public static void loadListQuestions(Activity currentActivity)
    {
        AllQuestionsList = new ArrayList<>();

        InputStream is = currentActivity.getResources().openRawResource(R.raw.questions);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();
        Log.i(QuestionsService.class.getName(), jsonString);

        try {
            JSONObject jsonObjectt = new JSONObject(jsonString);
            JSONArray themeArray = jsonObjectt.getJSONArray("themes");
            parseAllListQuestions(themeArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        loaded = true;
    }
    private static void parseAllListQuestions(JSONArray JSONAllListQuestions)
    {
        try {
            for(int i = 0; i < JSONAllListQuestions.length(); i++)
                AllQuestionsList.add(parseListQuestions(JSONAllListQuestions.getJSONObject(i)));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private static Quiz parseListQuestions(JSONObject JSONListQuestions)
    {
        try {
            String name = JSONListQuestions.getString("name");
            String pictureName = JSONListQuestions.getString("picture");
            int difficulty = JSONListQuestions.getInt("difficulty");

            List<Question> questions = new ArrayList<>();
            JSONArray JSONQuestions = JSONListQuestions.getJSONArray("questions");
            for(int i = 0; i < JSONQuestions.length(); i++)
            {
                JSONObject JSONquestion = JSONQuestions.getJSONObject(i);
                questions.add(parseQuestion(JSONquestion));
            }

            Quiz listQuestions = new Quiz(name, pictureName, difficulty, questions);
            return listQuestions;

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    private static Question parseQuestion(JSONObject JSONQuestion)
    {
        try {
            int number = JSONQuestion.getInt("number");
            String title = JSONQuestion.getString("title");
            double timer = JSONQuestion.getDouble("timer");

            List<Pair<String, Boolean>> answers = new ArrayList<>();
            JSONArray JSONAnswers = JSONQuestion.getJSONArray("answers");
            for(int i = 0; i < JSONAnswers.length(); i++)
            {
                JSONObject JSONAnswer = JSONAnswers.getJSONObject(i);
                String description = JSONAnswer.getString("description");
                Boolean isTrue = JSONAnswer.getBoolean("isTrue");
                answers.add(new Pair<String, Boolean>(description, isTrue));
            }

            Question question = new Question(number, title, answers, timer);
            return question;

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
