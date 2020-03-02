package com.example.quizz_app.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

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

    public static void afficher_rang(int score, int nbquiz, ImageView affiche, TextView rang) {
        Drawable place;
        if ((nbquiz>250)&&(score>25000)){
            place=affiche.getContext().getResources().getDrawable(R.drawable.dieu);
            affiche.setImageDrawable(place);
            rang.setText("Dieu du Quiz");
        }
        else if ((nbquiz>100)&&(score>10000)){
            place=affiche.getContext().getResources().getDrawable(R.drawable.heros);
            affiche.setImageDrawable(place);
            rang.setText("Héros du Quiz");
        }
        else if ((nbquiz>50)&&(score>5000)){
            place=affiche.getContext().getResources().getDrawable(R.drawable.grandmaitre);
            affiche.setImageDrawable(place);
            rang.setText("Grand Maître du Quiz");
        }
        else if ((nbquiz>25)&&(score>2500)){
            place=affiche.getContext().getResources().getDrawable(R.drawable.maitre);
            affiche.setImageDrawable(place);
            rang.setText("Maître du Quiz");
        }
        else if ((nbquiz>10)&&(score>1000)){
            place=affiche.getContext().getResources().getDrawable(R.drawable.veteran);
            affiche.setImageDrawable(place);
            rang.setText("Vétéran du Quiz");
        }
        else if ((nbquiz > 3) && (score > 300)) {
            place = affiche.getContext().getResources().getDrawable(R.drawable.compagnon);
            affiche.setImageDrawable(place);
            rang.setText("Compagnon du Quiz");
        }
        else if ((nbquiz > 0) && (score > 0)) {
            place = affiche.getContext().getResources().getDrawable(R.drawable.apprentie);
            affiche.setImageDrawable(place);
            rang.setText("Apprenti du Quiz");
        }
        else if ((nbquiz == 0) && (score > 0)) {
            place = affiche.getContext().getResources().getDrawable(R.drawable.neophite);
            affiche.setImageDrawable(place);
            rang.setText("Néophite du Quiz");
        }
        else if ((nbquiz == 0) && (score == 0)) {
            place = affiche.getContext().getResources().getDrawable(R.drawable.nouveauvenu);
            affiche.setImageDrawable(place);
            rang.setText("Nouveau Venu");
        }





    }
}
