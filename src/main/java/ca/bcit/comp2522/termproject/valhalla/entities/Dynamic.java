package ca.bcit.comp2522.termproject.valhalla.entities;

/**
 * A Dynamic interface for all things that moves on the display.
 * @author FonseLULW
 * @version 1.0
 */
public interface Dynamic {
    void setSpeedX(double speed);
    void setSpeedY(double speed);
    void move();
    boolean collision();
}
