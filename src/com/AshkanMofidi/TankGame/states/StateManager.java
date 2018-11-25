package com.AshkanMofidi.TankGame.states;

public class StateManager {

    /*
    We right GameState manager code here
    GameState manager code is a code that we right to tell in which state we run the game in
    We create a State object and we call it a current state and make it static that we can access it from anywhere
    This currentState object is going to hold what State we currently want to tick and render in our game

    */
    private static State currentState = null;

    /*
        Now we need a method that we can set this currentState object
     */
    public static void setState(State state){
        currentState = state;
    }
    public static State getState(){
        return currentState;
    }
}
