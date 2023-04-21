package com.ratattack.game.model.shootingStrategy;

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
import com.ratattack.game.model.ecs.components.CircleBoundsComponent;
import com.ratattack.game.model.ecs.components.PositionComponent;
import com.ratattack.game.model.ecs.components.SpriteComponent;
import com.ratattack.game.model.ecs.components.StrengthComponent;
import com.ratattack.game.model.ecs.components.VelocityComponent;

import java.util.ArrayList;

public class TripleBulletStrategy implements ShootingStrategy {

    Texture bulletTexture = new Texture("bullet.png");

    @Override
    public void shoot(int x, int y) {
        ArrayList<Entity> bullets = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            bullets.add(GameController.getInstance().getAshleyWorld().createBullet());
        }

        for (Entity bullet: bullets) {
            PositionComponent position = positionMapper.get(bullet);
            SpriteComponent sprite = spriteMapper.get(bullet);
            StrengthComponent strength = strengthMapper.get(bullet);
            CircleBoundsComponent bounds = circleBoundsMapper.get(bullet);
            //PowerUpComponent powerUp = powerUpMapper.get(bullet);

            position.x = x;
            position.y = y;

            sprite.sprite = new Sprite(bulletTexture);

            strength.strength = GameSettings.tripleBulletStrength;

            bounds.setCenter(x, y);
            bounds.setSize(bulletTexture.getWidth()/2, bulletTexture.getHeight()/2);
        }

        //Setter velocity til hver kule
        VelocityComponent velocity;
        velocity = velocityMapper.get(bullets.get(0));
        velocity.x = -5;
        velocity.y = 5;

        velocity = velocityMapper.get(bullets.get(1));
        velocity.x = 0;
        velocity.y = 7;

        velocity = velocityMapper.get(bullets.get(2));
        velocity.x = 5;
        velocity.y = 5;
    }
}
