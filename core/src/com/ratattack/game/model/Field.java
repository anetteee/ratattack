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
    //KOMMENTERTE UT DENNETexture backgroundBox; // For bakgrunnen til highScore og balanse

    public ArrayList<Integer> laneDividers = new ArrayList<>();
    public ArrayList<GrandmotherButton> grandmaButtons = new ArrayList<>();
    public ArrayList<UpgradeButton> upgradeButtons = new ArrayList<>();

    int widthOfScreen = Gdx.graphics.getWidth();
    int heightOfScreen = Gdx.graphics.getHeight();


    int laneWidth;

    public Field(int laneNumber) {
        // Setter opp hvor lanesene er separert.
        // Brukes til å finne ut hvor de skal tegnes, og hvor enheter skal spawne/tegnes.

        laneWidth = widthOfScreen /laneNumber;
        for (int i = 0; i < laneNumber; i++) {
            laneDividers.add(laneWidth*i);

            GrandmotherButton grandmaBtn = new GrandmotherButton(laneWidth, i);
            grandmaButtons.add(grandmaBtn);

            UpgradeButton upgradeBtn = new UpgradeButton(laneWidth, i);
            upgradeButtons.add(upgradeBtn);

            GameController.getInstance().getStage().addActor(grandmaBtn.getButton());
        }

    }

    public int getLaneWidth(){
        return laneWidth;
    }






    public void draw(int laneNr) {
        batch.begin();
        for (Integer i : laneDividers) {
            //opprinnelig
            //batch.draw(lane, i, 0, (float) width/GameSettings.laneNr, height);
            //mitt
            batch.draw(lane, i, 0, (float) widthOfScreen/laneNr, heightOfScreen);
        }
        batch.end();
    }
}
