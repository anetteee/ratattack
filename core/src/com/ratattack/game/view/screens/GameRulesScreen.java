package com.ratattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
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
    Texture gameRulesTexture = new Texture("screen_gamerules.png");
    Texture playTutorialTexture = new Texture("btn_play_tutorial.png");

    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    private final Stage stage = gameController.getStage();

    public GameRulesScreen() {
    }

    @Override
    public void show() {

        final Image gameRules = new Image(gameRulesTexture);
        gameRules.setSize(screenWidth, screenHeight);
        gameRules.setPosition(0, 0);

        Button playTutorialBtn = makeButton(playTutorialTexture,screenWidth-(playTutorialTexture.getWidth()*1.5f)-50, 150,"TUTORIAL");

        stage.addActor(gameRules);
        stage.addActor(playTutorialBtn);

    }

    private Button makeButton(Texture texture, float xPos, float yPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(texture.getWidth()*1.5f,texture.getHeight()*1.5f);
        b.setPosition(xPos,screenHeight - yPos);
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
        DisposeHelper.HelpTexture(gameRulesTexture);
        DisposeHelper.HelpTexture(playTutorialTexture);
    }
}
