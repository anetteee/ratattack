package com.ratattack.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ratattack.game.gamecontroller.GameController;

import javax.swing.text.html.Option;

public class OptionScreen implements Screen {

    GameController gameController = GameController.getInstance();

    private final Stage stage = GameController.getInstance().getStage();
    private final SpriteBatch batch = GameController.getInstance().getBatch();

    Texture goToMenuTexture = new Texture("gotomenubutton.png");
    Texture background = new Texture("lane.png");

    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();

    public OptionScreen() {
        Button menuButton = makeSmallButton(goToMenuTexture, 1.15f, 1.25f, "MENU");

        stage.addActor(menuButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, width, height);
        batch.end();
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
        stage.dispose();
        batch.dispose();
    }

    private Button makeSmallButton(Texture texture, float xPos, float yPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/5f);
        b.setPosition(Gdx.graphics.getWidth() / xPos - b.getWidth()/2f,Gdx.graphics.getHeight() / yPos - b.getHeight() / 2f);
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                gameController.screenContext.changeScreen(nextScreen);
            }
        });
        return b;
    }
}
