package com.ratattack.game;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// Her er logikk for Firebase
public class AndroidInterfaceClass implements FirebaseInterface {
    FirebaseDatabase database;

    DatabaseReference highscores;
    String myKey;

    public AndroidInterfaceClass() {

        database = FirebaseDatabase.getInstance();
        highscores = database.getReference("highscores");
    }


    @Override
    public void getHighscores(HashMap<String, Score> scoreMap) {
        System.out.println("Getting highcores");
        highscores.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("Got highcores");
                Iterable<DataSnapshot> response = task.getResult().getChildren();
                List<Score> scores = new ArrayList<>();
                for (DataSnapshot child : response) {
                    Score score = child.getValue(Score.class);
                    scores.add(score);
                }
                Collections.sort(scores);
                for (int i = 0; i < scores.size(); i++) {
                    scoreMap.put(String.valueOf(i), scores.get(i));
                }
            }
        });
    }




    @Override
    public void addHighscore(Score score,  final DataHolderClass dataHolder) {
        myKey = database.getReference("highscores").push().getKey();
        highscores.push().setValue(score);
        System.out.println("HER ER MYKEY: "+ myKey);
        dataHolder.someValue = myKey;
    }
}
