package ca.bcit.comp2522.termproject.valhalla.entities;

/**
 * A Dynamic interface for all moving things.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public interface Dynamic {
    void setVelocityX(double velocityX);
    void setVelocityY(double velocityY);
    void move();
}
