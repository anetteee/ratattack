package com.ratattack.game;

public class GameSettings {

    // Constants for spawntime and lane number
    public static final long startRatSpawnrate = 4000;
    public static final long startGrandChildSpawnrate = 12000;
    public static final int gameLaneNr = 4;
    public static final int tutorialLaneNr = 10;
    public static boolean debug = false; //OBS: ShapeRenderer fører til OutOfMemoryError når programmet har kjørt lenge


    // Constants for adding difficulty to game
    public static final int[] ratSpeed = {-6, -7, -10, -15};
    public static final int[] changeLevelTime = {0, 10000, 20000, 30000};
    public static final int[] showLevelUpMessageStartTime = {9000, 19000, 29000};
    public static final int[] showLevelUpMessageEndTime = {10000, 20000, 30000};

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
