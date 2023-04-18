package com.ratattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ratattack.game.gamecontroller.GameController;

public class GameScreen implements Screen {

    /***
     * TODO: LEGG TIL KOMMENTARER
     * */
    private final GameController gameController = GameController.getInstance();
    Texture goToMenuTexture = new Texture("goToMenu.png");
    Texture backgroundBox = new Texture("informationBox.png");
    Texture coinTexture = new Texture("coins.png");
    private final Stage stage = gameController.getStage();
    private BitmapFont fontBalance;
    private BitmapFont fontScore;
    private Label balance;
    private Label score;



    public GameScreen() {
        gameController.setUpGame();
        gameController.play();

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
        c.setPosition(Gdx.graphics.getWidth() / xPos - c.getWidth()/1f,Gdx.graphics.getHeight() / yPos - c.getHeight() / 2.2f);
        return c;
    }

    private Label makeBalance(){
        float xPositionBalance = Gdx.graphics.getWidth()/1.1f;
        float yPositionBalance = Gdx.graphics.getHeight()/1.08f;
        fontBalance = new BitmapFont();
        fontBalance.getData().setScale(5);
        Label.LabelStyle labelStyleBalance = new Label.LabelStyle();
        labelStyleBalance.font = fontBalance;
        Label balanceLabel = new Label(String.valueOf(gameController.getInstance().getPlayer().getBalance()), labelStyleBalance);
        balanceLabel.setPosition(xPositionBalance,yPositionBalance);
        return balanceLabel;
    }

    private void setBalance(){
        balance.setText(gameController.getInstance().getPlayer().getBalance());
    }

    private Label makeScore(){
        float xPositionScore = Gdx.graphics.getWidth()/10f;
        float yPositionScore = Gdx.graphics.getHeight()/1.08f;
        fontScore = new BitmapFont();
        fontScore.getData().setScale(5);
        Label.LabelStyle labelStyleScore = new Label.LabelStyle();
        labelStyleScore.font = fontScore;
        Label scoreLabel = new Label(String.valueOf(gameController.getInstance().getPlayer().getScore()), labelStyleScore);
        scoreLabel.setPosition(xPositionScore,yPositionScore);
        return scoreLabel;
    }

    private void setScore(){
        score.setText(gameController.getInstance().getPlayer().getScore());
    }

    @Override
    public void show() {
        Button goToMenuScreenB = new Button(new TextureRegionDrawable(new TextureRegion(goToMenuTexture)));
        goToMenuScreenB.setSize(Gdx.graphics.getWidth()/7.5f  ,   Gdx.graphics.getHeight()/20f);
        goToMenuScreenB.setPosition(Gdx.graphics.getWidth() / 1.5f - goToMenuScreenB.getWidth()/1f,Gdx.graphics.getHeight() / 1.045f - goToMenuScreenB.getHeight() / 2.2f);
        goToMenuScreenB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float xpos, float ypos) {
                //screencontext bytter screen vha state
                gameController.screenContext.changeScreen("MENU");
            }
        });
        Button backgroundBoxLabel = makeLabel(backgroundBox, 1.1f, 1.045f);
        Button coin = makeCoin(coinTexture, 1.13f, 1.0455f);

        stage.addActor(backgroundBoxLabel);
        stage.addActor(coin);

        stage.addActor(goToMenuScreenB);

        balance = makeBalance();
        score = makeScore();

    }

    @Override
    public void render(float delta) {
        gameController.field.draw();

        stage.draw();

        setScore();
        stage.addActor(score);
        setBalance();
        stage.addActor(balance);
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
