package ca.bcit.comp2522.termproject.valhalla.entities;

/**
 * A Slayable interface for all things that can be slain.
 * @author FonseLULW
 * @author kaioh08
 * @version 1.0
 */
public interface Slayable {
    /**
     * Checks whether a Slayable is alive.
     * @return a boolean true if alive, else false
     */
    boolean isAlive();

    /**
     * Does something after a Slayable dies.
     */
    void death();
}
