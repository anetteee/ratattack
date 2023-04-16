package com.ratattack.game;

public class User {
    public String username;
    public Integer highscore;

    public User() {
        // default konstruktør trengs for å kalle DataSnapshot.getValue(User.class)

    }

    public User(String username, Integer highscore) {
        this.username = username;
        this.highscore = highscore;
    }

}

