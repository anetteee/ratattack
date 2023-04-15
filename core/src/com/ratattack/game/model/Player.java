package com.ratattack.game.model;

public class Player {

    private int score;

    private String name;

    private static int balance;

    public Player(String name) {
        this.name = name;
        score = 0;
        balance = 0;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setBalance(int newBalance) {
        balance = newBalance;
    }

    public int getScore() {
        return score;
    }

    public static int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }
}
