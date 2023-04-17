package com.ratattack.game.model;

import com.ratattack.game.backend.DataHolderClass;
import com.ratattack.game.backend.FirebaseInterface;
import com.ratattack.game.backend.Score;
import com.ratattack.game.gamecontroller.GameController;

import java.util.LinkedHashMap;

public class HighscoreList {
    private final LinkedHashMap<String, Score> scoreList = new LinkedHashMap<>();
    FirebaseInterface _FBIC;
    DataHolderClass dataholder;

    public HighscoreList(FirebaseInterface _FBIC) {
        this._FBIC = _FBIC;

        dataholder = GameController.getInstance().getDataHolderClass();
        fetchHighscores();
    }

    public void fetchHighscores() {
        this.scoreList.clear();
        _FBIC.getHighscores(this.scoreList);
    }

    public void submitHighscore(String name, int score) {
        _FBIC.addHighscore(new Score(score, name), dataholder);
    }

    public LinkedHashMap<String, Score> getScoreList() {
        return scoreList;
    }

}
