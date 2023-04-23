package com.ratattack.game.view.screenState;


public interface State {


    void changeState(State state);


    boolean shouldChangeState(String type);


    void changeScreen(String type);

    void renderScreen();











}
