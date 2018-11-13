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
    * Basically, without using thread, when we run our game it creates a big program to run
    * However, in I want this class to be run separately on a MINI PROGRAM within the bigger program
    * Thread can help us with efficiency
    * To allow this class to run on a thread and separately from all the rest of our codes
    * we have to implement Runnable which allows our class to run on thread
    * and then we have to override its run() method in this class
    * and then we define an actual thread variable to run this class onto that
 */


public class Game implements Runnable{

    /*
        We define the Game constructor here
        our Game also need an instance of our Display class here as well
     */
    private Display display;

    /*
        we also define two variables, width and height, so that our class can easily get access to them
     */
    int width, height;
    private String title;

    /*
        initially this running method is set to false
        this running method should be updated to know if we need to run the game or not
        it will be used inside the run method
     */
    private boolean running = false;

    //we create an actual thread to run this class on that
    //We initialize this thread object by two methods, start() & stop()
    private Thread thread;

    //We have ot initialize our display object in the Game constructor
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
    }


    /*
        This init method basically initialize all the graphics of our game and runs once when we call our
        run() method
     */
    public void init(){
        /*
            our constructors already set the fields and we stored them to have
            access throught this program (title, width, and height)
            Now we pass them into the display to create a new instance of it
         */
        display = new Display(title, width, height);
    }

    /*
        this tick methods updates all variables
     */
    private void tick(){

    }

    /*
        This render method draw our game for us
     */
    private void render(){

    }

    /*
        a majority of our Game code goes inside this run method
        the first thing we do in the run method is calling a init() method
        which I define above
     */
    @Override
    public void run() {
        /*
            The first thing that we do in our run method is initializing the above init() method
         */
        init();
        /*
            Then we want to run the game loop of our game
            which updates all variables, positions of objects, etc
            Draw (render) everything to the screen
         */
        while(running){
            tick();
            render();
        }

        //We call the stop method in case if the stop the thread if it hasn't already been stopped
        stop();

    }


    /*
        To initialize the thread in the method called start()
        We basically use synchronized keyword when we work with thread directly
        to prevent anything to be missed up
        In conclusion, whenever we call our start method, we initialize our thread to a new thread
        and then we take this class to run on that new thread
     */
    public synchronized void start(){
        /*
            we initially set the running to false
            so we need to somewhere set it to true
            so what a better place that here to do that!!!
            But what if the game is already running?!
            If the game is running and accidentally this start method gets called it would mess up our game
            since it reinitialize a new thread
            so, we need to check whether the game is running or not before starting the thread
         */
        if(running)
            return;// and don't do the rest of these codes
        running = true;

        /*
            we want to use the thread to run this current class
            Because this class implements the Runnable it allows us to pass this into the Thread argument
         */
        thread = new Thread(this);
        /*
            and now we have to start the thread
            This start method actually calls the above run method
            thread.start() actually calls the above run method
         */
        thread.start();

    }

    public synchronized void stop(){
        /*
            We need to make sure whether the game hasn't been stopped yet
            because if we re stop the game then it would cause a bunch of errors
         */
        if(!running)
            return;// and don't do the rest of these codes
        running = false;
        try {
            //To stop our thread safely we use the thread.join()
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
