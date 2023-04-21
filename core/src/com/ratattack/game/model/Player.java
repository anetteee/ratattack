package com.ratattack.game.model;

import com.ratattack.game.gamecontroller.Observer;

import java.util.ArrayList;

public class Player {

    public static final ArrayList<Observer> balanceObservers = new ArrayList<>();
    private static final ArrayList<Observer> scoreObservers = new ArrayList<>();
    private static int score;

    private String name;

    private static int balance;

    public Player(String name) {
        this.name = name;
        score = 0;
        balance = 0;
    }

    public static void setScore(int newScore) {
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

    public static int getScore() {
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

    /*KOMMENTERTE UT DENNE
    public void attachScoreObserver(Observer observer){
        scoreObservers.add(observer);
    }

     */

    public static void notifyBalanceObservers(){
        for (Observer observer: balanceObservers) {
            observer.update();
        }
    }

    public static void notifyScoreObservers() {
        for (Observer observer: scoreObservers) {
            observer.update();
        }
    }
}
