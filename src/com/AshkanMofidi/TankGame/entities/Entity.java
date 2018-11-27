package com.AshkanMofidi.TankGame.entities;

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

    /*
        I define the constructor of the game and I pass the x, and y as two arguments to set the start point of my entity
     */
    public Entity(float x, float y){
        this.x = x;
        this.y = y;
    }

    /*
        Now, I gonna define the tick() and render() methods of my Entity class and make them abstract since different entity use different code to tick and
        render themselves into a screen
        In order for the render method to draw on the screen I need to pass a Graphics object to it, so that it can draw itself on the screen
     */
    public abstract boolean tick();

    public abstract void render(Graphics g) throws Exception;

}
