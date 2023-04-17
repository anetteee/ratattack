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
    Texture balanceLabelTexture = new Texture("informationBox.png");
    Texture coinTexture = new Texture("coins.png");
    private final Stage stage = gameController.getStage();
    SpriteBatch batch = GameController.getInstance().getBatch();
    private BitmapFont font;



    public GameScreen() {
        gameController.setUpGame();
        gameController.play();

        //UsernameTextInputListener listener = new UsernameTextInputListener();
        //Gdx.input.getTextInput(listener, "What is your name?", "", "Username");

    }

    private Button makeLabel(Texture texture, float xPos, float yPos){
        Button l = new Button(new TextureRegionDrawable(new TextureRegion(texture)));
        l.setSize(Gdx.graphics.getWidth()/7f  ,   Gdx.graphics.getHeight()/7f);
        l.setPosition(Gdx.graphics.getWidth() / xPos - l.getWidth()/2f,Gdx.graphics.getHeight() / yPos - l.getHeight() / 2f);
        return l;
    }

    @Override
    public void show() {
        Button goToMenuScreenB = new Button(new TextureRegionDrawable(new TextureRegion(goToMenuTexture)));
        goToMenuScreenB.setSize(Gdx.graphics.getWidth()/10f  ,   Gdx.graphics.getHeight()/7f);
        goToMenuScreenB.setPosition(Gdx.graphics.getWidth() / 2f - goToMenuScreenB.getWidth()/2f,Gdx.graphics.getHeight() / 10f*3f - goToMenuScreenB.getHeight() / 2f);
        goToMenuScreenB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                gameController.screenContext.changeScreen("MENU");

            }
        });

        stage.addActor(goToMenuScreenB);

        font = new BitmapFont();
        font.getData().setScale(5);
    }

    @Override
    public void render(float delta) {
        gameController.field.draw();
        Button balanceLabel = makeLabel(balanceLabelTexture, 1.1f, 1.1f);
        batch.begin();
        balanceLabel.draw(batch, 1f);
        Button coin = new Button(new TextureRegionDrawable(new TextureRegion(coinTexture)));
        coin.setSize(Gdx.graphics.getWidth()/20f  ,   Gdx.graphics.getHeight()/15f);
        //c.setSize(2,2);
        coin.setPosition(Gdx.graphics.getWidth() / 1.13f - coin.getWidth()/2f,Gdx.graphics.getHeight() / 1.1f - coin.getHeight() / 2f);

        coin.draw(batch,2f);
        font.draw(batch, String.valueOf(Player.getBalance()),2700, 1340);
        batch.end();
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
