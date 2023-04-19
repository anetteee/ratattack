package com.ratattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ratattack.game.gamecontroller.GameController;

public class GameRulesScreen implements Screen {
    private final GameController gameController = GameController.getInstance();

    Texture background = new Texture("mainbackground2.png");
    Texture watchTutorialTexture = new Texture("watchtutorialbutton.png");
    Texture newGameRules = new Texture("newgamerules.png");
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    private final Stage stage = gameController.getStage();

    public GameRulesScreen() {
        System.out.println(stage);
    }

    @Override
    public void show() {
        final Image title = new Image(newGameRules);
        title.setSize(Gdx.graphics.getWidth()/2f,  Gdx.graphics.getHeight()/2f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/4);

        final Image textBackground = new Image(background);
        textBackground.setSize(width, height);
        textBackground.setPosition(0, 0);

        Button goToTutorialScreenB = makeButton(watchTutorialTexture,5f,"TUTORIAL");

        stage.addActor(textBackground);
        stage.addActor(title);
        stage.addActor(goToTutorialScreenB);

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
    public void render(float delta) {
        stage.draw();
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
        DisposeHelper.HelpTexture(background);
        DisposeHelper.HelpTexture(watchTutorialTexture);
        DisposeHelper.HelpTexture(newGameRules);
    }
}
