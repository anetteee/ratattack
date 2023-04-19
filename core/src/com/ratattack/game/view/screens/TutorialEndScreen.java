package com.ratattack.game.view.screens;

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
import com.ratattack.game.gamecontroller.GameController;

public class TutorialEndScreen implements Screen {

    GameController gameController = GameController.getInstance();
    SpriteBatch batch = GameController.getInstance().getBatch();
    Texture background = new Texture("greenbackground.png");
    Texture playGameTexture = new Texture("playgamebutton.png");
    Texture watchTutorialTexture = new Texture("watchtutorialbutton.png");
    Texture gotoMenuTexture = new Texture("gotomenubutton.png");
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    private final Stage stage = gameController.getStage();

    public TutorialEndScreen() {
        System.out.println(stage);
    }

    private Button makeButton(Texture texture, float xPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(Gdx.graphics.getWidth()/4f  ,   Gdx.graphics.getHeight()/2f);
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
    public void show() {
        Button playGameButton = makeButton(playGameTexture,2f,"NAME");
        Button watchTutorialButton = makeButton(watchTutorialTexture,1.25f,"GAMERULES");
        Button goToMenuScreenB = makeButton(gotoMenuTexture,5f,"MENU");
        stage.addActor(goToMenuScreenB);
        stage.addActor(playGameButton);
        stage.addActor(watchTutorialButton);
    }

    @Override
    public void render(float delta) {

        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().scale(5);

        batch.begin();
        batch.draw(background, 0, 0, width, height);
        font.draw(gameController.getBatch(), "GAME OVER! Do you want to run the tutorial again?", 150, Gdx.graphics.getHeight() - 200);
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
        batch.dispose();
        stage.dispose();
        DisposeHelper.HelpTexture(background);
        DisposeHelper.HelpTexture(playGameTexture);
        DisposeHelper.HelpTexture(watchTutorialTexture);
        DisposeHelper.HelpTexture(gotoMenuTexture);
    }
}
