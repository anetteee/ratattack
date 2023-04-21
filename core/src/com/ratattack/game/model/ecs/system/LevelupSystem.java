package com.ratattack.game.model.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.Player;
import com.ratattack.game.model.ecs.components.BalanceComponent;
import com.ratattack.game.model.ecs.components.HealthComponent;
import com.ratattack.game.model.ecs.components.VelocityComponent;

public class LevelupSystem extends IteratingSystem {

    /***
     * TODO: LEGG TIL KOMMENTARER
     * */

    int level = 0;

    double timeSinceLevelup = Double.POSITIVE_INFINITY;
    Texture texture = new Texture("r.png");

    SpriteBatch batch = GameController.getInstance().getBatch();

    public LevelupSystem() {
        super(Family.all(HealthComponent.class).exclude(BalanceComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        timeSinceLevelup += (double) deltaTime;
        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // Make game more difficult by increasing the speed of all rats
        VelocityComponent velocity = entity.getComponent(VelocityComponent.class);

        if ((Player.getScore() >= GameSettings.changeLevelScore[level]) && (level < GameSettings.ratSpeed.length - 1)) {
            level += 1; // Upgrade one level
            timeSinceLevelup = 0;
            GameSettings.ratSpawnrate = GameSettings.spawnRates[level];
        }

        velocity.y = GameSettings.ratSpeed[level];

        //Show feedback to the user
        if(timeSinceLevelup < 2) {
            batch.begin();
            batch.draw(texture, (Gdx.graphics.getWidth()/2)-texture.getWidth()/2, (Gdx.graphics.getHeight()/2)-texture.getHeight()/2, texture.getWidth(),texture.getHeight());
            batch.end();
        }
    }

    public void setLevel(int i) {
        level = i;
    }
}
