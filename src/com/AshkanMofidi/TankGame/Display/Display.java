package com.AshkanMofidi.TankGame.Display;

import javax.swing.JFrame;
import java.awt.*;

public class Display {

    /*
    We use JFrame to create a window in java.
    So we create a private JFrame object here and we call it frame.
    We don't just initialize this frame yet.
     */
    private JFrame frame;

    /*
    We use something like canvas object allow us to draw graphics in our
    JFrame window
    Basically we paint or draw all of our graphics into this canvas object
    and then we add this canvas element into our JFrame so that we can see it
     */
    private Canvas canvas;

    /*
    Our JFrame object needs at least 3 things to display a window to a screen
    A title, a width, and height
    We define them as private since other classes doesn't need to get access to them
     */

    private String title;
    //the width and height are in terms of pixels
    private int width, height;

    /*
    We haven't initialized anything
    So we initialized them in the constructor of the class
     */
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    /*
    So instead of cluttering every initialization on above constructor
    we gonna initialize the JFrame in a new function
    and then we call that method inside the above constructor for more organization
     */
    private void createDisplay(){
        //the JFrame gets the title as an argument
        frame = new JFrame(title);
        frame.setSize(width, height);

        /*
        JFrame have to set a little bit more
        We have to set frame to set default close operation, this line makes
        sure that the window closes properly
        If we don't have this line of code, when we press X botton at the the top of the window
        the window may close, but the game mey not close in the background and it'll still running:))
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
        Resizable is for user to drag the window to resize it
        We do not want that setting right now
        We set it to false so that the window gets stuck at the width and height that we gave it
         */
        frame.setResizable(false);

        /*
        By this methods when we open the window, the window will appear
        in the center of the screen instead of a side
         */
        frame.setLocationRelativeTo(null);

        /*
        By this method we make our window visible
         */
        frame.setVisible(true);


        canvas = new Canvas();

        /*
        unlike JFrame, we can't directly define a size for canvas
        so we have to pass the width and height through a dimension object
         */
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        //We have to add our initialized canvas into our JFrame window so we can be able to see it
        frame.add(canvas);
        //We have to use pack func which resize the window that we can be sure we can see all of it
        frame.pack();








    }


}
