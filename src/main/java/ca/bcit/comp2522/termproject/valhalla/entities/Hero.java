package ca.bcit.comp2522.termproject.valhalla.entities;



public class Hero extends Entity {
    private static final int DEFAULT_X = 50;
    private static final int DEFAULT_Y = 50;
    private static final int DEFAULT_WIDTH = 150;
    private static final int DEFAULT_HEIGHT = 150;

    public Hero() {
        super("baymax.jpg");
    }

    @Override
    public void attack() {

    }

    @Override
    public void move() {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void death() {
        System.out.println("I died!");
    }
}
