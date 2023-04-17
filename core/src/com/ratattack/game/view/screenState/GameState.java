package com.ratattack.game.view.screenState;

import com.badlogic.gdx.Screen;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.system.SpawnSystem;
import com.ratattack.game.view.screens.ScreenFactory;

public class GameState implements State {


    /***
     * TODO: LEGG TIL KOMMENTARER
     * */
    private final ScreenContext screenContext;
    private Screen currentScreen;

    public GameState(ScreenContext screenContext) {
        this.screenContext = screenContext;
        currentScreen = ScreenFactory.getScreen("GAME");

        renderScreen();
    }

    @Override
    public void changeState(State state) {

        screenContext.changeState(state);
    }

    @Override
    public boolean shouldChangeState(String type) {

        return type.equalsIgnoreCase("MENU") ||
                type.equalsIgnoreCase("TUTORIAL") ;
    }

    @Override
    public void changeScreen(String type) {

        if(shouldChangeState(type)){
            State state = type.equalsIgnoreCase("MENU") ? new MenuState(screenContext): new TutorialState(screenContext);
            changeState(state);
        } else {
            currentScreen = ScreenFactory.getScreen(type);
            renderScreen();
        }

    }

    @Override
    public void renderScreen() {
        GameController.getInstance().getEngine().getSystem(SpawnSystem.class).setProcessing(true);
        GameController.getInstance().getGame().setScreen(currentScreen);
    }
}
