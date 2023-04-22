package com.ratattack.game.model.ecs.components;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;

public class CircleBoundsComponent extends BoundsComponent {

    private final Circle bounds = new Circle();

    @Override
    public Shape2D getBounds() {
        return bounds;
    }

    @Override
    public void setCenter(int x, int y) {
        bounds.setPosition(x, y);
    }

    @Override
    public void setSize(int width, int height) {
        bounds.setRadius(width);
    }
}
