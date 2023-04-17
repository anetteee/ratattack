package com.ratattack.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ratattack.game.gamecontroller.GameController;
import com.ratattack.game.gamecontroller.UsernameTextInputListener;

public class NameScreen implements Screen {

    SpriteBatch batch = GameController.getInstance().getBatch();

    Texture background = new Texture("greenbackground.png");

    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();

    public NameScreen() {

        UsernameTextInputListener listener = new UsernameTextInputListener();
        Gdx.input.getTextInput(listener, "What is your name?", "", "Username");

    }

    @Override
    public void show() {

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

    }
}
