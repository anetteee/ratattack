package com.ratattack.game.model.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.ratattack.game.model.ecs.components.BulletEffectComponent;
import com.ratattack.game.model.ecs.components.CircleBoundsComponent;
import com.ratattack.game.model.ecs.components.PositionComponent;
import com.ratattack.game.model.ecs.components.SpriteComponent;
import com.ratattack.game.model.ecs.components.StrengthComponent;
import com.ratattack.game.model.ecs.components.VelocityComponent;

public class GameWorld {

    final PooledEngine engine;

    public GameWorld(PooledEngine engine) {
        this.engine = engine;
    }

    public Entity createBullet() {
        Entity bulletEntity = new Entity();
        bulletEntity.add(new PositionComponent());
        bulletEntity.add(new VelocityComponent());
        bulletEntity.add(new SpriteComponent());
        bulletEntity.add(new StrengthComponent());
        bulletEntity.add(new CircleBoundsComponent());
        bulletEntity.add(new BulletEffectComponent());
        engine.addEntity(bulletEntity);
        return bulletEntity;
    }

}
