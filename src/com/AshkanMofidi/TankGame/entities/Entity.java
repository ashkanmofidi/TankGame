package com.AshkanMofidi.TankGame.entities;

import com.AshkanMofidi.TankGame.Game;

import java.awt.Graphics;

/*
    I am going to make an entity based system in our game
    Anything in our game that is NOT a tile (like grass tile, tree tile, or dirt tile) is considered a entity
    Every single item in our game is going to be an entity
    Every single enemy or player in our game is going to be an entity as well
    All the mentioned above things have something in common since they are all entities
    I create this class abstract since we don't want anyone to create any random entities
    I want to create a specific entity like a player or an item
    So, we know tha evey single entity needs a position on the screen (x and y) and tick() and render() method to both update and draw itself
 */
public abstract class Entity {
    /*
        I am going to create our x and y variable for the location of our entities on the screen
        I define them as protected which is like private but all the classes that extend this class have access to them
        I also define them as float since we want to achieve smooth movements in our game
        It maybe somehow strange to use float since I can't just display half of a pixel on the screen and I can only display the whole pixels
        So, why float? Because, the calculations in my game is not going to be perfect, and this is how I can achieve a smooth look in my game
     */
    protected float x, y;
    protected Game game;

    /*
        Let's define two variables for the width and height of our entities
     */
    protected int width, height;

    /*
        I define the constructor of the game and I pass the x, and y as two arguments to set the start point of my entity
     */
    public Entity(Game game, float x, float y, int width, int height){
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /*
        Now, I gonna define the tick() and render() methods of my Entity class and make them abstract since different entity use different code to tick and
        render themselves into a screen
        In order for the render method to draw on the screen I need to pass a Graphics object to it, so that it can draw itself on the screen
     */
    public abstract boolean tick();

    public abstract void render(Graphics g) throws Exception;

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }

}
