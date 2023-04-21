package com.ratattack.game.backend;

public class DataHolderClass {

    String currentKey;

    public DataHolderClass() {
    }

    public void setKeyValue(String someValue) {
        this.currentKey = someValue;
    }

    public String getKeyValue() {
        return currentKey;
    }
}
