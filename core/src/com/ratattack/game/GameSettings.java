package com.ratattack.game;

public class GameSettings {

    //Nyttige konstanter
    public static final long startRatSpawnrate = 4000;
    public static final long startGrandChildSpawnrate = 12000;

    public static final int startSpeedRat = -3;
    public static final int startSpeedGrandchild = -1;
    public static final int easySpeed = -7;
    public static final int mediumSpeed = -10;
    public static final int highSpeed = -25;
    public static final int laneNr = 4;
    public static boolean debug = false; //OBS: ShapeRenderer fører til OutOfMemoryError når programmet har kjørt lenge

    //Setup for rendering on screen
    public static int grandmotherLine = 20;


    //Health and strength constants
    public static final int ratStartHealth = 20;
    public static final int grandChildStartHealth = 50;

    public static final int normalBulletStrength = 10;
    public static final int tripleBulletStrength = 10;
    public static final int bigBulletStrength = 20;
    public static final int fastBulletStrength = 30;
    public static final int freezeBulletStrength = 40;

    //Velocity constants
    public static final int freezeVelocity = -2;





}
