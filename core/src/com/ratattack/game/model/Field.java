package com.ratattack.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.buttons.GrandmotherButton;
import com.ratattack.game.model.buttons.UpgradeButton;

import java.util.ArrayList;

public class Field {

    SpriteBatch batch = GameController.getInstance().getBatch();

    Texture lane = new Texture("lane.png");
    Texture backgroundBox; // For bakgrunnen til highScore og balanse

    public ArrayList<Integer> laneDividers = new ArrayList<>();
    public ArrayList<GrandmotherButton> grandmaButtons = new ArrayList<>();
    public ArrayList<UpgradeButton> upgradeButtons = new ArrayList<>();

    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();

    public Field() {
        // Setter opp hvor lanesene er separert.
        // Brukes til å finne ut hvor de skal tegnes, og hvor enheter skal spawne/tegnes.
        int laneWidth = width/GameSettings.laneNr;
        for (int i = 0; i < GameSettings.laneNr; i++) {
            laneDividers.add(laneWidth*i);

            GrandmotherButton grandmaBtn = new GrandmotherButton(laneWidth, i);
            grandmaButtons.add(grandmaBtn);

            UpgradeButton upgradeBtn = new UpgradeButton(laneWidth, i);
            upgradeButtons.add(upgradeBtn);

            GameController.getInstance().getStage().addActor(grandmaBtn.getButton());
            }

        //backgroundBox = new Texture("backgroundBox.png");
    }

    public void draw() {
        batch.begin();
        for (Integer i : laneDividers) {
            batch.draw(lane, i, 0, (float) width/GameSettings.laneNr, height);
        }
        batch.end();
    }
}
