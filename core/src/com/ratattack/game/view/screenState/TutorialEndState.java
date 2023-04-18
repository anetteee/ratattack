package com.ratattack.game.view.screenState;

import com.badlogic.gdx.Screen;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.system.SpawnSystem;
import com.ratattack.game.view.screens.ScreenFactory;

public class TutorialEndState implements State {
    private ScreenContext screenContext;
    private Screen currentScreen;

    public TutorialEndState(ScreenContext screenContext) {
        this.screenContext = screenContext;
        currentScreen = ScreenFactory.getScreen("TUTORIALEND");

        renderScreen();
    }
    @Override
    public void changeState(State state) {
        screenContext.changeState(state);
    }

    @Override
    public boolean shouldChangeState(String type) {
        return  type.equalsIgnoreCase("MENU") ||
                type.equalsIgnoreCase("TUTORIAL") ||
                type.equalsIgnoreCase("NAME");
    }

    @Override
    public void changeScreen(String type) {

        if(shouldChangeState(type)){
            if (type.equalsIgnoreCase("MENU")) {
                State state = new MenuState(screenContext);
                changeState(state);
            } else if (type.equalsIgnoreCase("TUTORIAL")) {
                State state = new TutorialState(screenContext);
                changeState(state);
            } else if (type.equalsIgnoreCase("NAME")) {
                State state = new NameState(screenContext);
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
