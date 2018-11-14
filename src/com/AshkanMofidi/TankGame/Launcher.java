package com.AshkanMofidi.TankGame;

import com.AshkanMofidi.TankGame.Display.Display;

/*
    This Launcher class is only responsible for starting up our game.
    The main method is the first method that any java program runs first.
 */
public class Launcher {

    public static void main(String[] args){
        /*
            This below line just set the game constructor and store it in game variable
            We save our game object into this below game variable
         */

        Game game = new Game("Ashkan's Game 2018", 400, 400);
        /*
            This line actually start a new thread that our game class run on it
            This will call the start method which will call the run method which will call the init method and start
            the game loop which has the tick and render method and run the while the loop argument is true
         */
        game.start();
    }
}
