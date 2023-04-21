package com.ratattack.game.model.ecs.components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

public class RectangleBoundsComponent extends BoundsComponent{

    private final Rectangle bounds = new Rectangle();

    @Override
    public Shape2D getBounds() {
        return bounds;
    }

    @Override
    public void setCenter(int x, int y) {
        bounds.setCenter(x, y);
    }

    @Override
    public void setSize(int width, int height) {
        bounds.setSize(width, height);
    }
}
