package com.ratattack.game.model.ecs.components;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {

    private int health;

    public void setHealth(int x) {
        this.health = x;
    }

    public int getHealth() {
        return health;
    }
}
