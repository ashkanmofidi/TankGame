package com.AshkanMofidi.TankGame.states;

import com.AshkanMofidi.TankGame.Game;
import com.AshkanMofidi.TankGame.entities.creatures.Player;
import com.AshkanMofidi.TankGame.gfx.Assets;

import java.awt.*;

/*
    Now our GameState class is a State since it extends the State class
    So, we override the abstract methods of our State class here
 */
public class GameState extends State {

    /*
        Now, we write codes to use the codes that we wrote inside the Player class in the entities and creatures packages
     */
    private Player player;

    /*
        We need this constructor in the future
     */
    public GameState(Game game){
        super(game);
        /*
            The player constructor takes a x and y variables as its position (the starting positions of our player )
         */
        player = new Player(100, 100);
    }
    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }
}
