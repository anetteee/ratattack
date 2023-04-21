package com.ratattack.game.model.shootingStrategy;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public interface ShootingStrategy {

    ShootingStrategy[] strategies = {new NormalBulletStrategy(), new FastBulletStrategy(), new FreezeBulletStrategy(), new BigBulletStrategy(), new TripleBulletStrategy()};
    //Texture[] strategyTextures = {new Texture("bullet.png"), new Texture("bullet.png"), new Texture("freezebullet.png"), new Texture("BIGbullet.png"), new Texture("bullet.png")};
    String[] strategyTextures = {"normalbulletgrandma.png", "fastbulletgrandma.png", "freezebulletgrandma.png", "bigbulletgrandma.png", "triplebulletgrandma.png"};
    int[] prices = {100, 150, 200, 250, 300};

    void shoot(int x, int y);
}
