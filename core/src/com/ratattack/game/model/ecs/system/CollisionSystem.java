package com.ratattack.game.model.ecs.system;

import static com.ratattack.game.model.ecs.ComponentMappers.bulletEffectMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.circleBoundsMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.healthMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.rectangleBoundsMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.strengthMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.velocityMapper;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.ecs.components.BalanceComponent;
import com.ratattack.game.model.ecs.components.BoundsComponent;
import com.ratattack.game.model.ecs.components.BulletEffectComponent;
import com.ratattack.game.model.ecs.components.CircleBoundsComponent;
import com.ratattack.game.model.ecs.components.HealthComponent;
import com.ratattack.game.model.ecs.components.SpriteComponent;
import com.ratattack.game.model.ecs.components.StrengthComponent;
import com.ratattack.game.model.ecs.components.VelocityComponent;


public class CollisionSystem extends IteratingSystem {

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

                //Sjekk om kula har en powerup, og apply effekten til entiteten
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
                    }

                    getEngine().removeEntity(hittableEntity);
                    hittableEntity.getComponent(SpriteComponent.class).sprite.getTexture().dispose();
                }

                //Remove bullet
                getEngine().removeEntity(entity);
            }
        }
    }
}
