package com.ratattack.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.ratattack.game.GameSettings;
import com.ratattack.game.backend.DataHolderClass;
import com.ratattack.game.backend.FirebaseInterface;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.system.LevelupSystem;
import com.ratattack.game.view.screenState.GameRulesState;

public class ScreenFactory {

    static FirebaseInterface _FBIC =  GameController.getInstance().getFirebaseInterface();
    static DataHolderClass dataHolder = GameController.getInstance().getDataHolderClass();

    public static Screen getScreen(String screenType) {
        switch (screenType) {
            case "MENU":
                return new MenuScreen(_FBIC, dataHolder);
            case "NAME":
                return new NameScreen();
            case "GAME":
                GameController.getInstance().getEngine().getSystem(LevelupSystem.class).setLevel(0);
                GameSettings.ratSpawnrate = GameSettings.startRatSpawnRate;
                return new GameScreen();
            case "TUTORIAL":
                GameController.getInstance().getEngine().getSystem(LevelupSystem.class).setLevel(0);
                GameSettings.ratSpawnrate = GameSettings.startRatSpawnRate;
                return new TutorialScreen();
            case "HIGHSCORE":
                return new HighscoreScreen(_FBIC, dataHolder);
            case "TUTORIALEND":
                return new TutorialEndScreen();
            case "GAMERULES":
                return new GameRulesScreen();
            default:
                return null;
        }
    }
}
