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

public class TutorialScreen implements Screen {

 /***
 * TODO: LEGG TIL KOMMENTARER
 * */
private final GameController gameController = GameController.getInstance();
    Texture quitTexture = new Texture("btn_quit.png");
    Texture pausedScreenTexture = new Texture("screen_paused.png");
    Image coins = new Image(new TextureRegion(new Texture("coins.png")));
    Image clickGrandmaText = new Image(new TextureRegion(new Texture("text_click_grandma.png")));
    Image seeBalanceText = new Image(new TextureRegion(new Texture("text_see_balance.png")));
    Image seeScoreText = new Image(new TextureRegion(new Texture("text_see_score.png")));
    Image navBar = new Image(new TextureRegion(new Texture("navbar.png")));
    TextureRegionDrawable pauseTexture = new TextureRegionDrawable(new TextureRegion(new Texture("btn_small_pause.png")));
    TextureRegionDrawable playTexture = new TextureRegionDrawable(new TextureRegion(new Texture("btn_small_play.png")));
    Button playPauseBtn;
    Button quitBtn;
    private final Stage stage = gameController.getStage();
    private final Label balance;
    private final Label score;
    SpriteBatch batch = GameController.getInstance().getBatch();

    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();


    public TutorialScreen() {
        gameController.setUpLanes(GameSettings.tutorialLaneNr);
        gameController.play();

        quitBtn = new Button(new TextureRegionDrawable(new TextureRegion(quitTexture)));
        quitBtn.setSize(quitTexture.getWidth(), quitTexture.getHeight()-30);
        quitBtn.setPosition( screenWidth- quitTexture.getWidth()-25,screenHeight - 140);
        quitBtn.addListener(
                new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                GameSettings.ratSpawnrate = GameSettings.spawnRates[0];
                Player.setBalance(0);
                Player.setScore(0);
                gameController.screenContext.changeScreen("MENU");
            }
        });

        playPauseBtn = new Button(pauseTexture);


        playPauseBtn.setSize(screenWidth/20f  ,   screenHeight/10f);
        playPauseBtn.setPosition(screenWidth / 30f - playPauseBtn.getWidth()/2f,screenHeight - playPauseBtn.getHeight()-10);
        playPauseBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                if (GameController.getInstance().getPaused()) {
                    GameController.getInstance().setPaused(false);
                    playPauseBtn.getStyle().up = pauseTexture;
                    playPauseBtn.getStyle().down = pauseTexture;
                    stage.clear();
                    stage.addActor(navBar);
                    stage.addActor(coins);
                    stage.addActor(balance);
                    stage.addActor(score);
                    stage.addActor(quitBtn);
                    stage.addActor(playPauseBtn);
                    stage.addActor(seeScoreText);
                    stage.addActor(seeBalanceText);
                    stage.addActor(clickGrandmaText);
                    for (int i = 0; i < GameController.getInstance().field.grandmaButtons.size(); i++) {
                        stage.addActor(GameController.getInstance().field.grandmaButtons.get(i).getButton());
                        stage.addActor(GameController.getInstance().field.upgradeButtons.get(i).getButton());
                    }

                }
                else {
                    GameController.getInstance().setPaused(true);
                    playPauseBtn.getStyle().up = playTexture;
                    playPauseBtn.getStyle().down = playTexture;
                    stage.clear();
                    stage.addActor(playPauseBtn);
                }
            }
        });

        coins.setPosition(screenWidth-400, screenHeight - 130);
        coins.setSize(screenWidth/20f, screenHeight/15f);
        navBar.setPosition(screenWidth / 1.1f - screenWidth, screenHeight / 1.045f - (screenHeight/7f) / 2f);
        navBar.setSize(screenWidth * 1.2f, screenHeight/7f);
        seeScoreText.setPosition(230, screenHeight - 510);
        seeBalanceText.setPosition(screenWidth- seeBalanceText.getWidth()-250, screenHeight - 500);
        clickGrandmaText.setPosition(350, 200);

        balance = makeBalance();
        score = makeScore();
    }

    @Override
    public void show() {
        setBalance();
        setScore();

        stage.addActor(navBar);
        stage.addActor(coins);
        stage.addActor(quitBtn);
        stage.addActor(playPauseBtn);
        stage.addActor(score);
        stage.addActor(balance);
        stage.addActor(seeScoreText);
        stage.addActor(seeBalanceText);
        stage.addActor(clickGrandmaText);

    }

    @Override
    public void render(float delta) {
        gameController.field.draw(GameSettings.tutorialLaneNr);
        stage.draw();

        setScore();
        setBalance();

        //If screen is paused, draw pauseTexture
        if (GameController.getInstance().getPaused().equals(true)) {
            batch.begin();
            batch.draw(pausedScreenTexture,0,0, screenWidth, screenHeight);
            batch.end();
        }
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        gameController.pause();
    }

    @Override
    public void resume() {
        gameController.play();
        //TODO: kanskje fjerne: Vet ikke om vi trenger dette. Vet ikke helt hva alle disse funksjonene gjøre enda, så sjekk det ut når man finner det ut
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }

    private Label makeScore(){
        float xPositionScore = 200;
        float yPositionScore = screenHeight - 130;
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
        float xPositionBalance = screenWidth - 600;
        float yPositionBalance = screenHeight - 130;
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
