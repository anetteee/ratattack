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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.model.Player;
import com.ratattack.game.gamecontroller.UsernameTextInputListener;

public class GameScreen implements Screen {

    /***
     * TODO: LEGG TIL KOMMENTARER
     * */


    private final GameController gameController = GameController.getInstance();
    Texture goToMenuTexture = new Texture("gotomenubutton.png");
    Texture goToTutorialTexture = new Texture("watchtutorialbutton.png");
    Texture backgroundBox = new Texture("informationBox.png");
    Texture coinTexture = new Texture("coins.png");
    private final Stage stage = gameController.getStage();
    SpriteBatch batch = GameController.getInstance().getBatch();
    private BitmapFont font;



    public GameScreen() {
        gameController.setUpGame();

        UsernameTextInputListener listener = new UsernameTextInputListener();
        Gdx.input.getTextInput(listener, "What is your name?", "", "Username");

    }

    private Button makeButton(Texture texture, float xPos, final String nextScreen){
        Button b = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        b.setSize(Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
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


    private Button makeLabel(Texture texture, float xPos, float yPos){
        Button l = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        l.setSize(Gdx.graphics.getWidth() * 1.2f ,   Gdx.graphics.getHeight()/7f);
        l.setPosition(Gdx.graphics.getWidth() / xPos - l.getWidth()/1.2f,Gdx.graphics.getHeight() / yPos - l.getHeight() / 2f);
        return l;
    }

    private Button makeCoin(Texture texture, float xPos, float yPos){
        Button c = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        c.setSize(Gdx.graphics.getWidth()/20f  ,   Gdx.graphics.getHeight()/15f);
        //c.setSize(2,2);
        c.setPosition(Gdx.graphics.getWidth() / xPos - c.getWidth()/1f,Gdx.graphics.getHeight() / yPos - c.getHeight() / 2.2f);
        return c;
    }

    @Override
    public void show() {
        Button goToMenuScreenB = makeButton(goToMenuTexture, 2f, "MENU");
        Button goToTutorialScreenB = makeButton(goToTutorialTexture, 5f, "TUTORIAL");

        stage.addActor(goToMenuScreenB);
        stage.addActor(goToTutorialScreenB);

        //font = new BitmapFont();
        //font.getData().setScale(5);
    }

    @Override
    public void render(float delta) {
        gameController.field.draw();
        Button backgroundBoxLabel = makeLabel(backgroundBox, 1.1f, 1.045f);
        Button coin = makeCoin(coinTexture, 1.13f, 1.0455f);
        float xPosition = Gdx.graphics.getWidth()/1.1f;
        float yPosition = Gdx.graphics.getHeight()/1.08f;
        font = new BitmapFont();
        font.getData().setScale(5);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        Label balanceLabel = new Label(String.valueOf(Player.getBalance()), labelStyle);
        balanceLabel.setPosition(xPosition,yPosition);
        stage.addActor(backgroundBoxLabel);
        stage.addActor(coin);
        stage.addActor(balanceLabel);
        stage.draw();

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
        stage.dispose();
    }

}
