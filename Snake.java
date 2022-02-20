import java.awt.*;
import java.util.ArrayList;

public class Snake {
    // Body represented by an array list of rectangles
    private ArrayList<Rectangle> body = new ArrayList<>();

    // Game Dimensions
    private int width = Game.width;
    private int dim = Game.dimension;
    private int height = Game.height;

    // Placeholder for snake's current movement
    private String movement;

    // Default constructor to build a snake that is 3 rectangles long
    public Snake(){
        body = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            Rectangle temp = new Rectangle(dim,dim);
            temp.setLocation((width/2)*dim, (height/2-i)*dim);
            body.add(temp);
        }
        movement = "NONE";
    }

    // Depending on the movement, it creates a new body part in the given direction and deletes the tail of the snake
    // in order to simulate movement
    public void move(){
        if (movement != "NONE"){
            Rectangle head = body.get(0);
            Rectangle temp = new Rectangle(dim,dim);

            if(movement == "UP"){temp.setLocation(head.x, head.y - dim);}
            if(movement == "DOWN"){temp.setLocation(head.x, head.y + dim);}
            if(movement == "LEFT"){temp.setLocation(head.x - dim, head.y);}
            if(movement == "RIGHT"){temp.setLocation(head.x + dim, head.y);}
            // Adds body part to the head and removes the tail
            body.add(0,temp);
            body.remove(body.size()-1);
        }
    }

    // Same logic as the move function but since you are growing in this case, you don't remove the tail of the snake
    public void grow(){
        Rectangle head = body.get(0);
        Rectangle temp = new Rectangle(dim,dim);

        if(movement == "UP"){temp.setLocation(head.x, head.y - dim);}
        if(movement == "DOWN"){temp.setLocation(head.x, head.y + dim);}
        if(movement == "LEFT"){temp.setLocation(head.x - dim, head.y);}
        if(movement == "RIGHT"){temp.setLocation(head.x + dim, head.y);}

        // Adds body part to head
        body.add(0,temp);
        }

    public ArrayList<Rectangle> getBody(){return body;}
    public void setBody(ArrayList<Rectangle> body){this.body = body;}

    // Returns the coordinates of the head of the snake for collision event purposes
    public int getX(){return body.get(0).x;}
    public int getY(){return body.get(0).y;}

    // Various Movement Functions
    public void up(){movement = "UP";}
    public void down(){movement = "DOWN";}
    public void left(){movement = "LEFT";}
    public void right(){movement = "RIGHT";}
}
