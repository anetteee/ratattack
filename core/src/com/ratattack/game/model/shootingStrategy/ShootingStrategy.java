package com.ratattack.game.model.shootingStrategy;


public interface ShootingStrategy {

    ShootingStrategy[] strategies = {new NormalBulletStrategy(), new FastBulletStrategy(), new FreezeBulletStrategy(), new BigBulletStrategy(), new TripleBulletStrategy()};
    String[] strategyTextures = {"normalbulletgrandma.png", "fastbulletgrandma.png", "freezebulletgrandma.png", "bigbulletgrandma.png", "triplebulletgrandma.png"};
    int[] prices = {100, 200, 300, 400, 500};

    void shoot(int x, int y);
}
