package com.ratattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ratattack.game.GameSettings;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.Player;
import com.ratattack.game.gamecontroller.UsernameTextInputListener;
import com.ratattack.game.model.system.RenderSystem;

public class GameScreen implements Screen {

    /***
     * TODO: LEGG TIL KOMMENTARER
     * */
    private final GameController gameController = GameController.getInstance();
    Texture goToMenuTexture = new Texture("gotomenubutton.png");
    Texture backgroundBox = new Texture("informationBox.png");
    Texture pauseScreenTexture = new Texture("purplebutton.png");
    Image coin = new Image(new TextureRegion(new Texture("coins.png")));
    Image navBar = new Image(new TextureRegion(new Texture("informationBox.png")));
    TextureRegionDrawable pauseTexture = new TextureRegionDrawable(new TextureRegion(new Texture("watchtutorialbutton.png")));
    TextureRegionDrawable playTexture = new TextureRegionDrawable(new TextureRegion(new Texture("pinkbutton.png")));
    Button playPauseButton;
    Button goToMenuScreenB;
    Label balanceLabel;
    private final Stage stage = gameController.getStage();
    SpriteBatch batch = GameController.getInstance().getBatch();
    float xPosition = Gdx.graphics.getWidth()/1.1f;
    float yPosition = Gdx.graphics.getHeight()/1.08f;


    public GameScreen() {
        gameController.setUpLanes(GameSettings.gameLaneNr);
        gameController.play();

        goToMenuScreenB = new Button(new TextureRegionDrawable(new TextureRegion(goToMenuTexture)));
        goToMenuScreenB.setSize(Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        goToMenuScreenB.setPosition(Gdx.graphics.getWidth() / 2f - goToMenuScreenB.getWidth()/2f,Gdx.graphics.getHeight() / 10f*3f - goToMenuScreenB.getHeight() / 2f);
        goToMenuScreenB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                GameSettings.ratSpawnrate = GameSettings.spawnRates[0];
                gameController.screenContext.changeScreen("MENU");
            }
        });

        playPauseButton = new Button(pauseTexture);
        playPauseButton.setSize(Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/5f);
        playPauseButton.setPosition(Gdx.graphics.getWidth() / 2f - playPauseButton.getWidth()/2f,Gdx.graphics.getHeight() / 10f - playPauseButton.getHeight() / 2f);
        playPauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (GameController.getInstance().getPaused()) {
                    GameController.getInstance().setPaused(false);
                    playPauseButton.getStyle().up = playTexture;
                    playPauseButton.getStyle().down = playTexture;
                    stage.addActor(navBar);
                    stage.addActor(coin);
                    stage.addActor(balanceLabel);
                    stage.addActor(goToMenuScreenB);
                    for (int i = 0; i < GameController.getInstance().field.grandmaButtons.size(); i++) {
                        stage.addActor(GameController.getInstance().field.grandmaButtons.get(i).getButton());
                        stage.addActor(GameController.getInstance().field.upgradeButtons.get(i).getButton());
                    }

                }
                else {
                    GameController.getInstance().setPaused(true);
                    playPauseButton.getStyle().up = pauseTexture;
                    playPauseButton.getStyle().down = pauseTexture;
                    stage.clear();
                    stage.addActor(playPauseButton);
                }
            }
        });

        coin.setPosition(Gdx.graphics.getWidth() / 1.2f, Gdx.graphics.getHeight() / 1.0455f - (Gdx.graphics.getHeight()/15f)/ 2.2f);
        coin.setSize(Gdx.graphics.getWidth()/20f, Gdx.graphics.getHeight()/15f);
        navBar.setPosition(Gdx.graphics.getWidth() / 1.1f - Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 1.045f - (Gdx.graphics.getHeight()/7f) / 2f);
        navBar.setSize(Gdx.graphics.getWidth() * 1.2f, Gdx.graphics.getHeight()/7f);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(5);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        balanceLabel = new Label(String.valueOf(Player.getBalance()), labelStyle);
        balanceLabel.setPosition(xPosition,yPosition);
    }

    @Override
    public void show() {
        stage.addActor(navBar);
        stage.addActor(coin);
        stage.addActor(balanceLabel);
        stage.addActor(goToMenuScreenB);
        stage.addActor(playPauseButton);
    }

    @Override
    public void render(float delta) {
        gameController.field.draw(GameSettings.gameLaneNr);

        //If screen is paused, draw pauseTexture
        if (GameController.getInstance().getPaused().equals(true)) {
            batch.begin();
            batch.draw(pauseScreenTexture,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
        }
    }


    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'resize'");
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        gameController.pause();
        //throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        gameController.play(); // Vet ikke om vi trenger dette. Vet ikke helt hva alle disse funksjonene gjøre enda, så sjekk det ut når man finner det ut
        //throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'hide'");
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }

}
