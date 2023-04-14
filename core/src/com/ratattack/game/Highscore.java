package com.ratattack.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ratattack.game.gamecontroller.GameController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Highscore {
    private  HashMap<String, Score>  scoreList = new HashMap<>();
    private BitmapFont font;
    FirebaseInterface _FBIC;

    DataHolderClass dataholder;


    public Highscore(FirebaseInterface _FBIC) {
        this._FBIC = _FBIC;
        font = new BitmapFont();
        font.getData().setScale(6f, 6f);
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

    /*
    public void render(SpriteBatch batch) {
        int xPosition = Gdx.graphics.getWidth() / 2 - 400;
        int yPosition = Gdx.graphics.getHeight() - 150;
        font.draw(batch, "HIGHSCORE LIST", xPosition, yPosition);
        for (int i = 0; i < getNumberOfElements(scoreList)  ; i++) {
            int xPos = Gdx.graphics.getWidth() / 2 - 350;
            int yPos = Gdx.graphics.getHeight() - 300 - (i * 100);
            String text = (i + 1) + ". " + scoreList.get(i).toString();
            font.draw(batch, text, xPos, yPos);
        }
    }

     */

    public void render(SpriteBatch batch) {
        int xPosition = Gdx.graphics.getWidth() / 2 - 400;
        int yPosition = Gdx.graphics.getHeight() - 150;
        font.draw(batch, "HIGHSCORE LIST", xPosition, yPosition);
        for (Map.Entry<String, Score> entry : scoreList.entrySet()) {
            int rank = Integer.parseInt(entry.getKey()) + 1;
            int xPos = Gdx.graphics.getWidth() / 2 - 350;
            int yPos = Gdx.graphics.getHeight() - 300 - (rank * 100);
            String text = rank + ". " + entry.getValue().toString();
            font.draw(batch, text, xPos, yPos);
        }
    }



    public void fetchHighscores() {
        this.scoreList.clear();
        _FBIC.getHighscores(this.scoreList);
        List<Score> scores = new ArrayList<>(scoreList.values());
        Collections.sort(scores);
        scoreList.clear();
        int i = 0;
        for (Score score : scores) {
            scoreList.put(String.valueOf(i), score);
            i++;
        }
    }


    public void submitHighscore(String name, int score) {
        _FBIC.addHighscore(new Score(score, name), dataholder);
    }

}
