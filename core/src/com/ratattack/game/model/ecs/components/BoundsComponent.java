package com.ratattack.game.model.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

public abstract class BoundsComponent implements Component {

    public boolean overlaps(BoundsComponent other) {

        Shape2D thisBounds = getBounds();
        Shape2D otherBounds = other.getBounds();

        if (thisBounds instanceof Rectangle && otherBounds instanceof Rectangle) {
            return Intersector.overlaps((Rectangle) thisBounds, (Rectangle) otherBounds);
        } else if (thisBounds instanceof Circle && otherBounds instanceof Circle) {
            //KOMMENTERTE UT DENNESystem.out.println("De traff");
            return Intersector.overlaps((Circle) thisBounds, (Circle) otherBounds);
        } else if (thisBounds instanceof Circle && otherBounds instanceof Rectangle) {
            return Intersector.overlaps((Circle) thisBounds, (Rectangle) otherBounds);
        } else if (thisBounds instanceof Rectangle && otherBounds instanceof Circle) {
            return Intersector.overlaps((Circle) otherBounds, (Rectangle) thisBounds);
        }
        throw new RuntimeException("Cannot compare " + this.getBounds() + " and " + other.getBounds());
    }

    public abstract Shape2D getBounds();

    public abstract void setCenter(int x, int y);

    public abstract void setSize(int width, int height);
}
