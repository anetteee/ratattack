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
    Texture background = new Texture("darkgreenbackground.png");
    Texture playTexture = new Texture("2play.png");
    Texture tutorialTexture = new Texture("2tutorial.png");
    Texture highscoreTexture = new Texture("2highscores.png");
    Texture gameOverTexture = new Texture("title_tutorial_end_screen.png");
    Texture arrowTexture = new Texture("pil.png");
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    private final Stage stage = gameController.getStage();

    public TutorialEndScreen() {
        gameController.setIsGameOver(false);
    }

    @Override
    public void show() {

        final Image gameOverImage;
        gameOverImage = new Image(gameOverTexture);
        gameOverImage.setSize(gameOverTexture.getWidth(), gameOverTexture.getHeight());
        gameOverImage.setPosition(Gdx.graphics.getWidth()/2 - gameOverTexture.getWidth()/2, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/3 );


        final Image arrow;
        arrow = new Image(arrowTexture);
        arrow.setSize(arrowTexture.getWidth(), arrowTexture.getHeight());
        arrow.setPosition(Gdx.graphics.getWidth()/2 + gameOverTexture.getWidth()/2, Gdx.graphics.getHeight()/2.5f );

        Button highscoreButton = makeButton(highscoreTexture,5f, 3.5f, Gdx.graphics.getWidth()/5f, Gdx.graphics.getHeight()/3f,"HIGHSCORE");
        Button playGameButton = makeButton(playTexture,2f,3f,"NAME");
        Button watchTutorialButton = makeButton(tutorialTexture,1.25f,3.5f, Gdx.graphics.getWidth()/5f, Gdx.graphics.getHeight()/3f, "GAMERULES");

        stage.addActor(gameOverImage);
        stage.addActor(arrow);
        stage.addActor(highscoreButton);
        stage.addActor(playGameButton);
        stage.addActor(watchTutorialButton);

    }

    private Button makeButton(Texture texture, float xPos, float yPos, float xSize, float ySize, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(xSize ,   ySize);
        b.setPosition(Gdx.graphics.getWidth() / xPos - b.getWidth()/2f,Gdx.graphics.getHeight() / yPos - b.getHeight() / 2f);
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
        b.setSize(Gdx.graphics.getWidth()/4f  ,   Gdx.graphics.getHeight()/2f);
        b.setPosition(Gdx.graphics.getWidth() / xPos - b.getWidth()/2f,Gdx.graphics.getHeight() / yPos - b.getHeight() / 2f);
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
        batch.dispose();
        stage.dispose();
    }
}
