package com.AshkanMofidi.TankGame.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
    I created this class that I prevent any unnecessary code repetition
    In this class I crop/ import all the images, characters, sounds, textures that I want to use throughout my game
    And then, I can use the imported items efficiently multiple times and in different location throughout my game
 */
public class Assets {

    /*
    Now, we define variables to contain all the images that I want to use in my game
    I define them as public so that I can get access to them from anywhere
 */
    public static BufferedImage redApple, greenApple, strawberry, toast, blueKey, greenKey, banana, candy, candyBar, goldKey, yellowKey, orangeKey;

    public static BufferedImage explosion1, explosion2, explosion3, explosion4, explosion5;

    public static BufferedImage[] expWithSmoke;


    public static BufferedImage[] explosions;

    //These are the width and height of each photos inside SpriteSheet sheetExplosionWithSpoke
    private static final int WIDTHOFEXPWITHSMOKE = 128;
    private static final int HEIGHTOFEXPWITHSMOKE = 127;

    //This is the distance along the x which set to be the beginning borderline of each photo inside our split sheet
    private static int xForEachImg = 0;

    /*
        Instead of hardcoding all the numbers in for cropping, I'm going to define variables to be more clear
     */
    private static final int width = 18, height = 18, outerMargin = 5, innerSpace = 2;

    /*
        In this class, I'm going to put only one static method and will call it init
        This init method will load everything that I want to use throughout my game and it's going to be called only once throughout my game
     */
    public static void init(){


        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        redApple = sheet.crop(outerMargin, outerMargin, width, height);
        greenApple = sheet.crop(outerMargin + width + innerSpace, outerMargin, width, height);
        strawberry = sheet.crop(outerMargin + 2 * width + 2 * innerSpace, outerMargin, width, height);
        toast = sheet.crop(outerMargin + 3 * width + 3 * innerSpace, outerMargin, width, height);
        blueKey = sheet.crop(outerMargin + 4 * width + 4 * innerSpace, outerMargin, width, height);
        greenKey = sheet.crop(outerMargin + 5 * width + 5 * innerSpace, outerMargin, width, height);
        banana = sheet.crop(outerMargin, outerMargin + width, width, height);
        candy = sheet.crop(outerMargin + width + innerSpace, outerMargin + width, width, height);
        candyBar = sheet.crop(outerMargin + 2 * width + 2 * innerSpace, outerMargin + width, width, height);
        goldKey = sheet.crop(outerMargin + 3 * width + 3 * innerSpace, outerMargin + width, width, height);
        yellowKey = sheet.crop(outerMargin + 4 * width + 4 * innerSpace, outerMargin + width, width, height);
        orangeKey = sheet.crop(outerMargin + 5 * width + 5 * innerSpace, outerMargin + width, width, height);

        SpriteSheet sheetOfExplosions = new SpriteSheet(ImageLoader.loadImage("/textures/explosion.png"));
        explosion1 = sheetOfExplosions.crop(0, 0, 14, 15);
        explosion2 = sheetOfExplosions.crop(17, 0, 14, 15);
        explosion3 = sheetOfExplosions.crop(32, 0, 15, 15);
        explosion4 = sheetOfExplosions.crop(47, 0, 17, 15);
        explosion5 = sheetOfExplosions.crop(64, 0, 16, 15);
        explosions = new BufferedImage[]{explosion1, explosion2, explosion3, explosion4, explosion5};

        SpriteSheet sheetExplosionWithSpoke = new SpriteSheet(ImageLoader.loadImage("/textures/explosion4.png"));
        expWithSmoke = new BufferedImage[12];
        for(int i = 0; i < 11; i++){
            expWithSmoke[i] = sheetExplosionWithSpoke.crop(xForEachImg, 0, WIDTHOFEXPWITHSMOKE, HEIGHTOFEXPWITHSMOKE);
            xForEachImg += WIDTHOFEXPWITHSMOKE;
        }



    }
}
