package com.ratattack.game.model.shootingStrategy;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public interface ShootingStrategy {

    ShootingStrategy[] strategies = {new NormalBulletStrategy(), new FastBulletStrategy(), new FreezeBulletStrategy(), new BigBulletStrategy(), new TripleBulletStrategy()};
    String[] strategyTextures = {"bullet.png", "bullet.png", "freezebullet.png", "BIGbullet.png", "bullet.png"};
    //String[] strategyTextures = {"normalGrandma.png", "fastGrandma.png", "freezeGrandma.png", "bigGrandma.png", "tripleGrandma.png"};
    int[] prices = {100, 200, 300, 400, 500};

    void shoot(int x, int y);
}
