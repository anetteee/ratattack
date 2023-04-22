package com.ratattack.game.model.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.ratattack.game.model.ecs.components.BalanceComponent;
import com.ratattack.game.model.ecs.components.BulletEffectComponent;
import com.ratattack.game.model.ecs.components.CircleBoundsComponent;
import com.ratattack.game.model.ecs.components.HealthComponent;
import com.ratattack.game.model.ecs.components.PositionComponent;
import com.ratattack.game.model.ecs.components.RectangleBoundsComponent;
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

    public Entity createRat() {
        Entity rat = new Entity();
        rat.add(new SpriteComponent());
        rat.add(new VelocityComponent());
        rat.add(new PositionComponent());
        rat.add(new HealthComponent());
        rat.add(new RectangleBoundsComponent());
        rat.add(new BulletEffectComponent());
        engine.addEntity(rat);
        return rat;
    }

    public Entity createGrandChild() {
        Entity grandChildEntity = new Entity();
        grandChildEntity.add(new SpriteComponent());
        grandChildEntity.add((new VelocityComponent()));
        grandChildEntity.add(new PositionComponent());
        grandChildEntity.add(new HealthComponent());
        grandChildEntity.add(new BalanceComponent());
        grandChildEntity.add(new RectangleBoundsComponent());
        engine.addEntity(grandChildEntity);
        return grandChildEntity;
    }

}
