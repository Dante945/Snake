public class Food {
    private int x,y;

    public Food(Snake player){this.randomSpawn();}
    public void randomSpawn(){
        x = (int)(Math.random() * Game.width - 1);
        y = (int)(Math.random() * Game.height - 1);
    }
    // Getters for food collision purposes
    public int getY() {return y;}
    public int getX() {return x;}
}
