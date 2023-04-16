package com.ratattack.game;

import com.ratattack.game.gamecontroller.GameController;

public class DataHolderClass {
    static FirebaseInterface _FBIC =  GameController.getInstance().getFirebaseInterface();
    String someValue;
    Score lastScore;

    public DataHolderClass() {

    }

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }

    public String getSomeValue() {
        return someValue;
    }

    public void PrintSomeValue()
    {
        System.out.println("from printSomeValue i DataHolderClass: "+ someValue);
    }
}
