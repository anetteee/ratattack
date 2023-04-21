package com.ratattack.game.model.system;

import static com.ratattack.game.model.ComponentMappers.healthMapper;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.components.BalanceComponent;
import com.ratattack.game.model.components.BoundsComponent;
import com.ratattack.game.model.components.BulletEffectComponent;
import com.ratattack.game.model.components.HealthComponent;
import com.ratattack.game.model.components.PositionComponent;
import com.ratattack.game.model.components.RectangleBoundsComponent;
import com.ratattack.game.model.components.SpriteComponent;
import com.ratattack.game.model.components.VelocityComponent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpawnSystem extends IteratingSystem {
    private long lastRatSpawnTime;
    private long lastGrandChildSpawnTime;
    private long ratSpawnInterval;
    private final long grandChildSpawnInterval;

    private final GameController gameController = GameController.getInstance();

    public SpawnSystem() {
        super(Family.all(SpriteComponent.class).get());
        this.ratSpawnInterval = GameSettings.ratSpawnrate;
        this.grandChildSpawnInterval = GameSettings.startGrandChildSpawnrate;
        lastRatSpawnTime = TimeUtils.millis();
        lastGrandChildSpawnTime = TimeUtils.millis();
    }

    @Override
    public void update(float deltaTime) {
        ratSpawnInterval = GameSettings.ratSpawnrate;
        long currentTime = TimeUtils.millis();
        if (currentTime - lastRatSpawnTime >= ratSpawnInterval) {
            spawnRat();
            lastRatSpawnTime = currentTime;
        }
        if (currentTime - lastGrandChildSpawnTime >= grandChildSpawnInterval) {
            spawnGrandChild();
            lastGrandChildSpawnTime = currentTime;
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }




    private void spawnRat() {

        Entity rat = new Entity();
        rat.add(new SpriteComponent());
        rat.add(new VelocityComponent());
        rat.add(new PositionComponent());
        rat.add(new HealthComponent());
        rat.add(new RectangleBoundsComponent());
        rat.add(new BulletEffectComponent());

        Texture texture = new Texture("rat.png");
        rat.getComponent(SpriteComponent.class).sprite = new Sprite(texture);

        rat.getComponent(BulletEffectComponent.class).setEffect("");

        //POSITION:

        PositionComponent position = rat.getComponent(PositionComponent.class);
        position.y = 1500;

        /*
        int antallLanes = gameController.field.laneDividers.size();
        int breddeSkjerm = Gdx.graphics.getWidth();
        int breddRotte = texture.getWidth();

        System.out.println("antall lanes " + antallLanes);
        System.out.println("bredde skjerm " + breddeSkjerm);
        System.out.println("bredde rotte " + breddRotte);

         */


        int indexInLaneDividers = (int) (Math.random()*gameController.field.laneDividers.size());
        System.out.println("random index in lavedividers som brukes: " + indexInLaneDividers);
        position.x = gameController.field.laneDividers.get(indexInLaneDividers) + gameController.field.getLaneWidth()/2 - texture.getWidth()/2;



        //VELOCITY:

        VelocityComponent velocity = rat.getComponent(VelocityComponent.class);
        velocity.x = 0;
        velocity.y = GameSettings.startSpeedRat;

        BoundsComponent bounds = rat.getComponent(RectangleBoundsComponent.class);
        bounds.setSize(2*(texture.getWidth()/3), (texture.getHeight()));
        bounds.setCenter(position.x, position.y);
        rat.getComponent(HealthComponent.class).setHealth(GameSettings.ratStartHealth);

        getEngine().addEntity(rat);
    }


    private void spawnGrandChild() {
        Entity grandChildEntity = new Entity();
        grandChildEntity.add(new SpriteComponent());
        grandChildEntity.add((new VelocityComponent()));
        grandChildEntity.add(new PositionComponent());
        grandChildEntity.add(new HealthComponent());
        grandChildEntity.add(new BalanceComponent());
        grandChildEntity.add(new RectangleBoundsComponent());

        //Add position, velocity, health, balance and sprite
        Texture texture = new Texture("grandchild.png");
        grandChildEntity.getComponent(SpriteComponent.class).sprite = new Sprite(texture);



        //POSITION
        PositionComponent position = grandChildEntity.getComponent(PositionComponent.class);

        int indexInLaneDividers = (int) (Math.random()*gameController.field.laneDividers.size());
        System.out.println("random index in lavedividers som brukes: " + indexInLaneDividers);
        position.x = gameController.field.laneDividers.get(indexInLaneDividers) + gameController.field.getLaneWidth()/2 - texture.getWidth()/2;
        position.y = 1500;


        //VELOCITY
        VelocityComponent velocity = grandChildEntity.getComponent(VelocityComponent.class);
        velocity.x = 0;
        velocity.y = GameSettings.startSpeedGrandchild;

        BoundsComponent bounds = grandChildEntity.getComponent(RectangleBoundsComponent.class);
        bounds.setSize(2*(texture.getWidth()/3), (texture.getHeight()));
        bounds.setCenter(position.x, position.y);

        grandChildEntity.getComponent(BalanceComponent.class).setBalance(500);

        HealthComponent health = healthMapper.get(grandChildEntity);
        health.setHealth(GameSettings.grandChildStartHealth);

        getEngine().addEntity(grandChildEntity);
    }
}
