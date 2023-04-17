package com.ratattack.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ratattack.game.backend.DataHolderClass;
import com.ratattack.game.backend.FirebaseInterface;
import com.ratattack.game.gamecontroller.*;
import com.ratattack.game.model.Highscore;

public class RatAttack extends Game {
	Texture img;

	//FIREBASE
	FirebaseInterface _FBIC;
	DataHolderClass dataHolder;

	Highscore highscore;

	public RatAttack(FirebaseInterface FBIC) {
		_FBIC = FBIC;
	}



	GameController gameController;
	
	@Override
	public void create () {
		gameController = GameController.getInstance();
		gameController.setUpAshley();
		gameController.setFirebaseInterface(_FBIC);
		dataHolder = new DataHolderClass();
		gameController.setDataHolderClass(dataHolder);
		gameController.setGame(this);
		gameController.setStartScreen();
		highscore = new Highscore(_FBIC);

		//FIREBASE
		/*
		highscore.submitHighscore("Miriam", 998);
		highscore.submitHighscore("Ole", 300);
		highscore.submitHighscore("Bjørn", 500);
		highscore.submitHighscore("lise", 700);

		 */
		//highscore.submitHighscore("Anne", 2);

	}

	@Override
	public void render () {
		//ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		gameController.update();
	}
	
	@Override
	public void dispose () {
		gameController.getBatch().dispose();
		gameController.getShapeRenderer().dispose();
		gameController.getStage().dispose();
		img.dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}
}
