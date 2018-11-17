package com.AshkanMofidi.TankGame.gfx;

import java.awt.image.BufferedImage;

/*
    In java, when we want to import bunch of game characters, we put them all in one png file and then with java we extract and load each of them
    separately
    This class will store a BufferedImage object
 */
public class SpriteSheet {
    private BufferedImage sheet;

    /*
        Now, we have to have our class constructor
        We throw a BufferedImage object right into the constructor of our SpriteSheet class so
        that we can manipulate that whole image
     */
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    /*
        This crop method will return the cropped part of the sheet object that we initially passed inside the class constructor
        this crop method takes 4 parameters, x & y as coordinates of our image to set which point we want to manipulate
        and width & height which set the targeted area that we want crop from the entire photo
        We also only need sub image of the whole photo by passing the same exact parameters
     */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
