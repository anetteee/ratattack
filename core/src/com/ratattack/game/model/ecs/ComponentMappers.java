package com.ratattack.game.model.ecs;

import com.badlogic.ashley.core.ComponentMapper;
import com.ratattack.game.model.ecs.components.CircleBoundsComponent;
import com.ratattack.game.model.ecs.components.HealthComponent;
import com.ratattack.game.model.ecs.components.PositionComponent;
import com.ratattack.game.model.ecs.components.RectangleBoundsComponent;
import com.ratattack.game.model.ecs.components.SpriteComponent;
import com.ratattack.game.model.ecs.components.BulletEffectComponent;
import com.ratattack.game.model.ecs.components.StrengthComponent;
import com.ratattack.game.model.ecs.components.UserComponent;
import com.ratattack.game.model.ecs.components.VelocityComponent;

public abstract class ComponentMappers {

    public static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<SpriteComponent> spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
    public static final ComponentMapper<UserComponent> userMapper = ComponentMapper.getFor(UserComponent.class);
    public static final ComponentMapper<RectangleBoundsComponent> rectangleBoundsMapper = ComponentMapper.getFor(RectangleBoundsComponent.class);
    public static final ComponentMapper<CircleBoundsComponent> circleBoundsMapper = ComponentMapper.getFor(CircleBoundsComponent.class);
    public static final ComponentMapper<StrengthComponent> strengthMapper = ComponentMapper.getFor(StrengthComponent.class);
    public static final ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);

    public static final ComponentMapper<BulletEffectComponent> bulletEffectMapper = ComponentMapper.getFor(BulletEffectComponent.class);
}
