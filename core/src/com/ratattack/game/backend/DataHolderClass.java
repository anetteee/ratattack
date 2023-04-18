package com.ratattack.game.backend;

import com.ratattack.game.gamecontroller.GameController;

public class DataHolderClass {
    static FirebaseInterface _FBIC =  GameController.getInstance().getFirebaseInterface();
    String currentKey;
    Score lastScore;

    public DataHolderClass() {

    }

    public void setKeyValue(String someValue) {
        this.currentKey = someValue;
    }

    public String getKeyValue() {
        return currentKey;
    }

    public void PrintKeyValue()
    {
        System.out.println("from printSomeValue i DataHolderClass: "+ currentKey);
    }
}
