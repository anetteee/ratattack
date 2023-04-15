package com.ratattack.game.model;

import com.ratattack.game.gamecontroller.Observer;

import java.util.ArrayList;

public class Player {

    public final ArrayList<Observer> balanceObservers = new ArrayList<>();
    private final ArrayList<Observer> scoreObservers = new ArrayList<>();
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
        notifyScoreObservers();
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setBalance(int newBalance) {
        balance = newBalance;
        notifyBalanceObservers();
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

    public void attachBalanceObserver(Observer observer){
        balanceObservers.add(observer);
    }

    public void attachScoreObserver(Observer observer){
        scoreObservers.add(observer);
    }

    public void notifyBalanceObservers(){
        for (Observer observer: balanceObservers) {
            observer.update();
        }
    }

    public void notifyScoreObservers() {
        for (Observer observer: scoreObservers) {
            observer.update();
        }
    }
}
