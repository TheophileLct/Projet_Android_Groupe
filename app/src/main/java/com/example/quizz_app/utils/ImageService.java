package com.example.quizz_app.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.quizz_app.R;

public class ImageService {


    public static void afficher_image(String quizname, ImageView symbole){
        Drawable place;
        if(quizname.equals("Retour Vers Le Futur")){
            place = symbole.getContext().getResources().getDrawable(R.drawable.backto);
            symbole.setImageDrawable(place);
        }
        else if(quizname.equals("Harry Potter")){
            place = symbole.getContext().getResources().getDrawable(R.drawable.harrypotter);
            symbole.setImageDrawable(place);
        }
        else if(quizname.equals("Marvel")){
            place = symbole.getContext().getResources().getDrawable(R.drawable.avengers);
            symbole.setImageDrawable(place);
        }
        else if(quizname.equals("Kaamelott")){
            place = symbole.getContext().getResources().getDrawable(R.drawable.kamelott);
            symbole.setImageDrawable(place);
        }
        else if(quizname.equals("Video game's costume")){
            place = symbole.getContext().getResources().getDrawable(R.drawable.mario);
            symbole.setImageDrawable(place);
        }
        else{
            place = symbole.getContext().getResources().getDrawable(R.drawable.quiz);
            symbole.setImageDrawable(place);
        }
    }
}
