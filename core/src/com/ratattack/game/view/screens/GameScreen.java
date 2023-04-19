package com.ratattack.game.view.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
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
import com.ratattack.game.model.components.SpriteComponent;

public class GameScreen implements Screen {

    /***
     * TODO: LEGG TIL KOMMENTARER
     * */
    private final GameController gameController = GameController.getInstance();
    Texture goToMenuTexture = new Texture("goToMenu.png");
    Texture pauseScreenTexture = new Texture("purplebutton.png");
    Image coin = new Image(new TextureRegion(new Texture("coins.png")));
    Image navBar = new Image(new TextureRegion(new Texture("informationBox.png")));
    TextureRegionDrawable pauseTexture = new TextureRegionDrawable(new TextureRegion(new Texture("watchtutorialbutton.png")));
    TextureRegionDrawable playTexture = new TextureRegionDrawable(new TextureRegion(new Texture("pinkbutton.png")));
    Button playPauseButton;
    Button goToMenuScreenB;
    private final Stage stage = gameController.getStage();
    private final Label balance;
    private final Label score;
    SpriteBatch batch = GameController.getInstance().getBatch();


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
                Player.setBalance(0);
                Player.setScore(0);
                gameController.screenContext.changeScreen("MENU");
            }
        });

        playPauseButton = new Button(pauseTexture);
        playPauseButton.setSize(Gdx.graphics.getWidth()/20f  ,   Gdx.graphics.getHeight()/10f);
        playPauseButton.setPosition(Gdx.graphics.getWidth() / 30f - playPauseButton.getWidth()/2f,Gdx.graphics.getHeight() / 1.05f - playPauseButton.getHeight() / 2f);
        playPauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (GameController.getInstance().getPaused()) {
                    GameController.getInstance().setPaused(false);
                    playPauseButton.getStyle().up = pauseTexture;
                    playPauseButton.getStyle().down = pauseTexture;
                    stage.clear();
                    stage.addActor(navBar);
                    stage.addActor(coin);
                    stage.addActor(balance);
                    stage.addActor(score);
                    stage.addActor(goToMenuScreenB);
                    stage.addActor(playPauseButton);
                    for (int i = 0; i < GameController.getInstance().field.grandmaButtons.size(); i++) {
                        stage.addActor(GameController.getInstance().field.grandmaButtons.get(i).getButton());
                        stage.addActor(GameController.getInstance().field.upgradeButtons.get(i).getButton());
                    }

                }
                else {
                    GameController.getInstance().setPaused(true);
                    playPauseButton.getStyle().up = playTexture;
                    playPauseButton.getStyle().down = playTexture;
                    stage.clear();
                    stage.addActor(playPauseButton);
                }
            }
        });

        coin.setPosition(Gdx.graphics.getWidth() / 1.2f, Gdx.graphics.getHeight() / 1.0455f - (Gdx.graphics.getHeight()/15f)/ 2.2f);
        coin.setSize(Gdx.graphics.getWidth()/20f, Gdx.graphics.getHeight()/15f);
        navBar.setPosition(Gdx.graphics.getWidth() / 1.1f - Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 1.045f - (Gdx.graphics.getHeight()/7f) / 2f);
        navBar.setSize(Gdx.graphics.getWidth() * 1.2f, Gdx.graphics.getHeight()/7f);

        balance = makeBalance();
        score = makeScore();
    }

    @Override
    public void show() {
        setBalance();
        setScore();

        stage.addActor(navBar);
        stage.addActor(coin);
        stage.addActor(goToMenuScreenB);
        stage.addActor(playPauseButton);
        stage.addActor(score);
        stage.addActor(balance);
    }

    @Override
    public void render(float delta) {
        gameController.field.draw(GameSettings.gameLaneNr);
        stage.draw();

        setScore();
        setBalance();

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
        PooledEngine engine = GameController.getInstance().getEngine();
        for (Entity entity : engine.getEntities()){
            if(entity.getComponent(SpriteComponent.class).sprite.getTexture() != null || gameController.getIsGameOver()){
                entity.getComponent(SpriteComponent.class).sprite.getTexture().dispose();
                entity.getComponent(SpriteComponent.class).sprite.getTexture().equals(null);
            }
        }
        DisposeHelper.HelpTexture(goToMenuTexture);
        DisposeHelper.HelpTexture(pauseScreenTexture);
        DisposeHelper.HelpTextureRegionDrawable(pauseTexture);
        DisposeHelper.HelpTextureRegionDrawable(playTexture);
        DisposeHelper.HelpImage(coin);
        DisposeHelper.HelpImage(navBar);
    }

    private Label makeScore(){
        float xPositionScore = Gdx.graphics.getWidth()/10f;
        float yPositionScore = Gdx.graphics.getHeight()/1.08f;
        BitmapFont fontScore = new BitmapFont();
        fontScore.getData().setScale(5);
        Label.LabelStyle labelStyleScore = new Label.LabelStyle();
        labelStyleScore.font = fontScore;
        Label scoreLabel = new Label(String.valueOf(Player.getScore()), labelStyleScore);
        scoreLabel.setPosition(xPositionScore,yPositionScore);
        return scoreLabel;
    }

    private void setScore(){
        score.setText(Player.getScore());
    }

    private Label makeBalance(){
        float xPositionBalance = Gdx.graphics.getWidth()/1.1f;
        float yPositionBalance = Gdx.graphics.getHeight()/1.08f;
        BitmapFont fontBalance = new BitmapFont();
        fontBalance.getData().setScale(5);
        Label.LabelStyle labelStyleBalance = new Label.LabelStyle();
        labelStyleBalance.font = fontBalance;
        Label balanceLabel = new Label(String.valueOf(Player.getBalance()), labelStyleBalance);
        balanceLabel.setPosition(xPositionBalance,yPositionBalance);
        return balanceLabel;
    }

    private void setBalance(){
        balance.setText(Player.getBalance());
    }

}
