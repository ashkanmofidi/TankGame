package com.AshkanMofidi.TankGame;
import com.AshkanMofidi.TankGame.Display.Display;
import com.AshkanMofidi.TankGame.gfx.Assets;
import com.AshkanMofidi.TankGame.input.KeyManager;
import com.AshkanMofidi.TankGame.states.*;
import com.AshkanMofidi.TankGame.gfx.ImageLoader;
import com.AshkanMofidi.TankGame.gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

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
    //++++++++
    private void insertDelay(final int DELAY_TIME) {
        try {
            Thread.sleep(DELAY_TIME);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    //+++++++++++

    /*
        We define the Game constructor here
        our Game also need an instance of our Display class here as well
     */
    private Display display;

    /*
        we also define two variables, width and height, so that our class can easily get access to them
     */
    private int width, height;
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

    /*
        SINCE THIS GAME CLASS IS OUR MAIN CLASS, WE GONNA DECLARE EVERY SINGLE STATE THAT WE HAVE IN THIS GAME PROGRAM
        We have to declare a gameState object that we can set the state of out game program
        we initialize this object inside our init() method
        STATES
     */
    private State gameState;
    private State menuState;
    private State settingState;
    private State gameWinState;
    private State gameOverState;

    /*
        INPUTS
        We initialize it inside our Game constructor
        Then, we pass it as an argument to the addKeyListener inside our init() method
     */
    private KeyManager keyManager;

    /*
        To just test my ImageLoader class, I create a BufferedImage object here
     */
//    private BufferedImage testImage;

    /*
        To load the sprite sheet containing game characters
     */
//

    //We have ot initialize our display object in the Game constructor
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
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

        /*
            To just test my ImageLoader class, I create a BufferedImage object here
            So, since the loadImage() method inside the ImageLoader class is static
            we can call it like ImageLoader.loadImage(); therefore we do not need
            to create an object of ImageLoader and through that object get access to
            the loadImage() function. Then we have to pass the path of our image into
            the loadImage function. Since I already linked the resource folder to the java
            build path, all we have to do is putting a slash to access it "/"
        */
//        testImage = ImageLoader.loadImage("/textures/Air.png");

//        testImg = ImageLoader.loadImage("/textures/sheet.png");
//        sheet = new SpriteSheet(testImg);

        /*
            Now, I need to get the JFrame and then add a key listener on it so it can listen to the inserted key on our keyboard
            Which takes a parameter of a KeyListener since our class calls KeyManager and KeyManager implements the KeyListener
            To do that we need to create an object of our KeyManager class at the top of this class
            Long story short, what we are doing here is that after creating a display (above) then we get its JFrame of our actual display window(down)
            and then we are adding a key listener; this allows us to access the keyboard and then we pass it a keyManager object that we created on top of this class
            and initialized it inside the class constructor. We are able to do that since our KeyManager class implements the KeyListener Interface/ Class
         */
        display.getFrame().addKeyListener(keyManager);
        //We initialize all of our assets
        Assets.init();

        /*
            SINCE THIS GAME CLASS IS OUR MAIN CLASS, WE GONNA INITIALIZE EVERY SINGLE STATE THAT WE HAVE IN OUR GAME PROGRAM
            We have initialized the State of our game here
            Because our GmaeState class extends the State class we can initialize the State object to the GameState
            ---------
            Since, we are in the Game class itself and since the argument for their game state constructors should we game, in this case we just pass
            this to their constructors since we are in the game class itself
         */
        gameState = new GameState(this);
        menuState = new MenuState(this);
        settingState = new SettingState(this);
        gameWinState = new GameWinState(this);
        gameOverState = new GameOverState(this);

        /*
            This create the current state of our game that we want to tick and render to
            for now -----ONLY----WE HAVE TO MAKE SURE WE SET OUR STATE ONLY TO GAMESTATE since we gonna write code only for this state for a while
         */
        StateManager.setState(gameState);
    }

    /*
        calling this function will simulate the fire in respect to explosion4.png image
        x & y are the coordinates of the place that we want to place the fire
        WIDTH & HEIGHT are the size of each frame
     */
    public void expWithSmoke(Graphics g, int x, int y){
        int timePerFrame = 10;
        final int WIDTH = 128;
        final int HEIGHT = 128;
        for(int i = 0; i < 11; i++){
            timePerFrame += 20;
            g.drawImage(Assets.expWithSmoke[i], x, y, null);
            bs.show();
            g.clearRect(x, y, WIDTH, HEIGHT);
            insertDelay(timePerFrame);
        }
    }


    /*
        calling this function will simulate the fire without any smoke in respect to explosion.png image
        x & y are the coordinates of the place that we want to place the fire
        WIDTH & HEIGHT are the size of each frame
        timePerFrame is the required time in millisecond that we want each sub image in the explosion.png to be shown
     */
    public void explWithoutSmoke(Graphics g, BufferStrategy bs, int x, int y) {
        int timePerFrame = 6;
        final int WIDTH = 16;
        final int HEIGHT = 16;
        for (int i = 0; i < 5; i++) {
            timePerFrame += 30;
            g.drawImage(Assets.explosions[i], x, y, null);
            bs.show();
            insertDelay(timePerFrame);
            g.clearRect(x, y, WIDTH, HEIGHT);
        }
    }


    /*
        this tick methods updates all variables
        This method will be called many many times every single seconds!
     */
        private void tick () {

            keyManager.tick();
            /*
                if the State exist then tick()
             */
            if(StateManager.getState() != null){
                StateManager.getState().tick();
            }
        }

    /*
        This render method draw our game for us
        to draw we have access to canvas object inside the display class
        since the canvas object is set to private we have to use its setters and getters
        Every time this render method runs we need to do the same thing over and over again
        *****WE HAVE TO CLEAR THE SCREEN EVERY TIME WE RENDER******
        *********All the code in this render method will be called many many times each second
        * So we have to prevent any unnecessary repetitive steps for speed and game efficiency********
     */
        private void render () throws Exception {
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
            if (bs == null) {
                display.getCanvas().createBufferStrategy(4);
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
            g.clearRect(0, 0, width, height);
            //--------------DRAW HERE

            /*
                This if state is really important because if our gameState.getState() ever equals null, it through error
                Basically, if the State exist then render()
             */
            if(StateManager.getState() != null){
                StateManager.getState().render(g);
            }
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


        /*
            In this render loop how do I render the images? So, Graphics objects have
            all the magical power to do that. So, we use g.drawImage(which takes at minimum 4 parameters)
            the first parameter should be the image that we want to draw, in this case, the testImage, then
            it takes x & y coordinates and it takes a null as its last parameter (which is an image observer)
         */
//        g.drawImage(testImage, 20, 20, null);
        /*
            I call the below crop method many many times per second
            So, why should I crop the same character many many times per second?!
            Good question, so I have to eliminate any unnecessary steps here to avoid them to be run repetitively
            This elimination will increase the speeds and makes our game to run efficiently
            Therefore, I'm going to create an assets class to crop and import game character only once but we still can
            use them multiple times throughout our game application
         */
//        g.drawImage(sheet.crop(25, 5, 18, 18), 5, 5, null);
//        g.drawImage(Assets.banana, 5, 5, null);
//        g.drawImage(Assets.candyBar, 20, 20, null);
//        g.drawImage(Assets.blueKey, 50, 50, null);
//        g.drawImage(Assets.redApple, 20, 100, null);
//        g.drawImage(Assets.strawberry, 100, 180, null);

            bs.show();
//        insertDelay(50);
//
//        g.drawImage(Assets.explosion1, 0 ,0, null);
//        insertDelay(100);
//        bs.show();
//        g.drawImage(Assets.explosion2, 30, 30, null);
//        insertDelay(50);
//        bs.show();
//        g.drawImage(Assets.explosion3, 60, 60, null);
//        insertDelay(50);
//        bs.show();
//
//        g.drawImage(Assets.explosion4, 90, 90, null);
//        insertDelay(50);
//        bs.show();
//
//        g.drawImage(Assets.explosion5, 120, 120, null);
//        insertDelay(50);


//        for(int i = 0; i < 5; i++){
//            y+=10;
//            g.drawImage(Assets.explosions[i], 89, 90, null);
//            bs.show();
//            insertDelay(y);
//        }
//        g.clearRect(89, 91, 17, 17);
//
//        for(int i = 0; i < 5; i++){
//            y+=10;
//            g.drawImage(Assets.explosions[i], 92, 92, null);
//            bs.show();
//            insertDelay(y);
//        }



//            explWithoutSmoke(g, bs, 0, 0);
//            explWithoutSmoke(g, bs, 25, 30);

//            g.drawImage(Assets.tank, x, 30, null);
//            expWithSmoke(g, bs, x, 30);
//
//
//            expWithSmoke(g, bs, 20, 150);
//
//
//            explWithoutSmoke(g, bs, 150, 30);
//
//            explWithoutSmoke(g, bs, 20, 130);
//            explWithoutSmoke(g, bs, 30, 25);
//
//            expWithSmoke(g, bs, 20, 10);
//            expWithSmoke(g, bs, 150, 10);
//            expWithSmoke(g, bs, 20, 100);
//            expWithSmoke(g, bs, 300, 30);



            //--------------END DRAWING!!!
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
        public void run () {
        /*
            The first thing that we do in our run method is initializing the above init() method
         */
            init();
        /*
            Then we want to run the game loop of our game
            which updates all variables, positions of objects, etc
            Draw (render) everything to the screen
            -------------------------------------------------------------------------------
            I created this x variable that my game runs perfectly on every different computers
            ****I WANT MY GAME TO RUN AT THE SAME SPEED ON ANY COMPUTER NO MATTER IF THE COMPUTER IS FAST OR SLOW*****
            * Therefore, I'm going to limit how many times the tick() and render() method inside the run() method run in every single second!
            * I'm going to define an int variable calling it fps (frames or ticks / second) and set it to 60 which means how many times in every
            * second we want our tick() and render() method to run
            * Then, I will define a double variable calling it timePerTick and will set it to 1 billion; why? Because, there are 1 billion nano seconds in one second
            * Because, usually when we do computer stuff, we measure time in nano seconds rather than in seconds, because it is much much more specific
            -------------------------------------------------------------------------------
         */
        //fps = frame per second
        int fps = 60;
        //The number of nano seconds per second which is 1 billion
        final int NANOINSECOND = 1000000000;
        //timePerTick in nano second
        double timePerTick = NANOINSECOND / fps;
        double delta = 0;
        long now;
        //System.nanoTime() returns the current time of our computer but in nano seconds
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

            while (running) {
                //We set the now variable to the current time of our computer
                now = System.nanoTime();
                /*
                    (now - lastTime) is the time past between last time we call this line of code
                    This delta variable basically tells our computer when and when not call these tick() and render() functions
                    The data variable will be added by at the top of our game loop
                 */
                delta += (now - lastTime) / timePerTick;
                /*
                    Timer indicates the amount of time passed since we call this piece of the code.
                 */
                timer += now - lastTime;
                lastTime = now;

                /*
                    Now we have to check if we should call these tick() and render() methods
                    So, if delta is equal or larger than 1 we know that we have to run tick() and render() methods to reach our 60 frame per seconds
                 */
                if(delta >= 1){
                    tick();
                    try {
                        render();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //We want to increment the tick method by one each time we run this code
                    ticks++;
                    //We tick() and render() one time, now we have to take 1 out of our delta
                    delta--;
                }

                /*
                    NANOINSECOND = 1 second
                    This block of if code will visualize for us how many frames we are running our game in
                 */
                if(timer >= NANOINSECOND){
                    System.out.println("Ticks and Frames: " + ticks);
                    ticks = 0;
                    timer = 0;
                }

            }



        //We call the stop method in case if the stop the thread if it hasn't already been stopped
        stop();



        }

        public KeyManager getKeyManager(){
            return keyManager;
        }


    /*
        To initialize the thread in the method called start()
        We basically use synchronized keyword when we work with thread directly
        to prevent anything to be missed up
        In conclusion, whenever we call our start method, we initialize our thread to a new thread
        and then we take this class to run on that new thread
     */
        public synchronized void start () {
        /*
            we initially set the running to false
            so we need to somewhere set it to true
            so what a better place that here to do that!!!
            But what if the game is already running?!
            If the game is running and accidentally this start method gets called it would mess up our game
            since it reinitialize a new thread
            so, we need to check whether the game is running or not before starting the thread
         */
            if (running)
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

        public synchronized void stop () {
        /*
            We need to make sure whether the game hasn't been stopped yet
            because if we re stop the game then it would cause a bunch of errors
         */
            if (!running)
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


