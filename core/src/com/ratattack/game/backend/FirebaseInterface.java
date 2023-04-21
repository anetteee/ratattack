package com.ratattack.game.backend;

import java.util.LinkedHashMap;

// Her initialiserer vi bare hvilke functioner de andre klassene skal ha
// Med "de andre" mener jeg AndroidInterfaceClass, CoreInterfaceClass og DesktopInterfaceClass
public interface FirebaseInterface {

    /***
     * TODO: LEGG TIL KOMMENTARER
     * */


    void getHighscores(LinkedHashMap<String, Score> scoreMap);


    void addHighscore(Score score, DataHolderClass dataHolder);

}