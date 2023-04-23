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

import java.util.zip.ZipEntry;

public class UpgradeButton extends Observer {

    Button button;
    int id;
    Stage stage = GameController.getInstance().getStage();

    boolean canAffordUpgrade = false;
    int nextUpgrade = 0;

    Texture grandmotherTexture = new Texture("grandma_normal_bullet.png");

    String[] upgradeButtonTextures = {
            "btn_upgrade_fast_bullet.png",
            "btn_upgrade_freeze_bullet.png",
            "btn_upgrade_big_bullet.png",
            "btn_upgrade_triple_bullet.png"};

    TextureRegionDrawable buttonUpgradeable = new TextureRegionDrawable(new TextureRegion(new Texture(upgradeButtonTextures[0])));
    TextureRegionDrawable buttonNotUpgradeable = new TextureRegionDrawable(new TextureRegion(new Texture("btn_no_upgrade.png")));


    public UpgradeButton(int laneWidth, final int i) {
        id = i;

        button = new Button(buttonNotUpgradeable);
        button.setSize(90, 110 );
        button.setPosition(laneWidth * i + (float) (laneWidth/2)+(0.45f*grandmotherTexture.getWidth()), GameSettings.grandmotherLine + 50);


        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (canAffordUpgrade) {
                    GameController.getInstance().field.grandmaButtons.get(i).upgrade();
                    Player.setBalance(Player.getBalance() - ShootingStrategy.prices[nextUpgrade]);
                    nextUpgrade = min(nextUpgrade +1 ,4);

                    // Changing the texture of the button
                    if (nextUpgrade < upgradeButtonTextures.length) {
                        buttonUpgradeable = new TextureRegionDrawable(new TextureRegion(new Texture(upgradeButtonTextures[nextUpgrade])));
                    }

                    // If the player can not afford the next upgrade
                    if (!(ShootingStrategy.prices[nextUpgrade] < Player.getBalance())) {
                        button.getStyle().down = buttonNotUpgradeable;
                        button.getStyle().up = buttonNotUpgradeable;
                        canAffordUpgrade = false;
                    }
                    // The player can afford the next upgrade
                    else {
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
        if (!canAffordUpgrade && (ShootingStrategy.prices[nextUpgrade] <= Player.getBalance()) && (nextUpgrade < upgradeButtonTextures.length)) {
            button.getStyle().down = buttonUpgradeable;
            button.getStyle().up = buttonUpgradeable;
            canAffordUpgrade = true;
        }

        if(ShootingStrategy.prices[nextUpgrade] > Player.getBalance()) {
            button.getStyle().down = buttonNotUpgradeable;
            button.getStyle().up = buttonNotUpgradeable;
            canAffordUpgrade = false;
        }
    }

    public Button getButton() {
        return button;
    }
}
