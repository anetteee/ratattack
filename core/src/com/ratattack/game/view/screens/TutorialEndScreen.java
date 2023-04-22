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
import com.ratattack.game.model.Player;
import com.ratattack.game.GameSettings;

public class TutorialEndScreen implements Screen {

    GameController gameController = GameController.getInstance();
    SpriteBatch batch = GameController.getInstance().getBatch();
    Texture background = new Texture("background_dark.png");
    Texture playBtnTexture = new Texture("btn_play.png");
    Texture tutorialBtnTexture = new Texture("btn_tutorial.png");
    Texture highscoreBtnTexture = new Texture("btn_highscores.png");
    Texture gameOverTexture = new Texture("title_tutorial_end_screen.png");
    Texture arrowTexture = new Texture("arrow.png");
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    private final Stage stage = gameController.getStage();

    public TutorialEndScreen() {
        gameController.setIsGameOver(false);
    }

    @Override
    public void show() {

        Image gameOver = new Image(gameOverTexture);
        gameOver.setSize(gameOverTexture.getWidth(), gameOverTexture.getHeight());
        gameOver.setPosition(screenWidth/2 - gameOverTexture.getWidth()/2, screenHeight-screenHeight/3 );

        Image arrow = new Image(arrowTexture);
        arrow.setSize(arrowTexture.getWidth(), arrowTexture.getHeight());
        arrow.setPosition(screenWidth/2 + gameOverTexture.getWidth()/2, screenHeight/2.5f );

        Button highscoreBtn = makeButton(highscoreBtnTexture,5f, 3.5f, screenWidth/5f, screenHeight/3f,"HIGHSCORE");
        Button playBtn = makeButton(playBtnTexture,2f,3f,"NAME");
        Button tutorialBtn = makeButton(tutorialBtnTexture,1.25f,3.5f, screenWidth/5f, screenHeight/3f, "GAMERULES");

        stage.addActor(gameOver);
        stage.addActor(arrow);
        stage.addActor(highscoreBtn);
        stage.addActor(playBtn);
        stage.addActor(tutorialBtn);

    }

    private Button makeButton(Texture texture, float xPos, float yPos, float xSize, float ySize, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(xSize ,   ySize);
        b.setPosition(screenWidth / xPos - b.getWidth()/2f,screenHeight / yPos - b.getHeight() / 2f);
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                GameSettings.ratSpawnrate = GameSettings.spawnRates[0];
                Player.setBalance(0);
                Player.setScore(0);
                gameController.screenContext.changeScreen(nextScreen);
            }
        });
        return b;
    }

    private Button makeButton(Texture texture, float xPos, float yPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(screenWidth/4f  ,   screenHeight/2f);
        b.setPosition(screenWidth / xPos - b.getWidth()/2f,screenHeight / yPos - b.getHeight() / 2f);
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                GameSettings.ratSpawnrate = GameSettings.spawnRates[0];
                Player.setBalance(0);
                Player.setScore(0);
                gameController.screenContext.changeScreen(nextScreen);
            }
        });
        return b;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);
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
        DisposeHelper.HelpTexture(playBtnTexture);
        DisposeHelper.HelpTexture(tutorialBtnTexture);
        DisposeHelper.HelpTexture(highscoreBtnTexture);
        DisposeHelper.HelpTexture(gameOverTexture);
        DisposeHelper.HelpTexture(arrowTexture);
    }
}
