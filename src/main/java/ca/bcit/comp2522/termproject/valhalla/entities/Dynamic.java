package ca.bcit.comp2522.termproject.valhalla.entities;

/**
 * A Dynamic interface for all moving things.
 * @author FonseLULW
 * @version 1.0
 */
public interface Dynamic {
    void movingAtX(boolean enabled);
    void movingAtY(boolean enabled);
    void move();
}
