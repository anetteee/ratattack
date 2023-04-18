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
import com.ratattack.game.model.shootingStrategy.ShootingStrategy;

public class UpgradeButton extends Observer {

    Button button;
    int id;
    Stage stage = GameController.getInstance().getStage();

    boolean canAffordUpgrade = false;
    int nextPrice = 0;

    Texture upgradeTexture = new Texture("purplebutton.png");
    Texture notUpgradeTexture = new Texture("pinkbutton.png");
    TextureRegionDrawable buttonUpgradeable = new TextureRegionDrawable(new TextureRegion(upgradeTexture));
    TextureRegionDrawable buttonNotUpgradeable = new TextureRegionDrawable(new TextureRegion(notUpgradeTexture));


    public UpgradeButton(int laneWidth, final int i) {
        id = i;

        button = new Button(buttonNotUpgradeable);
        button.setSize(20, 20);
        button.setPosition(laneWidth * i + (float) (laneWidth - upgradeTexture.getWidth()) / 2, GameSettings.grandmotherLine);


        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (canAffordUpgrade) {
                    GameController.getInstance().field.grandmaButtons.get(i).upgrade();
                    nextPrice = min(nextPrice+1 ,4);

                    //If the player can not afford the next upgrade
                    if (!(ShootingStrategy.prices[nextPrice] < GameController.getInstance().getPlayer().getBalance())) {
                        button.getStyle().down = buttonNotUpgradeable;
                        button.getStyle().up = buttonNotUpgradeable;
                        canAffordUpgrade = false;
                    }
                }
            }
        });

        GameController.getInstance().getPlayer().attachBalanceObserver(this);
        stage.addActor(button);
    }

    @Override
    public void update() {
        if (!canAffordUpgrade && (ShootingStrategy.prices[nextPrice] < GameController.getInstance().getPlayer().getBalance())) {
            button.getStyle().down = buttonUpgradeable;
            button.getStyle().up = buttonUpgradeable;
            canAffordUpgrade = true;
        }
    }
}
