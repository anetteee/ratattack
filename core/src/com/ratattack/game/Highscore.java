package com.ratattack.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ratattack.game.backend.DataHolderClass;
import com.ratattack.game.backend.FirebaseInterface;
import com.ratattack.game.backend.Score;
import com.ratattack.game.gamecontroller.GameController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Highscore {
    private LinkedHashMap<String, Score> scoreList = new LinkedHashMap<>();
    private BitmapFont fontText;
    private BitmapFont PinkFont;
    private BitmapFont bigFont;
    FirebaseInterface _FBIC;

    DataHolderClass dataholder;


    public Highscore(FirebaseInterface _FBIC) {
        this._FBIC = _FBIC;
        fontText = new BitmapFont();
        bigFont = new BitmapFont();
        fontText.setColor(Color.DARK_GRAY);
        bigFont.setColor(Color.DARK_GRAY);
        bigFont.getData().setScale(7f, 7f);
        PinkFont = new BitmapFont();
        PinkFont.setColor(Color.PINK);
        fontText.getData().setScale(4f, 4f);
        PinkFont.getData().setScale(4f, 4f);
        dataholder = GameController.getInstance().getDataHolderClass();
        fetchHighscores();
    }

    private int getNumberOfElements(ArrayList<Score> scoreList) {
        int number;
        if (scoreList.size()<10) {
            number = scoreList.size();
        }
        else {
           number = 10;
        }
        return number;
    }

    public void render(SpriteBatch batch) {
        int yPosition = Gdx.graphics.getHeight() - 50;
        int xPos = Gdx.graphics.getWidth() / 2 - 425;
        //bigFont.draw(batch, "HIGHSCORE LIST", xPos, yPosition);
        boolean isTopTen = false;
        int j = 0;
        int i = 1;
        for (Map.Entry<String, Score> entry : scoreList.entrySet()) {
            if (i < 11){
                int yPos = Gdx.graphics.getHeight() - 100 - (i * 100);
                String text = String.valueOf(i) + ". " + entry.getValue().toString();
                if (entry.getKey().equals(dataholder.getKeyValue())) {
                    PinkFont.draw(batch, text, xPos, yPos);
                    isTopTen = true;
                } else {
                    fontText.draw(batch, text, xPos, yPos);
                }
                i++;
                j=i-1;
            }
            int currYPos = Gdx.graphics.getHeight() - 100 - (11 * 100);
            int strek = Gdx.graphics.getHeight() - 100 - (10 * 100);
            if (!isTopTen && entry.getKey().equals(dataholder.getKeyValue())) {
                String currText = String.valueOf(j) + ". " + entry.getValue().toString();
                fontText.draw(batch, "_______________________", xPos, strek);
                PinkFont.draw(batch, currText, xPos, currYPos);
            }
            j++;
        }
    }


    public void fetchHighscores() {
        this.scoreList.clear();
        _FBIC.getHighscores(this.scoreList);
    }


    public void submitHighscore(String name, int score) {
        _FBIC.addHighscore(new Score(score, name), dataholder);
    }

}
