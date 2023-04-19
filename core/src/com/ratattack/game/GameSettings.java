package com.ratattack.game;

public class GameSettings {

    // Constants for spawntime and lane number
    public static long ratSpawnrate = 4000;
    public static final long startGrandChildSpawnrate = 12000;
    public static final int gameLaneNr = 4;
    public static final int tutorialLaneNr = 2;
    public static boolean debug = false; //OBS: ShapeRenderer fører til OutOfMemoryError når programmet har kjørt lenge


    // Constants for adding difficulty to game
    public static final int[] ratSpeed = {-3, -7, -10, -15};
    public static final int[] spawnRates = {4000, 3000, 2500, 2000};
    public static final int[] changeLevelScore = {20, 50, 70, 100};

    //Setup for rendering on screen
    public static int grandmotherLine = 20;


    //Health and strength constants
    public static final int ratStartHealth = 20;
    public static final int grandChildStartHealth = 50;

    public static final int normalBulletStrength = 10;
    public static final int tripleBulletStrength = 10;
    public static final int bigBulletStrength = 20;
    public static final int fastBulletStrength = 10;
    public static final int freezeBulletStrength = 10;

    //Velocity constants
    public static final int freezeVelocity = -2;
    public static final int startSpeedRat = -3;
    public static final int startSpeedGrandchild = -10;





}
