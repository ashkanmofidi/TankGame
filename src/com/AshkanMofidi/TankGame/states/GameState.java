package com.AshkanMofidi.TankGame.states;

import com.AshkanMofidi.TankGame.Game;
import com.AshkanMofidi.TankGame.entities.creatures.Bullet;
import com.AshkanMofidi.TankGame.entities.creatures.Player;
import com.AshkanMofidi.TankGame.gfx.Assets;
import com.AshkanMofidi.TankGame.tiles.Tile;
import com.AshkanMofidi.TankGame.worlds.World;

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
    private World world;
    public static ArrayList<Bullet> bullets;
    private long firingTimer, firingDelay;

    /*
        We need this constructor in the future
     */
    public GameState(Game game){
        super(game);
        /*
            The player constructor takes a x and y variables as its position (the starting positions of our player )
         */
//            public Player(float x, float y, int width, int height, float vx, float vy, float angle, int shoot, Game game) {
        world = new World("res/worlds/world1.txt");

        player = new Player(game,0, 0, 50, 50, 0, 0, 0, 0);
        player.setFiring(false);
        firingTimer = System.nanoTime();
        firingDelay = 200;
        bullets = new ArrayList<Bullet>();

    }
    @Override
    public void tick() {
        world.tick();
        player.tick();

        for(int i = 0; i < bullets.size(); i++){
            boolean remove = bullets.get(i).tick();
            if(remove){
                bullets.remove(i);
                i--;
            }
        }

        if(player.isFiring()){
            long  elapsed = (System.nanoTime() - firingTimer) / 1000000;
            if(elapsed > firingDelay) {
                bullets.add(new Bullet(game, player.getX(), player.getY(), player.getWidth(), player.getHeight(), player.getAngle()));
                firingTimer = System.nanoTime();

            }
        }
        player.setFiring(false);
    }

    @Override
    public void render(Graphics g) throws Exception {
            world.render(g);
            player.render(g);

        for(int i = 0; i < bullets.size(); i++){
                    bullets.get(i).render(g);
            }
    }
}
