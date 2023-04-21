package com.ratattack.game.view.screenState;

import com.badlogic.gdx.Screen;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.ecs.system.SpawnSystem;
import com.ratattack.game.view.screens.ScreenFactory;

public class NameState implements State {

    private final ScreenContext screenContext;
    private Screen currentScreen;


    public NameState(ScreenContext screenContext) {
        this.screenContext = screenContext;

        currentScreen = ScreenFactory.getScreen("NAME");

        renderScreen();
    }

    @Override
    public void changeState(State state) {

        screenContext.changeState(state);

    }

    @Override
    public boolean shouldChangeState(String type) {

        return type.equalsIgnoreCase("GAME");
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            if (type.equalsIgnoreCase("GAME")) {
                State state = new GameState(screenContext);
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
