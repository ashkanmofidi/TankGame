package com.AshkanMofidi.TankGame.entities.creatures;

import com.AshkanMofidi.TankGame.gfx.Assets;

import java.awt.*;

/*
    Since player is a creature I put this class in the creatures package
 */
public class Player extends Creature{

    public Player(float x, float y) {
        super(x, y);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        /*
            Now, the Player needs to be drawn on the screen
            This is not a ideal way to do it, but it perfectly works best for me right now
            I use the position of the Entity class as it variables
            I should advise that x and y of the Entity class are floats; therefore to use them here I have to cast them into int
         */
        g.drawImage(Assets.tankRed, (int) x, (int) y, null);
    }
}
