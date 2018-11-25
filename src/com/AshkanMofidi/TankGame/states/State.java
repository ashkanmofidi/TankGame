package com.AshkanMofidi.TankGame.states;


import java.awt.Graphics;

/*
    Instead of putting all the codes for main menu, settings, and the game itself altogether in one place
    we put the code for each single state in its own class
    This organize our code and make it much more clean
    Basically States are the separate areas of our game, but they have something in common
    Every State has tick() and render() method to update itself, so therefore we make this class as abstract class
 */
public abstract class State {


    /*
        We put all the things that are common between every single states
        Therefore, every single states must have all the functions of this class
     */
    public abstract void tick();

    /*
        render methods takes a Graphic object which we call it g
        Why we take a Graphics object g?! In our Game class we have a graphic objects which we called g, which was our magical game brush which allows us to draw on screen
        so, we have to pass the magical paint brush of our Game class to here, so that the state can be able to draw into screen directly
     */
    public abstract void render(Graphics g);
}
