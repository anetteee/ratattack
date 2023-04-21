package com.ratattack.game.model.shootingStrategy;

import static com.ratattack.game.model.ecs.ComponentMappers.bulletEffectMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.circleBoundsMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.positionMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.spriteMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.strengthMapper;
import static com.ratattack.game.model.ecs.ComponentMappers.velocityMapper;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.ecs.components.BulletEffectComponent;
import com.ratattack.game.model.ecs.components.CircleBoundsComponent;
import com.ratattack.game.model.ecs.components.PositionComponent;
import com.ratattack.game.model.ecs.components.SpriteComponent;
import com.ratattack.game.model.ecs.components.StrengthComponent;
import com.ratattack.game.model.ecs.components.VelocityComponent;

public class FreezeBulletStrategy implements ShootingStrategy{
    Texture bulletTexture = new Texture("freezebullet.png");
    @Override
    public void shoot(int x, int y) {
        Entity bullet = GameController.getInstance().getAshleyWorld().createBullet();

        PositionComponent position = positionMapper.get(bullet);
        VelocityComponent velocity = velocityMapper.get(bullet);
        SpriteComponent sprite = spriteMapper.get(bullet);
        StrengthComponent strength = strengthMapper.get(bullet);
        CircleBoundsComponent bounds = circleBoundsMapper.get(bullet);
        BulletEffectComponent effect = bulletEffectMapper.get(bullet);

        effect.setEffect("FREEZE");

        position.x = x;
        position.y = y;

        velocity.x = 0;
        velocity.y = 10;

        sprite.sprite = new Sprite(bulletTexture);
        sprite.sprite.setSize(20, 20);

        strength.strength = GameSettings.freezeBulletStrength;

        bounds.setCenter(x, y);
        bounds.setSize(bulletTexture.getWidth()/2, bulletTexture.getHeight()/2);

    }
}
