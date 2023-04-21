package com.ratattack.game.model.system;

import static com.ratattack.game.model.ComponentMappers.bulletEffectMapper;
import static com.ratattack.game.model.ComponentMappers.circleBoundsMapper;
import static com.ratattack.game.model.ComponentMappers.healthMapper;
import static com.ratattack.game.model.ComponentMappers.positionMapper;
import static com.ratattack.game.model.ComponentMappers.rectangleBoundsMapper;
import static com.ratattack.game.model.ComponentMappers.strengthMapper;
import static com.ratattack.game.model.ComponentMappers.velocityMapper;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.components.BalanceComponent;
import com.ratattack.game.model.components.BoundsComponent;
import com.ratattack.game.model.components.BulletEffectComponent;
import com.ratattack.game.model.components.CircleBoundsComponent;
import com.ratattack.game.model.components.HealthComponent;
import com.ratattack.game.model.components.PositionComponent;
import com.ratattack.game.model.components.StrengthComponent;
import com.ratattack.game.model.components.VelocityComponent;


public class CollisionSystem extends IteratingSystem {

    PooledEngine engine;

    private static final Family hittableEntitiesFamily = Family.all(HealthComponent.class).get();
    private static final Family bulletEntitiesFamily = Family.all(CircleBoundsComponent.class).get();

    public CollisionSystem() {
        super(bulletEntitiesFamily);
    }
    private final GameController gameController = GameController.getInstance();


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (entity.isScheduledForRemoval()) return;

        BoundsComponent bounds = circleBoundsMapper.get(entity); //Skal byttes til circlebounds når vi bare ser på bullets

        ImmutableArray<Entity> hittableEntities = getEngine().getEntitiesFor(hittableEntitiesFamily);

        if (bounds == null) return;

        for (Entity hittableEntity : hittableEntities) {
            if (hittableEntity.isScheduledForRemoval()) continue;

            //Get the other entity´s bounds
            BoundsComponent otherBounds = rectangleBoundsMapper.get(hittableEntity);

            if (otherBounds == null) continue;

            if (bounds.overlaps(otherBounds)) {

                //bullet er entity, hittable er den rat/child
                StrengthComponent hitStrength = strengthMapper.get(entity);
                HealthComponent entityHealth = healthMapper.get(hittableEntity);
                BulletEffectComponent bulletEffect = bulletEffectMapper.get(entity);

                entityHealth.setHealth((entityHealth.getHealth()-hitStrength.strength));

                //Legg inn sjekk av om kula har en powerup, og apply effekten til entiteten

                if (bulletEffect.getEffect().equals("FREEZE")) {

                    VelocityComponent velocity = velocityMapper.get(hittableEntity);
                    velocity.y = GameSettings.freezeVelocity;
                }

                //Remove entity if it has lost all health
                if (entityHealth.getHealth() <= 0) {

                    // Add score if rat is shot
                    if (hittableEntity.getComponent(BalanceComponent.class) == null) {

                        // Update highscore on grandchild arriving
                        int scoreFromGrandchild = 10;
                        int playerScore = gameController.getPlayer().getScore();
                        int updateScore = scoreFromGrandchild + playerScore;
                        gameController.getPlayer().setScore(updateScore);
                        System.out.println(gameController.getPlayer().getScore());
                    }

                    getEngine().removeEntity(hittableEntity);
                }

                //Remove bullet
                getEngine().removeEntity(entity);
            }
        }
    }
}
