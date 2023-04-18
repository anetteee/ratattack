package com.ratattack.game.view.screenState;

import com.badlogic.gdx.Screen;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.system.SpawnSystem;
import com.ratattack.game.view.screens.ScreenFactory;

public class TutorialState implements State {


    /***
     * TODO: LEGG TIL KOMMENTARER
     * */


    private ScreenContext screenContext;
    private Screen currentScreen;

    public TutorialState(ScreenContext screenContext) {
        this.screenContext = screenContext;
        currentScreen = ScreenFactory.getScreen("TUTORIAL");

        renderScreen();

    }

    @Override
    public void changeState(State state) {
        screenContext.changeState(state);

    }

    @Override
    public boolean shouldChangeState(String type) {
        return type.equalsIgnoreCase("GAME") ||
                type.equalsIgnoreCase("MENU") ;
    }

    @Override
    public void changeScreen(String type) {
        if(shouldChangeState(type)){
            State state = type.equalsIgnoreCase("GAME") ? new GameState(screenContext): new MenuState(screenContext);
            changeState(state);
        }
        else {
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
