package com.AshkanMofidi.TankGame;


import com.AshkanMofidi.TankGame.Display.Display;

/*
The Game class is the main class of our game
It will hold all the base code of our game
It start everything
It run everything
and it close out our game
    ****THREAD****
    * So, for this Game I'm going to use threads
    * Basically, in the traditional way, it creates a big PROGRAM for all of our code to run it
    * But, now we want our Game class to run on its own MINI PROGRAM, and that's what a thread is about
    * therefore, by creating a thread for this class
    * when we run the main game this class runs separately from all the rest of the code of in our application
    * Thread can help us with efficiency
    * To allow this class to run on a thread we have to implement Runnable
    * and then we have to override the run() method in this class
    * and we have to have a actual thread to run onto that
 */


public class Game implements Runnable{

    /*
    We define the Game constructor here
    our Game also need an instance of our Display class here as well
     */
    private Display display;

    /*
    we also define two variables, width and height, so that class can easily get access to them
     */
    int width, height;


    //we create an actual thread to run this class on that
    //We initialize this thread object by two methods, start() & stop
    private Thread thread;

    //We have ot initialize our display object in the Game constructor
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;

        //we pass the constructor argument right away into the Display as arguments
        display = new Display(title, width, height);

    }


    /*
    This init method basically initialize all the game graphics for our game 
     */

    /*
    a majority of our Game code goes inside this run method
    the first thing we do in the run method is calling a init() method
    which I define above
     */
    @Override
    public void run() {

    }


    /*
        To initialize the thread object by a method called start()
        * We basically use synchronized keyword when we work with thread directly to prevent anything to be missed up
     */
    public synchronized void start(){
        //we want to use our thread to run this current class, this line run the Game class on the thread
        thread = new Thread(this);
        /*
        and then we have to start our thread
        This start method actually calls the above run method
         */
        thread.start();

    }

    public synchronized void stope(){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
