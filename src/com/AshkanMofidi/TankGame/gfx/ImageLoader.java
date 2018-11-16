package com.AshkanMofidi.TankGame.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
    This ImageLoader class just simply load our images for us
 */
public class ImageLoader {
    /*
        Java stores images in BufferedImage objects
        To load image we define a static method so that we can get access to it from everywhere
        Its going to return a BufferedImage object, which is an object that has the image in it
        this methods gets a String path, which is the location of our target image, as its parameter
     */
    public static BufferedImage loadImage(String path){

        /*
            So lets return our BufferedImage object of our loaded Image
         */
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            /*
                Because if we can't load our image we do not want run our game
             */
            System.exit(1);
        }
        /*
            We return null to just get rid of all java errors
         */
        return null;
    }

}
