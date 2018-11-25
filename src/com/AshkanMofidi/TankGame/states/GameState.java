package com.AshkanMofidi.TankGame.states;

import com.AshkanMofidi.TankGame.gfx.Assets;

import java.awt.*;

/*
    Now our GameState class is a State since it extends the State class
    So, we override the abstract methods of our State class here
 */
public class GameState extends State {


    /*
        We need this constructor in the future
     */
    public GameState(){

    }
    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.banana, 0, 0, null);
    }
}
