package com.AshkanMofidi.TankGame.tiles;

import com.AshkanMofidi.TankGame.gfx.Assets;

import java.awt.image.BufferedImage;

public class StoneTile extends Tile {
    public StoneTile(int id) {
        super(Assets.rock, id);
    }

    /*
        Now we have to override the isSolid method of the super class and set it to true since
        StoneTile is not walkable
     */
    @Override
    public boolean isSolid(){
        return true;
    }

}
