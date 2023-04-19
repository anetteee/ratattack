package com.ratattack.game.view.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.components.SpriteComponent;

public class TutorialScreen implements Screen {/***
 * TODO: LEGG TIL KOMMENTARER
 * */

    private final GameController gameController = GameController.getInstance();

    private BitmapFont font;
    SpriteBatch batch = GameController.getInstance().getBatch();

    Texture gotoGameTexture = new Texture("playgamebutton.png");
    Texture gotoMenuTexture = new Texture("gotomenubutton.png");

    private final Stage stage = gameController.getStage();


    public TutorialScreen() {
        System.out.println(stage);
        gameController.setUpLanes(GameSettings.tutorialLaneNr);
        gameController.play();
    }

    @Override
    public void show() {
        Button goToGameScreenB = makeButton(gotoGameTexture,2f,"NAME");
        Button goToMenuScreenB = makeButton(gotoMenuTexture,5f,"MENU");

        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(3);

        stage.addActor(goToGameScreenB);
        stage.addActor(goToMenuScreenB);
    }

    @Override
    public void render(float delta) {
        gameController.field.draw(GameSettings.tutorialLaneNr);
        stage.draw();

        batch.begin();
        font.draw(gameController.getBatch(), "CLICK GRANDMA TO SHOOT!", 650, 200);
        font.draw(gameController.getBatch(), "CLICK GRANDMA TO SHOOT!", 1800, 200);

        font.draw(gameController.getBatch(), "UPGRADE BULLET HERE", 100, 400);
        batch.end();
    }

    private Button makeButton(Texture texture, float xPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        b.setPosition(Gdx.graphics.getWidth() / xPos - b.getWidth()/2f,Gdx.graphics.getHeight() / 10f*3f - b.getHeight() / 2f);
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                gameController.screenContext.changeScreen(nextScreen);
            }
        });
        return b;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {


    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        PooledEngine engine = GameController.getInstance().getEngine();
        for (Entity entity : engine.getEntities()){
            if(entity.getComponent(SpriteComponent.class).sprite.getTexture() != null || gameController.getIsGameOver()){
                entity.getComponent(SpriteComponent.class).sprite.getTexture().dispose();
                entity.getComponent(SpriteComponent.class).sprite.getTexture().equals(null);
            }
        }
        DisposeHelper.HelpTexture(gotoGameTexture);
        DisposeHelper.HelpTexture(gotoMenuTexture);
        DisposeHelper.HelpFont(font);
        stage.dispose();
    }
}
