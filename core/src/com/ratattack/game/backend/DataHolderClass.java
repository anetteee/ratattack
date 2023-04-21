package com.ratattack.game.backend;

import com.ratattack.game.gamecontroller.GameController;

public class DataHolderClass {
    static FirebaseInterface _FBIC =  GameController.getInstance().getFirebaseInterface();
    String currentKey;
    //FJERNET DENNE Score lastScore;

    public DataHolderClass() {

    }

    public void setKeyValue(String someValue) {
        this.currentKey = someValue;
    }

    public String getKeyValue() {
        return currentKey;
    }

    /*KOMMENTERTE UT DENNE
    public void PrintKeyValue()
    {
        //System.out.println("from printSomeValue i DataHolderClass: "+ currentKey);
    }

     */
}
