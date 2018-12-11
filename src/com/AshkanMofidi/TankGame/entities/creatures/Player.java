package com.AshkanMofidi.TankGame.entities.creatures;

import com.AshkanMofidi.TankGame.Game;
import com.AshkanMofidi.TankGame.gfx.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;

/*
    Since player is a creature I put this class in the creatures package
 */
public class Player extends Creature{

    //rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
    //is the angle to rotate the image. If you want to rotate around the center of an image,
    //use the image's center x and y coordinates for rx and ry.


    /*
        our Player object needs to get a Game object since we want to get access to KeyManager inside the Game class
     */
    private boolean firing;
    private float vx;
    private float vy;
    private int shoot = 0;
    private final int R = 2;
    private final int ROTATIONSPEED = 10;
    private double rad;


    public Player(Game game, float x, float y, int width, int height, float vx, float vy, float angle, int shoot) {

        super(game, x, y, width, height);
        this.vx = vx;
        this.vy = vy;
        this.shoot = shoot;
        this.game = game;
        this.angle = angle;
        firing = false;
    }

    public void setFiring(boolean f){
        firing = f;
    }

    public boolean isFiring(){
        return firing;
    }

    /*
        Our player class needs to somehow get access to the KeyManger object to it can access its variables (up, down, left, right, and...)
        Therefore, I am going to create a getGameManager() method inside my Game class and under our run method there
    */

    @Override
    public boolean tick() {
        /*
            I'm going to make this code more object oriented in the future, but for now I just create this to test my movement function with keyboard inputs
            As I mentioned this not a proper way to do it and I'm going to change it in the future
         */
        if(game.getKeyManager().up){
            this.moveForwards();
        }
        if(game.getKeyManager().down){
            this.moveBackwards();
        }
        if(game.getKeyManager().right){
            this.rotateRight();
        }
        if(game.getKeyManager().left){
            this.rotateLeft();
        }
        if(game.getKeyManager().space){
            setFiring(true);
        }
        return true;
        }
    private void getInput(){
        xMove = 0;
        yMove = 0;
    }


    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {

                vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
                x -= vx;
                y -= vy;


        checkBorder();
    }

    private void moveForwards() {
        rad = Math.toRadians(angle);
        vx = (float) Math.cos(rad);
        vy = (float) Math.sin(rad);
//        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
//        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += (vx*2);
        y += (vy*2);
        checkBorder();
    }

    //MYCODE-BEGIN
    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public float getAngle(){
        return this.angle;
    }

    //MYCODE-END




    private void checkBorder() {
        if (x < 40) {
            x = 40;
        }
        if (x >= game.getHeight() - 25) {
            x = game.getWidth() - 25;
        }
        if (y < 10) {
            y = 10;
        }
        if (y >= game.getWidth() - 33) {
            y = game.getHeight() - 33;
        }
    }

    @Override
    public void render(Graphics g) throws Exception{
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), Assets.tankRed.getWidth() / 2.0, Assets.tankRed.getHeight() / 2.0);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(Assets.tankRed, rotation, null);

    }
}
