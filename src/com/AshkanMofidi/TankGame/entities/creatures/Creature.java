package com.AshkanMofidi.TankGame.entities.creatures;

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
    protected int health;
    protected float angle;

    /*
        Now, every creature have a x and y position on the screen
     */
    public Creature(float x, float y) {
        /*
            This super class will lead to the class that I extend it; in this case, it leads to the Entity class
         */
        super(x, y);
        //I'm going to create methods to change this health value
        health = 10;
    }

    @Override
    public boolean tick() {
        return false;
    }

    @Override
    public void render(Graphics g) throws Exception {

    }
}
