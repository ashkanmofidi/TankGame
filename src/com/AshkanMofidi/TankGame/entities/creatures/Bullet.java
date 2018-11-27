package com.AshkanMofidi.TankGame.entities.creatures;

import com.AshkanMofidi.TankGame.Game;
import com.AshkanMofidi.TankGame.gfx.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet extends Creature {
    private double  rad, speed, dx, dy;
    private int r;
    private Color color1;
    private Player player;
    private Game game;
    private boolean show;


    public Bullet(float x, float y, double angle){
        super(x, y);
        //Radiuse of the bullet
        r = 3;
        this.angle = (float) angle;
        rad = Math.toRadians(this.angle);
        speed = 15;
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;
        color1 = Color.RED;


    }


    public boolean tick() {
//        System.out.println("CALL-1");
        x += dx;
        y += dy;
        if(game != null){
            if(x < -r || x > game.getWidth() + r || y < -r || y > game.getHeight() + r){
                return true;
            }
            return false;
        }
        return false;
    }

    public void render(Graphics g) {
            g.setColor(color1);
            g.fillOval((int) ((x - r) + 25), (int) ((y - r) +25),  r, r);
            System.out.println("YAyyyy");
    }

//    public void render(Graphics g) throws Exception{
//        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
//        rotation.rotate(rad, Assets.bullet.getWidth() / 2.0, Assets.bullet.getHeight() / 2.0);
//
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.drawImage(Assets.bullet, rotation, null);
//    }

}
