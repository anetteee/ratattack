package com.ratattack.game.view.screenState;

import com.badlogic.gdx.Screen;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.system.SpawnSystem;
import com.ratattack.game.view.screens.ScreenFactory;

public class GameRulesState implements State {
    /***
     * TODO: LEGG TIL KOMMENTARER
     * */
    private ScreenContext screenContext;
    private Screen currentScreen;

    public GameRulesState(ScreenContext screenContext) {
        this.screenContext = screenContext;
        currentScreen = ScreenFactory.getScreen("GAMERULES");

        renderScreen();
    }

    @Override
    public void changeState(State state) {
        screenContext.changeState(state);
    }

    @Override
    public boolean shouldChangeState(String type) {
        return type.equalsIgnoreCase("TUTORIAL");
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)) {
            if (type.equalsIgnoreCase("TUTORIAL")) {
                State state = new TutorialState(screenContext);
                changeState(state);
            }
        } else {
            currentScreen = ScreenFactory.getScreen(type);
            renderScreen();
        }
    }
    @Override
    public void renderScreen() {
        //GameController.getInstance().getEngine().getSystem(SpawnSystem.class).setProcessing(false);
        GameController.getInstance().getGame().setScreen(currentScreen);
    }
}
