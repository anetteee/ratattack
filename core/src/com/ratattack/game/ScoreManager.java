package com.ratattack.game;

public class ScoreManager {
    public static ScoreManager instance;
    public int score = 0;

    public ScoreManager() {
        if (instance == null) {
            instance = this;
        }
        else {
            throw new RuntimeException("ScoreManager singleton already exists");
        }
    }
}
