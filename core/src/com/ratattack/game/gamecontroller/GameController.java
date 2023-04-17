package com.ratattack.game.gamecontroller;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ratattack.game.DataHolderClass;
import com.ratattack.game.FirebaseInterface;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ratattack.game.GameSettings;
import com.ratattack.game.RatAttack;
import com.ratattack.game.model.Player;
import com.ratattack.game.model.system.CollisionSystem;
import com.ratattack.game.model.Field;
import com.ratattack.game.model.GameWorld;
import com.ratattack.game.view.screenState.MenuState;
import com.ratattack.game.view.screenState.ScreenContext;
import com.ratattack.game.model.system.BoundsSystem;
import com.ratattack.game.model.system.MovementSystem;
import com.ratattack.game.model.system.RenderSystem;
import com.ratattack.game.model.system.SpawnSystem;
import com.ratattack.game.model.system.UserSystem;
import com.ratattack.game.view.screens.OptionScreen;
import com.ratattack.game.view.screens.ScreenFactory;
import com.ratattack.game.view.screens.TutorialScreen;

public class GameController {

    private static final GameController instance = new GameController();

    private Player player;

    public Field field;
    private Boolean paused = true;

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

    private long gameStartTime;

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

    private void setGameScreen() {
        try {
            game.setScreen(ScreenFactory.getScreen("GAME"));
        } catch (Exception e) {
            System.out.println("No game instance set for the game controller"); //Denne slår ut, og jeg skjønner ikke helt hvorfor. Men alt funker allikevel
        }
    }

    private void setMenuScreen() {
        try {
            game.setScreen(ScreenFactory.getScreen("MENU"));
        } catch (Exception e) {
            System.out.println("No game instance set for the game controller");
        }
    }

    private void setOptionsScreen() {
        OptionScreen optionScreen = new OptionScreen();
        game.setScreen(optionScreen);
    }

    private void setTutorialScreen() {
        TutorialScreen tutorialScreen = new TutorialScreen();
        game.setScreen(tutorialScreen);
    }

    public void setUpAshley() {
        engine = new PooledEngine();
        ashleyWorld = new GameWorld(engine);

        //Add systems to engine
        addSystems(engine);

        //Add entities
        addEntities();

    }

    public void addSystems(PooledEngine engine) {
        engine.addSystem(new UserSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new SpawnSystem(engine, GameSettings.startRatSpawnrate, GameSettings.startGrandChildSpawnrate));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new RenderSystem(batch, shapeRenderer));
    }

    public void addEntities() {
        //Create Rat
        //ashleyWorld.createRat();

    }

    public void update() {
        if (!paused) {
            engine.update(Gdx.graphics.getDeltaTime());
        }
        stage.draw();
    }

    public void setUpGame() {
        try {
            field = new Field();
        }
        catch (Exception e) {
            System.out.println("Error with field creation");
        }
        gameStartTime = System.currentTimeMillis();
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
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }
}
