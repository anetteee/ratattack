package com.ratattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ratattack.game.DataHolderClass;
import com.ratattack.game.FirebaseInterface;
import com.ratattack.game.Highscore;
import com.ratattack.game.ScoreManager;
import com.ratattack.game.gamecontroller.GameController;

public class HighscoreScreen implements Screen {

    GameController gameController = GameController.getInstance();
    SpriteBatch batch2;

    Texture background = new Texture("lane.png");
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    DataHolderClass _dataHolderClass;

    private Stage stage;
    Highscore highscore;

    FirebaseInterface _FBIC;

    public HighscoreScreen(FirebaseInterface FBIC, DataHolderClass dataHolderClass) {
        _FBIC = FBIC;
        _dataHolderClass = dataHolderClass;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage = new Stage(new ScreenViewport());
        batch2 = new SpriteBatch();
        //gameController = GameController.getInstance();
        //dataHolder = gameController.getDataHolderClass();
        highscore = new Highscore(_FBIC);
        new ScoreManager();
        System.out.println("Denne er fra HighScoreScreen");
        _dataHolderClass.PrintSomeValue();
    }

    @Override
    public void render(float delta) {
        batch2.begin();
        batch2.draw(background, 0, 0, width, height);
        //gameController.update();
        highscore.render(batch2);
        batch2.end();
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

    }
}
