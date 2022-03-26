package ca.bcit.comp2522.termproject.valhalla.entities;

/**
 * A Hero class.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public class Hero extends Entity {
    private static final int DEFAULT_X = 50;
    private static final int DEFAULT_Y = 50;

    public Hero(final String filename, final int x, final int y) {
        super(filename, x, y);
    }

    public Hero(final String filename) {
        this(filename, DEFAULT_X, DEFAULT_Y);
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
