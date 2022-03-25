package ca.bcit.comp2522.termproject.valhalla.entities;

/**
 * A Dynamic interface for all that can move.
 * @author FonseLULW
 * @version 1.0
 */
public interface Dynamic {
    /**
     * Moves itself.
     */
    void move();

    /**
     * Checks collision to entity.
     * @param entity an entity
     * @return true if there is a collision, else false
     */
    boolean collision(Entity entity);
}
