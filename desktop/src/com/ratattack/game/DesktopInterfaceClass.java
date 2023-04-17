package com.ratattack.game;

import com.ratattack.game.backend.DataHolderClass;
import com.ratattack.game.backend.FirebaseInterface;
import com.ratattack.game.backend.Score;

import java.util.LinkedHashMap;

public class DesktopInterfaceClass implements FirebaseInterface {

    @Override
    public void getHighscores(LinkedHashMap<String, Score> scoreMap) {

    }

     @Override
     public void addHighscore(Score score, DataHolderClass dataHolder) {

     }

}
