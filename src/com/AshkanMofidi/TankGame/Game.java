package com.AshkanMofidi.TankGame;
import com.AshkanMofidi.TankGame.Display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;

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

    /*
        To render, we need to define two variable
        BufferStrategy is basically a way for computer to draw things on the screen and it uses buffer to do that
        Buffer? a buffer is basically a hidden computer screen within our computer(a bunch of memory in our computer that holds the same data as
        our actual computer screen)
        Basically we are doing to draw everything into the buffer first, then when the drawing ends, this buffer is going to another buffer
        after that redraw again then that buffer goes into the actual screen so that we can see the actual drawings
        Why do we need to first draw into the buffer (a hidden screen) and then to the actual screen?
        -We do that to prevent any flickering into our game, to not be like a old computer game which has lots of flickering and low graphic quality
     */
    private BufferStrategy bs;
    private Graphics g;

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
        to draw we have access to canvas object inside the display class
        since the canvas object is set to private we have to use its setters and getters
        Every time this render method runs we need to do the same thing over and over again
        *****WE HAVE TO CLEAR THE SCREEN EVERY TIME WE RENDER******
     */
    private void render(){
        /*
            We need to initialize the above defined BufferStrategy object into the BufferStrategy of our display canvas
            BufferStrategy gets how many buffers that the canvas uses
         */

        bs = display.getCanvas().getBufferStrategy();
        /*
            if this is the first time running our game, our BufferStrategy or canvas doesn't have any BufferStrategy, so it has no clue how many
            buffer to use
            So, we create a buffer strategy for our canvas if it doesn't have one yet
            createBufferStrategy(TAKES THE NUMBER OF BUFFERS IT USE)
         */
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        /*
            Now that I can sure that our BufferStrategy has been set, we can begin drawing
            So, to draw into the canvas we are going to use our Graphics variable as something like a paintbrush
            Therefore, we set our Graphics objects that we define as g by getting the getDrawGraphics of our BufferStrategy
         */
        g = bs.getDrawGraphics();

        /*
            Every single time that the render runs, before drawing anything to the screen
            we have to clear the screen.
            clearRect() clears a rectangle portion of our screen within the JFrame
            therefore we have to pass it width and height that it clears everything on our screen
         */
        g.clearRect(0, 0,  width, height);
        //--------------DRAW HERE
        /*
            this method fill the rectangle inside our JFrame window
            this method takes 4 parameters:  x, y, width, height
            so, we should get a full rectangle filling out our JFrame window
         */
//        g.fillRect(0, 0, width, height);

        /*
            we can change the Graphics color by the following method:
            This line means that every thing I draw after this line of code should be that color that I set
         */
//        g.setColor(Color.red);
        /*
            To draw a rectangle in specific locations with specific size
         */
//        g.fillRect(10, 50, 50, 70);
//        g.setColor(Color.BLUE);
//        g.fillRect(0, 0, 10, 10);
//        g.fillOval(60, 60, 100, 200);


        //--------------END DRAWING!
        /*
            We have to actually tell java that we are done with our drawing
         */
        bs.show();
        g.dispose();
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
            //To close our thread safely we use the thread.join()
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
