package com.ratattack.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private BitmapFont font;
    private BitmapFont currFont;
    FirebaseInterface _FBIC;

    DataHolderClass dataholder;


    public Highscore(FirebaseInterface _FBIC) {
        this._FBIC = _FBIC;
        font = new BitmapFont();
        font.setColor(Color.DARK_GRAY);
        currFont = new BitmapFont();
        currFont.setColor(Color.PINK);
        font.getData().setScale(4f, 4f);
        currFont.getData().setScale(4f, 4f);
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
        int yPosition = Gdx.graphics.getHeight() - 100;
        int xPos = Gdx.graphics.getWidth() / 2 - 270;
        font.draw(batch, "HIGHSCORE LIST", xPos, yPosition);
        boolean isTopTen = false;
        int j = 0;
        int i = 1;
        for (Map.Entry<String, Score> entry : scoreList.entrySet()) {
            if (i < 11){
                int yPos = Gdx.graphics.getHeight() - 100 - (i * 100);
                String text = String.valueOf(i) + ". " + entry.getValue().toString();
                if (entry.getKey().equals(dataholder.getSomeValue())) {
                    currFont.draw(batch, text, xPos, yPos);
                    isTopTen = true;
                } else {
                    font.draw(batch, text, xPos, yPos);
                }
                i++;
                j=i-1;
            }
            int currYPos = Gdx.graphics.getHeight() - 100 - (11 * 100);
            int strek = Gdx.graphics.getHeight() - 100 - (10 * 100);
            if (!isTopTen && entry.getKey().equals(dataholder.getSomeValue())) {
                String currText = String.valueOf(j) + ". " + entry.getValue().toString();
                font.draw(batch, "_______________________", xPos, strek);
                currFont.draw(batch, currText, xPos, currYPos);
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
