package com.ratattack.game;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Her er logikk for Firebase
public class AndroidInterfaceClass implements FirebaseInterface {
    FirebaseDatabase database;

    DatabaseReference highscores;
    String myKey;

    public AndroidInterfaceClass() {

        database = FirebaseDatabase.getInstance();
        highscores = database.getReference("highscores");
    }

    /*


    public void getHighscores(HashMap<String, Score> scoreMap) {
        System.out.println("Getting highcores");
        highscores.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("Got highcores");
                Iterable<DataSnapshot> response = task.getResult().getChildren();
                List<Map.Entry<String, Score>> entryList = new ArrayList<>();
                for (DataSnapshot child : response) {
                    Score score = child.getValue(Score.class);
                    entryList.add(new AbstractMap.SimpleEntry<>(child.getKey(), score));
                    //System.out.println("her er key fra android" + child.getKey());
                }
                Collections.sort(entryList, new Comparator<Map.Entry<String, Score>>() {
                    @Override
                    public int compare(Map.Entry<String, Score> o1, Map.Entry<String, Score> o2) {
                        return Integer.compare(o2.getValue().getScore(), o1.getValue().getScore());
                    }
                });
                scoreMap.clear();
                int i = 0;
                for (Map.Entry<String, Score> entry : entryList) {
                    scoreMap.put(entry.getKey(), entry.getValue());
                    String key = String.valueOf(i);
                    if (scoreMap.containsKey(key)) {
                        Score existingScore = scoreMap.get(key);
                        if (existingScore.getScore() == entry.getValue().getScore()) {
                            // if two scores have the same value, append a suffix to the key
                            key += "_";
                            while (scoreMap.containsKey(key)) {
                                key += "_";
                            }
                        }
                    }
                    scoreMap.put(key, entry.getValue());
                    i++;
                }
            }
        });
    }

     */

    @Override
    public void getHighscores(LinkedHashMap<String, Score> scoreMap) {
        System.out.println("Getting highcores");
        highscores.orderByChild("score").get().addOnCompleteListener(task -> {
            System.out.println("Got highcores");
            if (task.isSuccessful()) {
                List<Map.Entry<String, Score>> entryList = new ArrayList<>();
                for (DataSnapshot child : task.getResult().getChildren()) {
                    String key = child.getKey();
                    Score score = child.getValue(Score.class);
                    entryList.add(new AbstractMap.SimpleEntry<>(key, score));
                }
                Collections.sort(entryList, new Comparator<Map.Entry<String, Score>>() {
                    @Override
                    public int compare(Map.Entry<String, Score> e1, Map.Entry<String, Score> e2) {
                        return Integer.compare(e2.getValue().getScore(), e1.getValue().getScore());
                    }
                });
                scoreMap.clear();
                for (int i = 0; i < entryList.size(); i++) {
                    Map.Entry<String, Score> entry = entryList.get(i);
                    scoreMap.put(entry.getKey(), entry.getValue());
                }
            } else {
                // Handle error
                Log.e(TAG, "Error getting highscores", task.getException());
            }
        });
    }








    @Override
    public void addHighscore(Score score,  final DataHolderClass dataHolder) {
        myKey = database.getReference("highscores").push().getKey();
        DatabaseReference highscoreRef = database.getReference("highscores/" + myKey);
        highscoreRef.setValue(score);
        //highscores.push().setValue(score);
        System.out.println("HER ER MYKEY: "+ myKey);
        //dataHolder.someValue = myKey;
        dataHolder.setSomeValue(myKey);
    }
}
