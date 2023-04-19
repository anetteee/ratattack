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
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.Player;

public class TutorialEndScreen implements Screen {

    GameController gameController = GameController.getInstance();
    SpriteBatch batch = GameController.getInstance().getBatch();
    Texture background = new Texture("greenbackground.png");
    Texture playGameTexture = new Texture("playgamebutton.png");
    Texture watchTutorialTexture = new Texture("watchtutorialbutton.png");
    Texture goToMenuTexture = new Texture("gotomenubutton.png");

    Button goToTutorialB;
    Button goToMenuScreenB;
    Button goToPlayGameB;
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
        goToTutorialB = new Button(new TextureRegionDrawable(new TextureRegion(watchTutorialTexture)));
        goToTutorialB.setSize(Gdx.graphics.getWidth()/4f  ,   Gdx.graphics.getHeight()/2f);
        goToTutorialB.setPosition(Gdx.graphics.getWidth() / 1.25f - goToTutorialB.getWidth()/2f,Gdx.graphics.getHeight() / 10f*3f - goToTutorialB.getHeight() / 2f);
        goToTutorialB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                GameSettings.ratSpawnrate = GameSettings.spawnRates[0];
                Player.setBalance(0);
                Player.setScore(0);
                gameController.screenContext.changeScreen("GAMERULES");
            }
        });

        goToMenuScreenB = new Button(new TextureRegionDrawable(new TextureRegion(goToMenuTexture)));
        goToMenuScreenB.setSize(Gdx.graphics.getWidth()/4f  ,   Gdx.graphics.getHeight()/2f);
        goToMenuScreenB.setPosition(Gdx.graphics.getWidth() / 5f - goToMenuScreenB.getWidth()/2f,Gdx.graphics.getHeight() / 10f*3f - goToMenuScreenB.getHeight() / 2f);
        goToMenuScreenB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                GameSettings.ratSpawnrate = GameSettings.spawnRates[0];
                Player.setBalance(0);
                Player.setScore(0);
                gameController.screenContext.changeScreen("MENU");
            }
        });

        goToPlayGameB = new Button(new TextureRegionDrawable(new TextureRegion(playGameTexture )));
        goToPlayGameB.setSize(Gdx.graphics.getWidth()/4f  ,   Gdx.graphics.getHeight()/2f);
        goToPlayGameB.setPosition(Gdx.graphics.getWidth() / 2f - goToPlayGameB.getWidth()/2f,Gdx.graphics.getHeight() / 10f*3f - goToPlayGameB.getHeight() / 2f);
        goToPlayGameB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                GameSettings.ratSpawnrate = GameSettings.spawnRates[0];
                Player.setBalance(0);
                Player.setScore(0);
                gameController.screenContext.changeScreen("NAME");
            }
        });
        
        //Button playGameButton = makeButton(playGameTexture,2f,"NAME");
        //Button watchTutorialButton = makeButton(watchTutorialTexture,1.25f,"GAMERULES");
        //Button goToMenuScreenB = makeButton(gotoMenuTexture,5f,"MENU");
        stage.addActor(goToPlayGameB);
        stage.addActor(goToMenuScreenB);
        stage.addActor(goToTutorialB);
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
