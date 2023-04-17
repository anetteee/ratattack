package com.ratattack.game.view.screenState;

import com.badlogic.gdx.Screen;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.system.SpawnSystem;
import com.ratattack.game.view.screens.ScreenFactory;

public class HighScoreState implements State {

    private final ScreenContext screenContext;
    private Screen currentScreen;


    public HighScoreState(ScreenContext screenContext) {
        this.screenContext = screenContext;
        currentScreen = ScreenFactory.getScreen("HIGHSCORE");

        renderScreen();
    }

    @Override
    public void changeState(State state) {
        screenContext.changeState(state);
    }

    @Override
    public boolean shouldChangeState(String type) {
        return type.equalsIgnoreCase("MENU");
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            State state = new MenuState(screenContext);
            changeState(state);
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
