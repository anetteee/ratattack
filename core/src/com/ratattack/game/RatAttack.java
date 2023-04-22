package com.ratattack.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.ratattack.game.backend.DataHolderClass;
import com.ratattack.game.backend.FirebaseInterface;
import com.ratattack.game.gamecontroller.*;

public class RatAttack extends Game {

	/***
	 * TODO: LEGG TIL KOMMENTARER
	 * */

	//FIREBASE
	FirebaseInterface _FBIC;
	DataHolderClass dataHolder;

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
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		gameController.update();
	}
	
	@Override
	public void dispose () {
		gameController.getBatch().dispose();
		gameController.getShapeRenderer().dispose();
		gameController.getStage().dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}
}
