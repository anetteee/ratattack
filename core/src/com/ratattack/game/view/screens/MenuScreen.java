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
    //HighscoreList highscoreList;
    FirebaseInterface _FBIC;
    DataHolderClass _dataHolderClass;

    /***
     * TODO: LEGG TIL KOMMENTARER
     * */

    private final GameController gameController = GameController.getInstance();

    SpriteBatch batch = GameController.getInstance().getBatch();

    Texture background = new Texture("greenbackground.png");
    Texture playGameTexture = new Texture("playgamebutton.png");
    Texture watchTutorialTexture = new Texture("watchtutorialbutton.png");
    Texture highscoreTexture = new Texture("highscorebutton.png");
    Texture settingsButtonTexture = new Texture("highscorebutton.png"); //Må fikse riktig texture her

    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();

    private final Stage stage = gameController.getStage();

    public MenuScreen(FirebaseInterface FBIC, DataHolderClass dataHolderClass) {
        _FBIC = FBIC;
        _dataHolderClass = dataHolderClass;

        System.out.println(stage);
    }

    @Override
    public void show() {
        final Image title = new Image(new Texture("ratattacklogo.png"));
        title.setSize(Gdx.graphics.getWidth()/3f,  Gdx.graphics.getHeight()/2f);
        title.setPosition(Gdx.graphics.getWidth()/2f - title.getWidth()/2f, Gdx.graphics.getHeight()/2f);

        Button highscoreButton = makeButton(highscoreTexture,5f, 3f,"HIGHSCORE");
        Button playGameButton = makeButton(playGameTexture,2f, 3f, "NAME");
        Button watchTutorialButton = makeButton(watchTutorialTexture,1.25f, 3f,"GAMERULES");

        stage.addActor(title);
        stage.addActor(highscoreButton);
        stage.addActor(playGameButton);
        stage.addActor(watchTutorialButton);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, width, height);
        batch.end();
    }

    private Button makeButton(Texture texture, float xPos, float yPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(Gdx.graphics.getWidth()/4f  ,   Gdx.graphics.getHeight()/2f);
        b.setPosition(Gdx.graphics.getWidth() / xPos - b.getWidth()/2f,Gdx.graphics.getHeight() / yPos - b.getHeight() / 2f);
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
