package com.ratattack.game.view.screenState;

import com.badlogic.gdx.Screen;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.system.SpawnSystem;
import com.ratattack.game.view.screens.ScreenFactory;

public class MenuState implements State {

    /***
     * TODO: LEGG TIL KOMMENTARER
     * */

    private final ScreenContext screenContext;
    private Screen currentScreen;

    public MenuState(ScreenContext stateManager) {
        this.screenContext = stateManager;
        currentScreen = ScreenFactory.getScreen("MENU");

        renderScreen();

    }

    @Override
    public void changeState(State state) {
        screenContext.changeState(state);

    }


    @Override
    public boolean shouldChangeState(String type) {

        return type.equalsIgnoreCase("HIGHSCORE") ||
                type.equalsIgnoreCase("GAMERULES")
                || type.equalsIgnoreCase("NAME");
    }
    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            if (type.equalsIgnoreCase("NAME")) {
                State state = new NameState(screenContext);
                changeState(state);
            } else if (type.equalsIgnoreCase("HIGHSCORE")) {
                State state = new HighScoreState(screenContext);
                changeState(state);
            } else if (type.equalsIgnoreCase("GAMERULES")) {
                State state = new GameRulesState(screenContext);
                changeState(state);
            }
        } else {
            currentScreen = ScreenFactory.getScreen(type);
            renderScreen();
        }
    }

    @Override
    public void renderScreen() {
        GameController.getInstance().getEngine().getSystem(SpawnSystem.class).setProcessing(false);
        GameController.getInstance().getGame().setScreen(currentScreen);


    }


}
