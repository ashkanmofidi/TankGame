package com.AshkanMofidi.TankGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
    This class will manage all the key board inputs for our game program
    This class must implements the KeyListener class which is a class that java already has which
    allows us to access the keyboard
 */
public class KeyManager implements KeyListener {

    /*
        We are going to create a boolean array here
        Why boolean? Because, we can have their indexes representing the key code (which is a three-digit code) and
        their value either true or false (either pressed or released)
     */
    private boolean [] keys;
    /*
        Here we define the name of our keys that we want to use in this game
        up for moving towards y to up
        down for moving along y to down
        left for moving along x to left
        right for moving along x to right
        space for doing an specific action like shooting
        enter for doing an specific action like shooting
     */
    public boolean up, down, left, right, space, enter, esc;
    /*
        First, I'm going to create a key manager constructor
        In this constructor method we initialize our keys arrays and we set the size to 256 which gives us enough room to represent our key codes
        Because every single key in our keyboard has an id attached to it which we call it a key code
     */
    public KeyManager(){
        keys = new boolean[256];


    }

    /*
        In order for this class to be completely reliable, we are going to define a tick() function here so the values can get updated live
        This tick() method is just like the ones in our GameState or Player class
        We are going to call it constantly many times a second to move do the keyboard actions on time
     */
    public void tick(){
                /*
            We have to get the code for up arrow key of the keyboard
            In order to get the code associated with certain keys, we should use KeyEvent. and then we can see the list of all the codes for different keys
            Any keys start with VK and then underscore VK_(the name of the code)
         */
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        enter = keys[KeyEvent.VK_ENTER];
        esc = keys[KeyEvent.VK_ESCAPE];
    }


    /*
        This method will be called whenever I press a key on my keyboard
        I'm going to develop a way to figure out weather a key is pressed or not
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        //To test our KeyManager class
        System.out.println("Pressed!");
    }


    /*
        This method do the opposite; it will be called whenever I released that key on my keyboard
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }
}
