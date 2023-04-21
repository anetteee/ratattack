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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ratattack.game.GameSettings;
import com.ratattack.game.backend.DataHolderClass;
import com.ratattack.game.backend.FirebaseInterface;
import com.ratattack.game.model.HighscoreList;
import com.ratattack.game.backend.Score;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.Player;

import java.util.Map;

public class HighscoreScreen implements Screen {

    GameController gameController = GameController.getInstance();
    SpriteBatch batch = GameController.getInstance().getBatch();

    private final BitmapFont fontText;
    private final BitmapFont PinkFont;
    private final BitmapFont bigFont;
    Texture background = new Texture("mainbackground2.png");
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    DataHolderClass _dataHolderClass;
    HighscoreList highscoreList;
    //KOMMENTERTE UT DENNEString userName;

    Texture highscoretexture = new Texture("blackhighscores.png");
    Texture gameOvertexture = new Texture("blackgameover.png");

    FirebaseInterface _FBIC;

    Texture gotoMenuTexture = new Texture("backtomenu.png");

    private final Stage stage = gameController.getStage();
    boolean titleGameOver;

    public HighscoreScreen(FirebaseInterface FBIC, DataHolderClass dataHolderClass) {
        fontText = new BitmapFont();
        bigFont = new BitmapFont();
        fontText.setColor(Color.DARK_GRAY);
        bigFont.setColor(Color.DARK_GRAY);
        bigFont.getData().setScale(7f, 7f);
        PinkFont = new BitmapFont();
        PinkFont.setColor(Color.PINK);
        fontText.getData().setScale(4f, 4f);
        PinkFont.getData().setScale(4f, 4f);


        _FBIC = FBIC;
        highscoreList = new HighscoreList(_FBIC);
        titleGameOver = false;

        //Hvis det nettopp har blitt spilt et spill er gameOver variabelen true, og scoren skal pushes til databasen
        if (gameController.getIsGameOver()) {
            highscoreList.submitHighscore(gameController.getPlayer().getName(), gameController.getPlayer().getScore());
            gameController.setIsGameOver(false);
            titleGameOver =true;
        }
        highscoreList.fetchHighscores();
        //Ellers skal bare scorelisten vises

        _dataHolderClass = dataHolderClass;
    }

    int marginFromTopOfScreen;

    @Override
    public void show() {
        //dataHolder = gameController.getDataHolderClass();
        //new ScoreManager();
        //KOMMENTERTE UT DENNE _dataHolderClass.PrintKeyValue();

        Button goToMenuScreenB = makeButton(gotoMenuTexture,5f,"MENU");
        final Image title;
        System.out.println(gameController.getIsGameOver());
        if (titleGameOver) {
            title = new Image(gameOvertexture);
        }
        else {
            title = new Image(highscoretexture);
        }
        marginFromTopOfScreen = Gdx.graphics.getHeight()-500;
        title.setSize(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
        title.setPosition(Gdx.graphics.getWidth()/2-(highscoretexture.getWidth()/2), Gdx.graphics.getHeight()-500);

        stage.addActor(title);
        stage.addActor(goToMenuScreenB);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, width, height);
        //int yPosition = Gdx.graphics.getHeight() - 50;
        int xPos = Gdx.graphics.getWidth() / 2 - 200;
        //int xPos = Gdx.graphics.getWidth() / 2 ;
        boolean isTopTen = false;
        int j = 0;
        int i = 1;
        for (Map.Entry<String, Score> entry : highscoreList.getScoreList().entrySet()) {
            if (i < 11){
                int yPos = Gdx.graphics.getHeight() - 200 - (i * 100);
                String text = String.valueOf(i) + ". " + entry.getValue().toString();
                if (entry.getKey().equals(gameController.getDataHolderClass().getKeyValue())) {
                    PinkFont.draw(batch, text, xPos, yPos);
                    isTopTen = true;
                } else {
                    fontText.draw(batch, text, xPos, yPos);
                }
                i++;
                j=i-1;
            }
            int currYPos = Gdx.graphics.getHeight() - 100 - (12 * 100);
            int strek = Gdx.graphics.getHeight() - 100 - (11 * 100);
            if (!isTopTen && entry.getKey().equals(gameController.getDataHolderClass().getKeyValue())) {
                String currText = String.valueOf(j) + ". " + entry.getValue().toString();
                fontText.draw(batch, "__________________", xPos, strek);
                PinkFont.draw(batch, currText, xPos, currYPos);
            }
            j++;
        }
        //gameController.update();
        batch.end();
        //stage.draw();
    }

    private Button makeButton(Texture texture, float xPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(Gdx.graphics.getWidth()/8f  ,   Gdx.graphics.getHeight()/4f);
        //b.setPosition(Gdx.graphics.getWidth() / xPos - b.getWidth()/2f,Gdx.graphics.getHeight() / 10f*3f - b.getHeight() / 2f);
        b.setPosition(Gdx.graphics.getWidth() / xPos - b.getWidth()/2f,Gdx.graphics.getHeight()-370);
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
