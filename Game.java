import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game
implements KeyListener {
    private Snake player;
    private Food food;
    private UI ui;

    private JFrame window;

    public static final int width = 40;
    public static final int height = 40;
    public static final int dimension = 20;

    private int left_bound, lower_bound = 0;
    private int right_bound = width * dimension;
    private int upper_bound = height * dimension;

    public Game(){
            window = new JFrame();
            player = new Snake();
            food = new Food(player);
            ui = new UI(this);
            window.add(ui);

            window.setSize(width * dimension,height * dimension);
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void startGame(){
        ui.curr_state = "Playing";
    }

    // Check all possible events that can happen while playing
    public void update(){
        if (ui.curr_state == "Playing"){
            //Event 1: Ate food
            if(foodCollision()){
                player.grow();
                food.randomSpawn();
            }
            // Event 2 and 3: Self and wall collisions
            else if (wallCollision() || selfCollision()){
                ui.curr_state = "END";
            }
            // If no events occur, simply move
            else {
                player.move();
            }}}

    private boolean selfCollision(){
        // Check if the head of the snake has made contact with any body part of the snake
        for(int i = 1; i < player.getBody().size(); i++){
            if(player.getX() == player.getBody().get(i).x &&
                    player.getY() == player.getBody().get(i).y){
                return true;
            }
        }
        return false;
    }

    private boolean foodCollision(){
        if(player.getX() == food.getX() * dimension
                && player.getY() == food.getY() * dimension){
            return true;
        }
        return false;
    }

    private boolean wallCollision(){
        if(player.getX() >= right_bound || player.getX() < left_bound
                || player.getY() < lower_bound || player.getY() >= upper_bound) {
        return true;
        }
        return false;
    }

    public void setWindow(JFrame window) {this.window = window;}
    public JFrame getWindow() {return window;}

    public Snake getPlayer() {return player;}

    public Food getFood() {return food;}

    public UI getUi() {return ui;}

    @Override
    public void keyPressed(KeyEvent e) {
        // Store the current pressed key
        int key = e.getKeyCode();

        // Compare it to the W,A,S,D format of moving
        if(ui.curr_state == "Playing") {
            if (key == KeyEvent.VK_W) {player.up();}
            else if (key == KeyEvent.VK_S) {player.down();}
            else if (key == KeyEvent.VK_A) {player.left();}
            else {player.right();}
        }
        // If the game has not started, any key press will do so
        else { this.startGame(); }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
