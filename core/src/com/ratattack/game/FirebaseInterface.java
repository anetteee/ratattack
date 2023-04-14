package com.ratattack.game;

import java.util.ArrayList;
import java.util.HashMap;

// Her initialiserer vi bare hvilke functioner de andre klassene skal ha
// Med "de andre" mener jeg AndroidInterfaceClass, CoreInterfaceClass og DesktopInterfaceClass
public interface FirebaseInterface {


    public void getHighscores(HashMap<String, Score> scoreMap);


    public void addHighscore(Score score, DataHolderClass dataHolder);

}