package com.AshkanMofidi.TankGame.states;

import com.AshkanMofidi.TankGame.Game;
import com.AshkanMofidi.TankGame.entities.creatures.Bullet;
import com.AshkanMofidi.TankGame.entities.creatures.Player;
import com.AshkanMofidi.TankGame.gfx.Assets;

import java.awt.*;
import java.util.ArrayList;

/*
    Now our GameState class is a State since it extends the State class
    So, we override the abstract methods of our State class here
 */
public class GameState extends State {

    /*
        Now, we write codes to use the codes that we wrote inside the Player class in the entities and creatures packages
     */
    private Player player;
//    private boolean remove;
    public static ArrayList<Bullet> bullets;
//    public Bullet b;
    private long firingTimer, firingDelay;

//    private Bullet bullet;

    /*
        We need this constructor in the future
     */
    public GameState(Game game){
        super(game);


        /*
            The player constructor takes a x and y variables as its position (the starting positions of our player )
         */
        player = new Player(0, 0, 0, 0, 0, 0, game);

//        b = new Bullet(0, 0, 0);
        player.setFiring(false);
        firingTimer = System.nanoTime();
        firingDelay = 500;
        bullets = new ArrayList<Bullet>();


    }
    @Override
    public void tick() {
        player.tick();


        for(int i = 0; i < bullets.size(); i++){
            boolean remove = bullets.get(i).tick();
            if(remove){
                bullets.remove(i);
                i--;
            }
        }
        System.out.println("player.isFiring() is: " + player.isFiring());

        if(player.isFiring()){

            System.out.println("CALL-4");
            long  elapsed = (System.nanoTime() - firingTimer) / 1000000;
            if(elapsed > firingDelay) {
                System.out.println("1111111111111bullets.size() is: "+bullets.size());
                bullets.add(new Bullet(player.getX(), player.getY(), player.getAngle()));
                System.out.println("2222222bullets.size() is: "+bullets.size());

                firingTimer = System.nanoTime();
            }
        }
        player.setFiring(false);


        System.out.println("Bullets size is: " + bullets.size());
    }

    @Override
    public void render(Graphics g) throws Exception {
//        System.out.println("CALL-45");
        player.render(g);

            for(int i = 0; i < bullets.size(); i++){
                    bullets.get(i).render(g);
            }
            System.out.println("CALL-3");
    }
}
