package com.ratattack.game.model.system;

import static com.ratattack.game.model.ComponentMappers.positionMapper;
import static com.ratattack.game.model.ComponentMappers.spriteMapper;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.components.BalanceComponent;
import com.ratattack.game.model.components.BoundsComponent;
import com.ratattack.game.model.components.CircleBoundsComponent;
import com.ratattack.game.model.components.HealthComponent;
import com.ratattack.game.model.components.PositionComponent;
import com.ratattack.game.model.components.RectangleBoundsComponent;
import com.ratattack.game.model.components.SpriteComponent;
import com.ratattack.game.model.components.VelocityComponent;

public class RenderSystem extends IteratingSystem {

    private static final Family renderFamily = Family.all(SpriteComponent.class, PositionComponent.class).get();
    private final SpriteBatch batch;
    private final ShapeRenderer renderer;
    int windowWidth = Gdx.graphics.getWidth();
    int windowHeight = Gdx.graphics.getHeight();
    private final GameController gameController = GameController.getInstance();


    public RenderSystem(SpriteBatch batch, ShapeRenderer renderer) {
        super(renderFamily);
        this.batch = batch;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        //System.out.println(getEngine().getEntitiesFor(renderFamily));
    }

    public static int getIndexOfRatSpeedArray(int[] levelChangeTimes, int timeElapsed) {
        int left = 0;
        int right = levelChangeTimes.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (levelChangeTimes[mid] == timeElapsed) {
                return mid;
            } else if (levelChangeTimes[mid] < timeElapsed) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpriteComponent spriteComponent = spriteMapper.get(entity);
        PositionComponent positionComponent = positionMapper.get(entity);
        BoundsComponent bounds = entity.getComponent(CircleBoundsComponent.class);
        if (bounds == null) {
            bounds = entity.getComponent(RectangleBoundsComponent.class);
        }

        Texture texture = spriteComponent.sprite.getTexture();

        batch.begin();

        if ((entity.getComponent(HealthComponent.class) != null)
        && (entity.getComponent(BalanceComponent.class) == null)) {

            VelocityComponent velocity = entity.getComponent(VelocityComponent.class);

            long now = System.currentTimeMillis();
            long timeElapsed = now - gameController.getGameStartTime();

            int index = getIndexOfRatSpeedArray(GameSettings.changeLevelTime, (int) timeElapsed);
            velocity.y = GameSettings.ratSpeed[index];

            System.out.println(velocity.y);

            /*
            if(timeElapsed > 8000 && timeElapsed<10000) {
                BitmapFont font = new BitmapFont();
                font.setColor(Color.RED);
                font.getData().setScale(20);
                font.draw(gameController.getBatch(), "LEVEL UP 1", 400, 1000);
            }

            if (timeElapsed > 10000) {
                velocity.y = GameSettings.easySpeed;

                if(timeElapsed>18000 && timeElapsed<20000) {
                    BitmapFont font = new BitmapFont();
                    font.setColor(Color.RED);
                    font.getData().setScale(20);
                    font.draw(gameController.getBatch(), "LEVEL UP 2", 400, 1000);
                }

                if(timeElapsed > 20000) {
                    velocity.y = GameSettings.mediumSpeed;

                    if(timeElapsed>28000 && timeElapsed<30000) {
                        BitmapFont font = new BitmapFont();
                        font.setColor(Color.RED);
                        font.getData().setScale(20);
                        font.draw(gameController.getBatch(), "LEVEL UP 3", 400, 1000);
                    }
                    if(timeElapsed > 30000) {
                        velocity.y = GameSettings.highSpeed;
                    }
                }
            }
            */
        }

        if (entity.getComponent(HealthComponent.class) != null) {
            BitmapFont font = new BitmapFont();
            font.setColor(Color.RED);
            font.getData().setScale(5);
            String s = Integer.toString(entity.getComponent(HealthComponent.class).getHealth());
            font.draw(gameController.getBatch(),s, positionComponent.x+215, positionComponent.y+200);
        }


        batch.draw(texture, positionComponent.x, positionComponent.y);
        batch.end();

        if (bounds == null) return;//If the entity does not have bounds, don´t render the bound or remove entity

        //Check if the entity has moved out of the screen
        //300 må endres til texture.getheigth() - men då kreves det at begge
        //sprites er av samme størrelse - altså begge bildene!
        Rectangle windowBounds = new Rectangle(0, -300, windowWidth, (windowHeight + (spriteComponent.sprite.getTexture().getHeight())*2));

        Circle circle = null;
        Rectangle rectangle = null;
        boolean isBoundsOutsideWindow = false;
        if (bounds.getBounds() instanceof Circle) {
            // The shape is a Circle
            circle = (Circle) bounds.getBounds();
            isBoundsOutsideWindow = !windowBounds.contains(circle);

        } else if (bounds.getBounds() instanceof Rectangle) {
            // The shape is a Rectangle
            rectangle = (Rectangle) bounds.getBounds();
            isBoundsOutsideWindow = !windowBounds.contains(rectangle);
        }

        if (isBoundsOutsideWindow) {
            // Gets the BalanceComponent for grandchildren and updates the balance
            // of the player when grandchild has crossed the whole field.
            if(entity.getComponent(BalanceComponent.class) != null) {
               int balance = entity.getComponent(BalanceComponent.class).getBalance();
               int oldBalance = gameController.player.getBalance();
               int newBalance = oldBalance + balance;
               gameController.player.setBalance(newBalance);
            }
            getEngine().removeEntity(entity);
        }

        //If debug is true, the bounds should be rendered
        if (GameSettings.debug) {
            renderer.begin(ShapeRenderer.ShapeType.Filled); // specify the shape type (Filled, Line, or Point)
            renderer.setColor(Color.RED); // set the color of the shape
            Shape2D shape = bounds.getBounds();
            if (shape instanceof Circle && circle != null) {
                renderer.circle(circle.x, circle.y, circle.radius); // draw the circle
            } else if (shape instanceof Rectangle && rectangle != null){
                renderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height); // draw the rectangle
            }
            renderer.end(); // end the ShapeRenderer
        }
    }
}
