package com.ratattack.game.backend;

public class Score implements Comparable<Score> {
    /***
     * TODO: LEGG TIL KOMMENTARER
     * */

    public int score;
    public String name;

    // Default konstruktør. Denne må være med
    public Score() {
        score = 0;
        name = "";
    }

    public Score(int score, String name) {
        this.score = score;
        this.name = name;

    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + ": " + score;
    }

    @Override
    public int compareTo(Score o) {
        if (score > o.score) {
            return -1;
        }
        else if (score < o.score) {
            return 1;
        }
        else {
            return name.compareTo(o.name);
        }
    }
}
