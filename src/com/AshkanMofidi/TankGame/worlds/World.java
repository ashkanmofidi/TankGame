package com.AshkanMofidi.TankGame.worlds;

import com.AshkanMofidi.TankGame.tiles.Tile;
import com.AshkanMofidi.TankGame.utils.Utils;

import java.awt.*;

public class World {
    /*
        This is width and height of our world in terms of tile sizes
     */
    public  int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    public World(String path){
        loadWorld(path);
    }

    public void tick(){

    }

    public void render(Graphics g){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }
    }

    /*
        By this method we get each tile out of our multi dimensional array
     */
    public Tile getTile(int x, int y){
        Tile t = Tile.tiles[tiles[x][y]];
        /*
            We have to check that the tile is not equal to null otherwise we break everything
         */
        if(t == null){
            return Tile.soilTile;
        }else{
            return t;
        }
    }

    /*
        This loadWorld function is going to get the file off our computer from whatever world we want to load and then
        going to get all the data from it and store them  into the multi dimensional array so we know where is the location
        of each tile
     */
    private void loadWorld (String path){
        /*
            If I look into my world file, I can see that I either separate things with character or new lines
         */
        String file = Utils.loadFileAsString(path);
        /*
            Now, I create a array of all the String in the file
            I also want to split everything based on spaces or newlines between them
         */
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        tiles = new int[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

}
