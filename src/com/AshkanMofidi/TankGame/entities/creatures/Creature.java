package com.AshkanMofidi.TankGame.entities.creatures;

import com.AshkanMofidi.TankGame.Game;
import com.AshkanMofidi.TankGame.entities.Entity;

import java.awt.*;

/*
    This class is our creature class which I make it abstract since its a generalized class of all creatures
    And I don't want random creatures running all over the place; therefore, it's abstract
 */
public abstract  class Creature extends Entity {

    /*
        Now, I have to add all the things that are specified to the Creature class
        So, I'm going to add health variable since all creatures have their own health
     */
    protected float angle;

    /*
        We define these final variables as default for each creature
     */
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    /*
        each creature should move, and they move with a certain speed
     */
    protected int health;
    protected float speed;
    protected float xMove, yMove;

    /*
        Now, every creature have a x and y position on the screen
     */
    public Creature(Game game, float x, float y, int width, int height) {
        /*
            This super class will lead to the class that I extend it; in this case, it leads to the Entity class
         */
        super(game, x, y, width, height);
        //I'm going to create methods to change this health value
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    @Override
    public boolean tick() {
        return false;
    }

    @Override
    public void render(Graphics g) throws Exception {

    }

    public void move(){
        x += xMove;
        y += yMove;
    }

    /*
        We define getters and setters to access these variables outside of their classes
     */
    public float getxMove(){
        return xMove;
    }
    public float getyMove(){
        return yMove;
    }
    public void setxMove(float xMove){
        this.xMove = xMove;
    }
    public void setyMove(float yMove){
        this.yMove = yMove;
    }
    public void setSpeed(float speed){
        this.speed = speed;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public float getSpeed(){
        return speed;
    }
    public int getHealth(){
        return health;
    }
}
