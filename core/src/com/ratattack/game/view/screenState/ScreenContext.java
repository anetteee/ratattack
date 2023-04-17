package com.ratattack.game.view.screenState;

import com.ratattack.game.gamecontroller.GameController;

import java.util.Stack;

public class ScreenContext {


    /***
     * TODO: LEGG TIL KOMMENTARER
     * */

    public Stack<State> states;
    public GameController gameController = GameController.getInstance();

    /***
     * This class manages all of the states and keeps track of current state in the stack.
     * state top of the stack gets rendered.
     */
    public ScreenContext() {
        states  = new Stack<>();
    }

    public void push(State state){
        states.push(state);
    }

    private void pop(){
        states.pop();
    }

    void changeState(State state){
        pop();
        states.push(state);
    }

    public void changeScreen(String type){
        gameController.getStage().clear();
        gameController.getEngine().removeAllEntities();
        states.peek().changeScreen(type);
        System.out.println("Dette er state id-en" + states.peek());

    }

}
