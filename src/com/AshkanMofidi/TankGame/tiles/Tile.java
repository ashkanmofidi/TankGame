package com.AshkanMofidi.TankGame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC STUFF HERE
    /*
        This Tile array will hold ONE instance of every SINGLE  tile in our game
     */
    public static Tile[] tiles = new Tile[256];
    /*
        So since grassTile is a Tile but it has its own class
        We give the grassTile the id of 0
        Therefore, id of 0 is referred to grassTile all the time
        WE SHOULD GIVE EVERY SINGLE TILE A UNIQUE ID
     */
    public static Tile grassTile  = new GrassTile(0);
    public static Tile soilTile = new SoilTile(1);
    public static Tile wallTile = new WallTile(2);
//    public static Tile rockTile = new StoneTile(3);

    //CLASS
    public static final int TILEWIDTH = 17, TILEHEIGHT = 17;
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        /*
            Therefore, tiles[id] is going to be the particular tile that we are making in subclasses. this refers to the BufferedImage texture that
            we pass into this constructor
         */
        tiles[id] = this;
    }


    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    /*
        This method determines weather our tile is walkable or not
        This method return false as default
     */
    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }
}
