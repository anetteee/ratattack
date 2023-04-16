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
        int xPosition = Gdx.graphics.getWidth() / 2 - 350;
        int yPosition = Gdx.graphics.getHeight() - 100;
        font.draw(batch, "HIGHSCORE LIST", xPosition, yPosition);
        int i = 1;
        for (Map.Entry<String, Score> entry : scoreList.entrySet()) {
            if (i > 10) {
                break;
            }
            //int rank = Integer.parseInt(entry.getKey());
            int xPos = Gdx.graphics.getWidth() / 2 - 350;
            int yPos = Gdx.graphics.getHeight() - 100 - (i*100);
            String text = String.valueOf(i) + ". " + entry.getValue().toString();
            if (entry.getKey().equals(dataholder.getSomeValue())) {
                currFont.draw(batch, text, xPos, yPos);
            }
            else {
                font.draw(batch, text, xPos, yPos);
            }
            i++;
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
