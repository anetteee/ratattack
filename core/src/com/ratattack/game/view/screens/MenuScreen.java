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
import com.ratattack.game.backend.DataHolderClass;
import com.ratattack.game.backend.FirebaseInterface;
import com.ratattack.game.gamecontroller.GameController;

public class MenuScreen implements Screen {
    /***
     * TODO: LEGG TIL KOMMENTARER
     * */
    FirebaseInterface _FBIC;
    DataHolderClass _dataHolderClass;
    private final GameController gameController = GameController.getInstance();
    SpriteBatch batch = GameController.getInstance().getBatch();
    Texture background = new Texture("background_dark.png");
    Texture playBtnTexture = new Texture("btn_play.png");
    Texture tutorialBtnTexture = new Texture("btn_tutorial.png");
    Texture highscoreBtnTexture = new Texture("btn_highscores.png");
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    private final Stage stage = gameController.getStage();

    public MenuScreen(FirebaseInterface FBIC, DataHolderClass dataHolderClass) {
       _FBIC = FBIC;
       _dataHolderClass = dataHolderClass;
    }

    @Override
    public void show() {
        final Image title = new Image(new Texture("title_ratattack.png"));
        title.setSize(title.getWidth()*1.6f,  title.getHeight()*1.6f);
        title.setPosition(screenWidth/2 - title.getWidth()/2f, screenHeight/2 - 600 );

        Button highscoreBtn = makeButton(highscoreBtnTexture,5f, 3.5f, screenWidth/5f, screenHeight/3f,"HIGHSCORE");
        Button playBtn = makeButton(playBtnTexture,2f,3f,"NAME");
        Button tutorialBtn = makeButton(tutorialBtnTexture,1.25f,3.5f, screenWidth/5f, screenHeight/3f, "GAMERULES");

        stage.addActor(title);
        stage.addActor(highscoreBtn);
        stage.addActor(playBtn);
        stage.addActor(tutorialBtn);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.end();
    }

    private Button makeButton(Texture texture, float xPos, float yPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(screenWidth/4f  ,   screenHeight/2f);
        b.setPosition(screenWidth / xPos - b.getWidth()/2f,screenHeight / yPos - b.getHeight() / 2f);
        b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                gameController.screenContext.changeScreen(nextScreen);
            }
        });
        return b;
    }

    private Button makeButton(Texture texture, float xPos, float yPos, float xSize, float ySize, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(xSize ,   ySize);
        b.setPosition(screenWidth / xPos - b.getWidth()/2f,screenHeight / yPos - b.getHeight() / 2f);
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
