package com.ratattack.game.model.buttons;

import static java.lang.Math.min;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.gamecontroller.Observer;
import com.ratattack.game.model.Player;
import com.ratattack.game.model.shootingStrategy.ShootingStrategy;

public class UpgradeButton extends Observer {

    Button button;
    int id;
    Stage stage = GameController.getInstance().getStage();

    boolean canAffordUpgrade = false;
    int nextUpgrade = 0;

    String[] upgradeButtonTextures = {"bullet.png", "bullet.png", "freezebullet.png", "BIGbullet.png", "bullet.png"};

    TextureRegionDrawable buttonUpgradeable = new TextureRegionDrawable(new TextureRegion(new Texture(upgradeButtonTextures[0])));
    TextureRegionDrawable buttonNotUpgradeable = new TextureRegionDrawable(new TextureRegion(new Texture("pinkbutton.png")));


    public UpgradeButton(int laneWidth, final int i) {
        id = i;

        button = new Button(buttonNotUpgradeable);
        button.setSize(50, 50);
        button.setPosition(laneWidth * i + (float) (laneWidth - buttonUpgradeable.getRegion().getRegionWidth()) / 2, GameSettings.grandmotherLine);


        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (canAffordUpgrade) {
                    GameController.getInstance().field.grandmaButtons.get(i).upgrade();
                    Player.setBalance(Player.getBalance() - ShootingStrategy.prices[nextUpgrade]);
                    nextUpgrade = min(nextUpgrade +1 ,4);

                    //If the player can not afford the next upgrade
                    if (!(ShootingStrategy.prices[nextUpgrade] < Player.getBalance())) {
                        button.getStyle().down = buttonNotUpgradeable;
                        button.getStyle().up = buttonNotUpgradeable;
                        canAffordUpgrade = false;
                    }
                    else { //Spilleren har råd til neste oppgradering
                        buttonUpgradeable = new TextureRegionDrawable(new TextureRegion(new Texture(upgradeButtonTextures[nextUpgrade])));
                        button.getStyle().down = buttonUpgradeable;
                        button.getStyle().up = buttonUpgradeable;
                    }
                }
            }
        });

        GameController.getInstance().getPlayer().attachBalanceObserver(this);
        stage.addActor(button);
    }

    @Override
    public void update() {
        if (!canAffordUpgrade && (ShootingStrategy.prices[nextUpgrade] < Player.getBalance())) {
            button.getStyle().down = buttonUpgradeable;
            button.getStyle().up = buttonUpgradeable;
            canAffordUpgrade = true;
        }
    }

    public Button getButton() {
        return button;
    }
}
