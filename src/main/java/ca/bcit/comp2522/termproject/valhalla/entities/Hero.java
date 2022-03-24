package ca.bcit.comp2522.termproject.valhalla.entities;



public class Hero extends Entity {
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
