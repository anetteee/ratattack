package com.ratattack.game.model.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.shootingStrategy.NormalBulletStrategy;
import com.ratattack.game.model.shootingStrategy.ShootingStrategy;

public class GrandmotherButton {

    Button button;
    ShootingStrategy strategy;
    Stage stage = GameController.getInstance().getStage();
    Texture grandMotherTexture = new Texture("normalbulletgrandma.png");

    int id;
    int currentUpgrade = 0;

    public GrandmotherButton(int laneWidth, int i) {
        id = i;

        button = new Button(new TextureRegionDrawable(new TextureRegion(grandMotherTexture)));
        //button.setSize(grandMotherTexture.getWidth(),   grandMotherTexture.getHeight());
        button.setSize((3*laneWidth)/4,   (laneWidth)/3);
        button.setPosition(laneWidth*i + (float)(laneWidth-grandMotherTexture.getWidth())/4, GameSettings.grandmotherLine);

        strategy = new NormalBulletStrategy();

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                strategy.shoot((int) button.getX() + grandMotherTexture.getWidth()/3, (int) button.getY());
            }
        });

        stage.addActor(button);
    }

    public Button getButton() {
        return button;
    }

    public void upgrade() {
        //Må sjekke om balansen er høy nok for å betale for oppgraderinga
        // Må inkludere prisen for oppgraderinga
        if (currentUpgrade != ShootingStrategy.strategies.length - 1) {
            currentUpgrade += 1;
            strategy = ShootingStrategy.strategies[currentUpgrade];
        }
    }
}
