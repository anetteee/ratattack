package com.ratattack.game.gamecontroller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.ratattack.game.model.Player;

public class UsernameTextInputListener implements Input.TextInputListener {

    @Override
    public void input(String userName) {
        GameController.getInstance().getPlayer().setName(userName);
        Player.setBalance(0);
        Player.setScore(0);
        GameController.getInstance().screenContext.changeScreen("GAME");
    }

    @Override
    public void canceled() {
        GameController.getInstance().screenContext.changeScreen("MENU");
        GameController.getInstance().pause();
    }
}
