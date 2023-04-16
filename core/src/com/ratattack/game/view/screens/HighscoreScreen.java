package com.ratattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ratattack.game.DataHolderClass;
import com.ratattack.game.FirebaseInterface;
import com.ratattack.game.Highscore;
import com.ratattack.game.ScoreManager;
import com.ratattack.game.gamecontroller.GameController;

public class HighscoreScreen implements Screen {

    GameController gameController = GameController.getInstance();
    SpriteBatch batch2 = GameController.getInstance().getBatch();

    Texture background = new Texture("lane.png");
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    DataHolderClass _dataHolderClass;
    Highscore highscore;

    FirebaseInterface _FBIC;

    Texture gotoMenuTexture = new Texture("gotomenubutton.png");

    private final Stage stage = gameController.getStage();

    public HighscoreScreen(FirebaseInterface FBIC, DataHolderClass dataHolderClass) {
        _FBIC = FBIC;
        _dataHolderClass = dataHolderClass;
    }

    @Override
    public void show() {
        batch2 = new SpriteBatch();
        //gameController = GameController.getInstance();
        //dataHolder = gameController.getDataHolderClass();
        highscore = new Highscore(_FBIC);
        //new ScoreManager();
        System.out.println("Denne er fra HighScoreScreen");
        _dataHolderClass.PrintSomeValue();

        Button goToMenuScreenB = makeButton(gotoMenuTexture,5f,"MENU");
        stage.addActor(goToMenuScreenB);
    }

    @Override
    public void render(float delta) {
        batch2.begin();
        batch2.draw(background, 0, 0, width, height);
        //gameController.update();
        highscore.render(batch2);
        batch2.end();
        //stage.draw();
    }

    private Button makeButton(Texture texture, float xPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(Gdx.graphics.getWidth()/8f  ,   Gdx.graphics.getHeight()/4f);
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

    }
}
