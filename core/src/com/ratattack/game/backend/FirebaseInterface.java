package com.ratattack.game.backend;

import java.util.LinkedHashMap;

/**
 * Initializing the funtions that the classes AndroidInterfaceClass, CoreInterfaceClass and DesktopInterfaceClass will have
 */

public interface FirebaseInterface {

    /***
     * TODO: LEGG TIL KOMMENTARER
     * */


    void getHighscores(LinkedHashMap<String, Score> scoreMap);


    void addHighscore(Score score, DataHolderClass dataHolder);

}