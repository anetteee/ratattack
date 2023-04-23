package com.ratattack.game.model.shootingStrategy;


public interface ShootingStrategy {

    ShootingStrategy[] shootingStrategies = {new NormalBulletStrategy(), new FastBulletStrategy(), new FreezeBulletStrategy(), new BigBulletStrategy(), new TripleBulletStrategy()};
    String[] grandmaTextures = {"grandma_normal_bullet.png", "grandma_fast_bullet.png", "grandma_freeze_bullet.png", "grandma_big_bullet.png", "grandma_triple_bullet.png"};
    int[] prices = {100, 150, 200, 250, 300};

    void shoot(int x, int y);
}
