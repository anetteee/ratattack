package com.ratattack.game.gamecontroller;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ratattack.game.backend.DataHolderClass;
import com.ratattack.game.backend.FirebaseInterface;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ratattack.game.RatAttack;
import com.ratattack.game.model.Player;
import com.ratattack.game.model.ecs.system.CollisionSystem;
import com.ratattack.game.model.Field;
import com.ratattack.game.model.ecs.GameWorld;
import com.ratattack.game.model.ecs.system.LevelupSystem;
import com.ratattack.game.view.screenState.MenuState;
import com.ratattack.game.view.screenState.ScreenContext;
import com.ratattack.game.model.ecs.system.BoundsSystem;
import com.ratattack.game.model.ecs.system.MovementSystem;
import com.ratattack.game.model.ecs.system.RenderSystem;
import com.ratattack.game.model.ecs.system.SpawnSystem;

public class GameController {

    private static final GameController instance = new GameController();

    private Player player;

    public Field field;
    private Boolean paused = true;
    private Boolean isGameOver = false;

    Stage stage;
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;

    // Ashley
    private RatAttack game;

    private static GameWorld ashleyWorld;
    private static PooledEngine engine;

    public ScreenContext screenContext;

    FirebaseInterface _FBIC;
    DataHolderClass dataHolder;

    private GameController() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        player = new Player("Spiller");
    }

    public RatAttack getGame(){
        return game;
    }

    public static GameController getInstance() {
        return instance;
    }

    public void setStartScreen() {

        screenContext = new ScreenContext();
        screenContext.push(new MenuState(screenContext));
    }

    public void setUpAshley() {
        engine = new PooledEngine();
        ashleyWorld = new GameWorld(engine);

        addSystems(engine);

    }

    public void addSystems(PooledEngine engine) {
        engine.addSystem(new MovementSystem());
        engine.addSystem(new SpawnSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new RenderSystem(batch, shapeRenderer));
        engine.addSystem(new LevelupSystem());
    }

    public void update() {
        if (!paused) {
            engine.update(Gdx.graphics.getDeltaTime());
        }
        stage.draw();
    }

    public void setUpLanes(int laneNr) {
        try {
            field = new Field(laneNr);
        }
        catch (Exception e) {
            System.out.println("Error with field creation");
        }
    }

    public void play() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public PooledEngine getEngine() {
        return engine;
    }

    public GameWorld getAshleyWorld() {
        return ashleyWorld;
    }

    public SpriteBatch getBatch() {
        return batch;
    }
    public Stage getStage() { return stage; }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setGame(RatAttack game) {
        this.game = game;
    }

    public FirebaseInterface getFirebaseInterface() {
        return _FBIC;
    }

    public void  setFirebaseInterface(FirebaseInterface FBIC) {
        this._FBIC = FBIC;
    }
    public void setDataHolderClass(DataHolderClass dataHolder) {
        this.dataHolder = dataHolder;
    }

    public DataHolderClass getDataHolderClass(){
        return dataHolder;
    }

    public Player getPlayer() {
        return player;
    }

    public void setIsGameOver(Boolean gameOver) {
        isGameOver = gameOver;
    }

    public Boolean getIsGameOver() {
        return isGameOver;
    }

    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(Boolean change) {
        paused = change;
    }

}
