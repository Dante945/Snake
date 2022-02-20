import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JPanel implements ActionListener {

    private Timer timer = new Timer(100,this);
    public String curr_state;
    private Food food;
    private Snake player;
    private Game game;

    public UI(Game g){
        timer.start();
        curr_state = "Starting Screen";

        // Initialize components that need to have their designs changed
        game = g;
        player = game.getPlayer();
        food = game.getFood();

        // Adds a Key listener for the game
        this.addKeyListener(game);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(java.awt.Graphics graphics){
        int dim = Game.dimension;
        // Calls the parent class's paint function
        super.paintComponent(graphics);

        // Background while playing
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0,0,
                Game.width * dim,
                Game.height * dim);

        //Loading Screen
        if (curr_state == "Starting Screen"){
            graphics2D.setColor(Color.black);
            graphics2D.drawString("Press A key to begin",
                    ((Game.width * dim) / 2) - 60,
                    ((Game.height * dim) / 2) - 30);
        }
        else if(curr_state == "Playing"){
            // Color in the food
            graphics2D.setColor(Color.red);
            graphics2D.fillRect(food.getX() * dim,
                    food.getY() * dim,
                    dim, dim);

            // Color in the snake
            graphics2D.setColor(Color.green);
            for (Rectangle body_part: player.getBody()){
                graphics2D.fill(body_part);
            }
        }
        // Game Over
        else {
            graphics2D.setColor(Color.black);
            graphics2D.drawString(
                    "Game Over - Score: " + (player.getBody().size() - 3),
                    Game.width/2 * dim - 60,
                    Game.height / 2 * dim - 20);
        }
    }
    @Override
    // Updates new layout when the game is started
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }
}
